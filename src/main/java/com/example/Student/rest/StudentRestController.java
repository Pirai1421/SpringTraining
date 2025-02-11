package com.example.Student.rest;


import com.example.Student.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;
    @PostConstruct
    public void loadData(){
        theStudents=new ArrayList<>();
        theStudents.add(new Student("pirai","soodan "));
        theStudents.add(new Student("yathis","kumar"));

    }
    @GetMapping("/students/{sid}")
    public Student getStudent(@PathVariable int sid){
        if ((sid>=theStudents.size())||(sid<0)){
            throw new StudentNotFoundException("Student not found -" + sid);
        }

        return theStudents.get(sid);
    }
    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents;
    }


}
