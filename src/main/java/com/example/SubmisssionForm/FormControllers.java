package com.example.SubmisssionForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormControllers {

    @GetMapping("power")
    public String power() {
        System.out.println("hello");
        return "power";
    }
    @RequestMapping("/")
    public String about(){
        return "about";
    }

    @GetMapping ("test")
    public String test() {
        return "testView";
    }

    @PostMapping ("details")
    public String viewDetails(@RequestParam("cid") String cid,
                              @RequestParam("cname") String cname,
                              @RequestParam("cemail") String cemail,
                              ModelMap modelMap) {
        modelMap.put("cid", cid);
        modelMap.put("cname", cname);
        modelMap.put("cemail", cemail);
        return "ViewCustomer";
    }
}