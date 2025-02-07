package com.example.injection;

import org.springframework.stereotype.Component;

@Component
public class VolleyballCoach implements coach{
    @Override
    public void daily() {
        System.out.println("smash, volley");
    }
}
