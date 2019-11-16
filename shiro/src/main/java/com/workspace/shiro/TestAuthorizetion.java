package com.workspace.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-11-03 11:01
 **/
public class TestAuthorizetion {
    private static final transient Logger log = LoggerFactory.getLogger(TestAuthentication.class);
    public static void main(String[] args) {
        String username= "user2";
        String password = "123456";
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
            boolean authenticated = subject.isAuthenticated();
            if (authenticated){
                System.out.println("登录成功");
                boolean role1 = subject.hasRole("role1");
                System.out.println("验证是否有role1角色-----"+role1);
                boolean permitted = subject.isPermitted("user:query");
                if (permitted){
                    System.out.println("验证当前用户是否有权限---" +permitted);
                }

            }

        }catch (AuthenticationException e){
            System.out.println("用户名或密码错误");
        }


    }
}
