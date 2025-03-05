package com.example.restaurant.service;

import com.example.restaurant.dao.CustomerDao;
import com.example.restaurant.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl {
    private final CustomerDao customerDao;


    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Cacheable("customers")
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Cacheable(value = "customers", key = "#id")
    public Customer findById(long id) {
        return customerDao.findById(id).orElse(null);
    }

    @Transactional
    @CacheEvict(value = "customers",allEntries = true)
    public Customer save(Customer customer) {
        return customerDao.save(customer);
    }

    @Transactional
    @CacheEvict(value = "customers",allEntries = true)
    public void deleteById(long id) {
        customerDao.deleteById(id);
    }
}