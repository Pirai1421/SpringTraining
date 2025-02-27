package com.example.restaurant.service;

import com.example.restaurant.dao.OrderDao;
import com.example.restaurant.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl {
    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> findAll() {
        return orderDao.findAll();
    }

    public Order findById(long id) {
        return orderDao.findById(id).orElse(null);
    }

    @Transactional
    public Order save(Order order) {
        return orderDao.save(order);
    }

    @Transactional
    public void deleteById(long id) {
        orderDao.deleteById(id);
    }

    public List<Order> findByCustomerId(long customerId) {
        return orderDao.findByCustomerId(customerId);
    }
}