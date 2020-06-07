package com.blackmilk.securitydemo2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @RequestMapping("/demoError")
    public String error(){
        return "error";
    }
}
