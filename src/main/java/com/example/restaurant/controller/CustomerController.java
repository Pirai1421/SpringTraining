package com.example.restaurant.controller;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.entity.Order;
import com.example.restaurant.service.CustomerServiceImpl;
import com.example.restaurant.service.MenuItemServiceImpl;
import com.example.restaurant.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.parameters.P;
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

    //GETTING THE LIST OF CUSTOMER
    @GetMapping("/all")
    public List<Customer> findAll() {
        return customerService.findAll();
    }


    //ADDING THE CUSTOMER DETAILS WITH THE NAME, BASICALLY CREATING CUSTOMER OBJECT and saving it into the database...
    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    //GETTING THE LIST OF ITEM IN THE MENU

    @GetMapping("/menu")
    public List<MenuItem> getMenu() {
        return menuItemService.findAll();
    }

    //ADDING THE ITEMS WITH THEIR PRICES IN THE MENU
    @PostMapping("/menu/add")
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.save(menuItem);
    }

    //POST THE LIST OF ITEM WITH THEIR ID

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

    //GETTING THE LIST OF ITEMS
    @GetMapping("/{customerId}/orders")
    public List<Order> getCustomerOrders(@PathVariable long customerId) {
        return orderService.findByCustomerId(customerId);
    }

    //MAKE CHANGE TO THE ITEMS LIST
    @PutMapping("/{customerId}/orderchange")
    public Order ChangeOrder(@PathVariable long customerId,@RequestBody List<MenuItem> menuItems){
        Customer customer=customerService.findById(customerId);
        List<MenuItem> ChangeMenuItems=menuItems.stream().map(items -> {
            MenuItem ChangeItem=menuItemService.findById(items.getId());
            ChangeItem.setQuantity(items.getQuantity());
            return ChangeItem;
        }).collect(Collectors.toList());
        Order order=new Order();
        order.setCustomer(customer);
        order.setMenuItems(ChangeMenuItems);
        return orderService.save(order);

    }
    @DeleteMapping("/{customerId}")
    public Customer DeleteCustomer(@PathVariable long customerId){
        Customer customer=customerService.findById(customerId);
        customerService.deleteById(customerId);
        return customer;


    }
}