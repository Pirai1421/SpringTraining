package com.example.restaurant.controller;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.entity.Order;
import com.example.restaurant.service.CustomerServiceImpl;
import com.example.restaurant.service.MenuItemServiceImpl;
import com.example.restaurant.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerServiceImpl customerService;
    private final MenuItemServiceImpl menuItemService;
    private final OrderServiceImpl orderService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService, MenuItemServiceImpl menuItemService, OrderServiceImpl orderService) {
        this.customerService = customerService;
        this.menuItemService = menuItemService;
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping("/menu")
    public List<MenuItem> getMenu() {
        return menuItemService.findAll();
    }


    @PostMapping("/menu/add")
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.save(menuItem);
    }


    @PostMapping("/{customerId}/order")
    public Order placeOrder(@PathVariable long customerId, @RequestBody List<MenuItem> menuItems) {
        Customer customer = customerService.findById(customerId);
        List<MenuItem> fullMenuItems = menuItems.stream()
                .map(item -> {
                    MenuItem fullItem = menuItemService.findById(item.getId());
                    fullItem.setQuantity(item.getQuantity());
                    return fullItem;
                })
                .collect(Collectors.toList());
        Order order = new Order();
        order.setCustomer(customer);
        order.setMenuItems(fullMenuItems);
        return orderService.save(order);
    }


    @GetMapping("/{customerId}/orders")
    public List<Order> getCustomerOrders(@PathVariable long customerId) {
        return orderService.findByCustomerId(customerId);
    }
}