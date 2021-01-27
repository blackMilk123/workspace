package com.sxt.realm;

import java.util.ArrayList;
import java.util.List;

import com.sxt.domain.Permission;
import com.sxt.domain.Role;
import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.sxt.domain.User;
import com.sxt.service.PermissionService;
import com.sxt.service.RoleService;
import com.sxt.service.UserService;
import com.sxt.utils.ActivierUser;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService  permissionService;

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	/*
	*
	* 授权
	* */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		ActivierUser activierUser = (ActivierUser) principalCollection.getPrimaryPrincipal();

		List<String> permissions = activierUser.getPermissions();
		List<String> roles = activierUser.getRoles();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();



		if (roles != null && roles.size()>0){

			info.addRoles(roles);
		}
		if (permissions != null && permissions.size()>0){

			info.addStringPermissions(permissions);
		}
		return info;



//		return null;

	}

	/*
	* 认证
	* */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		//拿到用户名
		String username = token.getPrincipal().toString();
		//查询用户名是否存在
		User user = userService.queryUserByName(username);
		if(user == null){
			return null;
		}
		//查询角色和权限
		List<Role> roleList = roleService.queryRoleList(user.getUserid().toString());
		ArrayList<String> roles = new ArrayList<>();
		for (Role role : roleList) {
			roles.add(role.getRolename());
		}

		ArrayList<String> permissions = new ArrayList<>();

		List<Permission> permissionList = permissionService.queryPermissionByUserId(user.getUserid().toString());
		for (Permission permission : permissionList) {
			permissions.add(permission.getPercode());
		}
		//盐
		ByteSource salt = ByteSource.Util.bytes(user.getUsername() + user.getAddress());

		ActivierUser activierUser = new ActivierUser(user,roles,permissions);

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activierUser,user.getUserpwd(),salt,this.getName());
		return info;


	}
}
