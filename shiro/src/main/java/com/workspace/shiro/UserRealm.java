package com.workspace.shiro;

import com.workspace.shiro.entity.ActiveUser;
import com.workspace.shiro.entity.User;
import com.workspace.shiro.service.PermissionService;
import com.workspace.shiro.service.RoleService;
import com.workspace.shiro.service.UserService;
import com.workspace.shiro.service.impl.PermissionServiceImpl;
import com.workspace.shiro.service.impl.RoleServieImpl;
import com.workspace.shiro.service.impl.UserImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: workspace
 * @description:
 * @author: Y-J
 * @create: 2019-11-03 17:40
 **/
public class UserRealm extends AuthorizingRealm {
    UserService userService = new UserImpl();
    RoleService roleService = new RoleServieImpl();
    PermissionService permissionService = new PermissionServiceImpl();

    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //得到用户名
        String username = token.getPrincipal().toString();
        String password = token.getCredentials().toString();


        User user = userService.queryUserName(username);
        List<String> permission = permissionService.queryPermissionByUserName(username);
        List<String> role = roleService.queryRoleByUserName(username);

        ActiveUser activeUser = new ActiveUser(role, permission, user);
        //数据库里查询是否有对应的用户
        //然后才会进行认证  不是用户名密码一起认证

        //如果用户存在
        if (user != null){

            /**
             * 参数1:传任意对象 可以传参给授权方法
             * 参数2:传数据库查出来的密码
             * 参数3:当前类名
             */
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, user.getPassword(), this.getName());

            //根据用户名得到方法
            return info;
        }else {
            return null;
        }

    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        ActiveUser activeUser = (ActiveUser) principalCollection.getPrimaryPrincipal();


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addRoles(activeUser.getRoles());
       info.addStringPermissions(activeUser.getPermission());



        System.out.println("授权");

        return  info;
    }
}
