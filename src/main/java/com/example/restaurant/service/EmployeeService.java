package com.example.restaurant.service;

import com.example.restaurant.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {

    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Employee getEmployeeDetails(int employeeId) {
        String url = "http://localhost:8097/api/employ/" + employeeId; // Replace with the actual endpoint
        return restTemplate.getForObject(url, Employee.class);
    }
}