package com.example.restaurant.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_menu_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id")
    )
    private List<MenuItem> menuItems;

    @Column(name = "total_price")
    private double totalPrice;

    @PrePersist
    @PreUpdate
    private void calculateTotalPrice() {
        totalPrice = menuItems.stream().mapToDouble(MenuItem::getPrice).sum();
    }


    public Order() {
    }

    public Order(Customer customer, List<MenuItem> menuItems) {
        this.customer = customer;
        this.menuItems = menuItems;
        calculateTotalPrice();
    }


    public long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        calculateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}