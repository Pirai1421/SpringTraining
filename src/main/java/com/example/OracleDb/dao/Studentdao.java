package com.example.OracleDb.dao;

import com.example.OracleDb.Entity.Student;

import java.util.List;

public interface Studentdao {
    void save(Student student);
    Student findbyId(Integer id);

    List<Student> findall();

    List<Student> findbyLastName(String lastname);

    int update();
    void updates(Student s);

    void removes(int id);
}
