package com.sxt.mapper;

import org.apache.ibatis.annotations.Param;

import com.sxt.domain.User;

public interface UserMapper {

    User queryUserByName(String username);
}