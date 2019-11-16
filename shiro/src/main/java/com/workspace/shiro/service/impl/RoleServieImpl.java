package com.workspace.shiro.service.impl;

import com.workspace.shiro.service.RoleService;

import java.util.Arrays;
import java.util.List;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-11-08 17:25
 **/
public class RoleServieImpl implements RoleService {
    @Override
    public List<String> queryRoleByUserName(String username) {
        return Arrays.asList("role1","role2","role3");
    }
}
