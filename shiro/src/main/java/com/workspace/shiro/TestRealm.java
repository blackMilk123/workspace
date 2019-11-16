package com.workspace.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-11-03 17:53
 **/
public class TestRealm {
    private static final transient Logger log = LoggerFactory.getLogger(TestAuthentication.class);
    public static void main(String[] args) {
        String username= "user1";
        String password = "123456";
        //创建安全管理器工厂对象
        IniSecurityManagerFactory managerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //创建安全管理器实例
        DefaultSecurityManager securityManager = (DefaultSecurityManager)managerFactory.getInstance();
        //绑定安全管理器到当前线程
        SecurityUtils.setSecurityManager(securityManager);
        //得到realm对象 再认证就会走这里
        UserRealm userRealm = new UserRealm();
        securityManager.setRealm(userRealm);
        //得到主体对象
        Subject subject = SecurityUtils.getSubject();
        //封装认证
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        try {
            //进行认证
            subject.login(token);
            System.out.println("登录成功");

            //判断是否有该角色
            boolean role1 = subject.hasRole("role1");
            System.out.println(role1);

            //判断是否有对应的权限
            boolean permitted = subject.isPermitted("user:add");
            System.out.println(permitted);
        }catch (AuthenticationException e){
            System.out.println("用户名或密码错误");
        }


    }
}
