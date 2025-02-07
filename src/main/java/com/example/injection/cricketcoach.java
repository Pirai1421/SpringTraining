package com.example.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
public class cricketcoach implements coach{



    public void daily(){
        System.out.println("bowling,batting");

    }
}
