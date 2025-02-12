package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Instructor;

public interface Appdao {
    void save(Instructor theinstructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
}
