package com.workspace.shiro.entity;

import java.util.List;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-11-08 17:28
 **/
public class ActiveUser {
    private List<String> roles;
    private List<String> permission;
    private User user;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ActiveUser(List<String> roles, List<String> permission, User user) {
        this.roles = roles;
        this.permission = permission;
        this.user = user;
    }
}
