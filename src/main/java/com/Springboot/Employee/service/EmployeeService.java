package com.Springboot.Employee.service;

import com.Springboot.Employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee theEmployee);
    void deleteById(int id);
    Employee findEmploy(int id);
}
