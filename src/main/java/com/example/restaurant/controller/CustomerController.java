package com.example.restaurant.controller;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.Employee;
import com.example.restaurant.service.CustomerServiceImpl;
import com.example.restaurant.service.MenuItemServiceImpl;
import com.example.restaurant.service.OrderServiceImpl;
import com.example.restaurant.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
@Tag(name = "Customer", description = "Customer management APIs")
public class CustomerController {
    private final CustomerServiceImpl customerService;
    private final MenuItemServiceImpl menuItemService;
    private final OrderServiceImpl orderService;
    private final EmployeeService employeeService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService, MenuItemServiceImpl menuItemService, OrderServiceImpl orderService, EmployeeService employeeService) {
        this.customerService = customerService;
        this.menuItemService = menuItemService;
        this.orderService = orderService;
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get all customers")
    @GetMapping("/all")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @Operation(summary = "Add a new customer")
    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @Operation(summary = "Get all menu items")
    @GetMapping("/menu")
    public List<MenuItem> getMenu() {
        return menuItemService.findAll();
    }

    @Operation(summary = "Add a new menu item")
    @PostMapping("/menu/add")
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.save(menuItem);
    }

    @Operation(summary = "Place an order")
    @PostMapping("/{customerId}/order")
    public Order placeOrder(@PathVariable long customerId, @RequestParam int employeeId, @RequestBody List<MenuItem> menuItems) {
        Customer customer = customerService.findById(customerId);
        Employee employee = employeeService.getEmployeeDetails(employeeId);
        List<MenuItem> fullMenuItems = menuItems.stream()
                .map(item -> {
                    MenuItem fullItem = menuItemService.findById(item.getId());
                    fullItem.setQuantity(item.getQuantity());
                    return fullItem;
                })
                .collect(Collectors.toList());
        Order order = new Order();
        order.setCustomer(customer);
        order.setEmployee(employee);
        order.setMenuItems(fullMenuItems);
        return orderService.save(order);
    }

    @Operation(summary = "Get all orders for a customer")
    @GetMapping("/{customerId}/orders")
    public List<Order> getCustomerOrders(@PathVariable long customerId) {
        return orderService.findByCustomerId(customerId);
    }

    @Operation(summary = "Change an order")
    @PutMapping("/{customerId}/orderchange")
    public Order changeOrder(@PathVariable long customerId, @RequestParam int employeeId, @RequestBody List<MenuItem> menuItems) {
        Customer customer = customerService.findById(customerId);
        Employee employee = employeeService.getEmployeeDetails(employeeId);
        List<MenuItem> changeMenuItems = menuItems.stream().map(items -> {
            MenuItem changeItem = menuItemService.findById(items.getId());
            changeItem.setQuantity(items.getQuantity());
            return changeItem;
        }).collect(Collectors.toList());
        Order order = new Order();
        order.setCustomer(customer);
        order.setEmployee(employee);
        order.setMenuItems(changeMenuItems);
        return orderService.save(order);
    }

    @Operation(summary = "Delete a customer")
    @DeleteMapping("/{customerId}")
    public Customer deleteCustomer(@PathVariable long customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer != null) {
            customerService.deleteById(customerId);
            return customer;
        } else {
            throw new RuntimeException("Customer not found with id: " + customerId);
        }
    }
}