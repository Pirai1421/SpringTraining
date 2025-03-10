package com.example.restaurant.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
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

    @JdbcTypeCode(SqlTypes.JSON)
    @Transient
    private Employee employee; // Add employee field

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
        totalPrice = menuItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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