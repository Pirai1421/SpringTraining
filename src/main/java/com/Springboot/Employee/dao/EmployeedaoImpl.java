package com.Springboot.Employee.dao;

import com.Springboot.Employee.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeedaoImpl implements Employeedao{
    private EntityManager entityManager;
    @Autowired
    public EmployeedaoImpl(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery=entityManager.createQuery("from Employee",Employee.class);
        List<Employee> employees=theQuery.getResultList();
        return employees;

    }

    @Override
    public Employee findById(int id) {
        Employee theEmployee=entityManager.find(Employee.class,id);
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        Employee dbEmployee =entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
        Employee dbEmployee=entityManager.find(Employee.class,id);
        entityManager.remove(dbEmployee);

    }

    @Override
    public Employee findEmploy(int id) {
        Employee dbEmployee=entityManager.find(Employee.class,id);
        return dbEmployee;
    }
}
