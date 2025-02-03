package com.Web.simpleWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @RequestMapping("/")
    @ResponseBody
    public String Greet() {
        System.out.println("welcome to webpage");
        return "welcome to the webpage";
    }
    @RequestMapping("/about")
    @ResponseBody
    public String about(){
        return "we don't teach , we Educate!!!";
    }
}
