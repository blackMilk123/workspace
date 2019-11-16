package com.workspace.shiro.service;

import java.util.List;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-11-08 17:21
 **/
public interface RoleService {
    List<String> queryRoleByUserName(String username);
}
