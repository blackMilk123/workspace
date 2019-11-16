package com.workspace.shiro.service.impl;

import com.workspace.shiro.entity.User;
import com.workspace.shiro.service.UserService;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-11-03 17:21
 **/
public class UserImpl implements UserService {
    @Override
    public User queryUserName(String username) {

        User user =null;
        switch (username){
            case "user1":
                 user = new User(1, "user1", "123456");
                break;
            case "user2":
                 user = new User(2, "user2", "123456");
                break;
            case "user3":
                user = new User(3, "user3", "123456");
        }
        return user;
    }
}
