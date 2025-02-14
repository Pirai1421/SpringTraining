package com.Springboot.Employee.rest;

import com.Springboot.Employee.dao.EmployeedaoImpl;
import com.Springboot.Employee.entity.Employee;
import com.Springboot.Employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/employ/{employeeid}")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        Employee dbemployee=employeeService.save(theEmployee);
        return dbemployee;
    }
    @PutMapping("/employ")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
    @DeleteMapping("/employ")
    public String DeleteEmployee(@PathVariable int eid ){
        Employee theEmployee=employeeService.findById(eid);
        if (theEmployee==null){
            throw new RuntimeException("employee not found");
        }
        employeeService.deleteById(eid);
        return "Deleted employee id-" +eid;
    }
}

