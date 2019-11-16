package com.workspace.shiro.service;

import java.util.List;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-11-08 17:20
 **/
public interface PermissionService {
    List<String> queryPermissionByUserName(String username);
}
