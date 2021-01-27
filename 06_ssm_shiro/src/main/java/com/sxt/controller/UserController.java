package com.sxt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制器
 * 
 * @author LJH
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/toUserManager")
    public String toUserManager() {
        return "list";
    }

}
