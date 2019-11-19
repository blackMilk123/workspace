package com.sxt.service;

import com.sxt.domain.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> queryPermissionByUserId(String id);
}
