package com.sxt.mapper;

import java.util.List;

import com.sxt.domain.Role;

public interface RoleMapper {


    List<Role> queryRoleByUserId(String userid);
}