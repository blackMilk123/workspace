package com.sxt.service;

import com.sxt.domain.Role;

import java.util.List;

public interface RoleService {
	

    List<Role> queryRoleList(String userid);

}
