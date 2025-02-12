package com.Springboot.Employee.service;

import com.Springboot.Employee.dao.Employeedao;
import com.Springboot.Employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService{
    private Employeedao employeedao;
    @Autowired
    public EmployeeServiceImpl(Employeedao e){
        employeedao=e;
    }

    @Override
    public List<Employee> findAll() {
        return employeedao.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeedao.findById(id);
    }

    @Override
    @Transactional
    public Employee save(Employee theEmployee) {
        return employeedao.save(theEmployee);

    }

    @Override
    @Transactional
    public void deleteById(int id) {
        employeedao.deleteById(id);


    }

    @Override
    public Employee findEmploy(int id) {
        return employeedao.findEmploy(id);
    }
}
