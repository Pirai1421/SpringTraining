package com.example.restaurant.service;

import com.example.restaurant.dao.CustomerDao;
import com.example.restaurant.dao.OrderDao;
import com.example.restaurant.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl {
    private final CustomerDao customerDao;
    private final OrderDao orderDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, OrderDao orderDao) {
        this.customerDao = customerDao;
        this.orderDao = orderDao;
    }

    @Cacheable(value = "customers",cacheManager = "customersCacheManager")
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Cacheable(value = "customers", key = "#id",cacheManager = "customersCacheManager")
    public Customer findById(long id) {
        return customerDao.findById(id).orElse(null);
    }

    @Transactional
    @CacheEvict(value = "customers", allEntries = true,cacheManager = "customersCacheManager")
    public Customer save(Customer customer) {
        return customerDao.save(customer);
    }

    @Transactional
    @CacheEvict(value = "customers", allEntries = true,cacheManager = "customersCacheManager")
    public void deleteById(long id) {
        // Delete child records first
        orderDao.deleteByCustomerId(id);

        // Delete parent record
        customerDao.deleteById(id);
    }
}