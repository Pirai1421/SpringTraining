package com.example.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private coach mycoach;
    @Autowired
    public void Controller(@Qualifier("volleyballCoach") coach thecoach){
        mycoach=thecoach;
     }

    @RequestMapping("/")
    public void usecoach(){
        mycoach.daily();
    }

}
