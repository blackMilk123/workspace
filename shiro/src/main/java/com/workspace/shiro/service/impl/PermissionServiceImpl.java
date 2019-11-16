package com.workspace.shiro.service.impl;

import com.workspace.shiro.service.PermissionService;

import java.util.Arrays;
import java.util.List;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-11-08 17:21
 **/
public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<String> queryPermissionByUserName(String username) {
        return Arrays.asList("user:query","user:add","user:update","user:delete");
    }
}
