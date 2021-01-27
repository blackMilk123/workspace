package com.sxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.domain.Role;
import com.sxt.mapper.RoleMapper;
import com.sxt.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> queryRoleList(String userid) {
        return roleMapper.queryRoleByUserId(userid);
    }
}
