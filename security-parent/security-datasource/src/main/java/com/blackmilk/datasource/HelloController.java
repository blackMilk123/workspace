package com.blackmilk.datasource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
    @RequestMapping("/admin/hello")
    public String admin(){
        return "admin";
    }
    @RequestMapping("/user/hello")
    public String user(){
        return "user";
    }
}
