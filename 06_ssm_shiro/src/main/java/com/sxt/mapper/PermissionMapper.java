package com.sxt.mapper;

import java.util.List;

import com.sxt.domain.Permission;

public interface PermissionMapper {
    List<Permission> queryPermissionByUserId(String userid);
}