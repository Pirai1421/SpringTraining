package com.Springboot.Employee.rest;

import com.Springboot.Employee.dao.EmployeedaoImpl;
import com.Springboot.Employee.entity.Employee;
import com.Springboot.Employee.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    public EmployeeRestController(EmployeeService e){
        employeeService=e;
    }
    @GetMapping("/employ")
    public List<Employee> findAll(){
        return employeeService.findAll();

    }
    @GetMapping("/employ/{eid}")
    public Employee findEmploy(@PathVariable int eid){
        Employee theEmployee=employeeService.findEmploy(eid);
        if(theEmployee==null){
            throw new RuntimeException("Employee not found "+eid);

        }
        else{
            return theEmployee;
        }
    }
}
