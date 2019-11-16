package com.workspace.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-11-03 09:54
 **/
public class TestAuthentication {
    private static final transient Logger log = LoggerFactory.getLogger(TestAuthentication.class);
    public static void main(String[] args) {
        String username= "admin";
        String password = "1234556";
        //创建安全管理器工厂对象
        IniSecurityManagerFactory managerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //创建安全管理器实例
        SecurityManager securityManager = managerFactory.getInstance();
        //绑定安全管理器到当前线程
        SecurityUtils.setSecurityManager(securityManager);
        //得到主体对象
        Subject subject = SecurityUtils.getSubject();
        //封装认证
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        try {
            //进行认证
            subject.login(token);
            System.out.println("登录成功");
        }catch (AuthenticationException e){
            System.out.println("用户名或密码错误");
        }


    }
}
