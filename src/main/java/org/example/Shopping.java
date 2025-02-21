package org.example;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
public class Shopping {
    public void checkout(String status){
        //logging


        System.out.println("checkout");
    }
}
