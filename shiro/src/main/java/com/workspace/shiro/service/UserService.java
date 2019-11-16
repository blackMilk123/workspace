package com.workspace.shiro.service;

import com.workspace.shiro.entity.User;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-11-03 17:20
 **/
public interface UserService {
    public User queryUserName(String username);
}
