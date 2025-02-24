package com.Security.SecurityProject;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private String sec;
    public Student(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public int getId() {
        return id;
    }

    public Student(int id, String sec, String name) {
        this.id = id;
        this.sec = sec;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }



}
