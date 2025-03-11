package com.example.restaurant.service;

import com.example.restaurant.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {

    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Employee getEmployeeDetails(int employeeId) {

        String url = "http://localhost:8097/api/employ/" + employeeId;
        return restTemplate.getForObject(url, Employee.class);

    }

    public Employee addEmployee(Employee employee) {

        String url = "http://localhost:8097/api/employ/add";
        return restTemplate.postForObject(url, employee, Employee.class);

    }

    public ResponseEntity<Employee> updateEmployee(Employee employee) {

            String url = "http://localhost:8097/api/employ/update";
            HttpEntity<Employee> request = new HttpEntity<>(employee);
            return restTemplate.exchange(url, HttpMethod.PUT, request, Employee.class);

    }
}