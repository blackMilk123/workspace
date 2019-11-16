package com.workspace.shiro.entity;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-11-03 17:25
 **/
public class User {
    private Integer id;
    private  String username;
    private String password;

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public User(Integer id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
