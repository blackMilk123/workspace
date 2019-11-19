<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<shiro:hasPermission name="person:query">
		<h1><a href="user/query.action">查询用户</a></h1>
	</shiro:hasPermission>
	<shiro:hasPermission name="person:add">
	<h1><a href="user/add.action">添加用户</a></h1>
	</shiro:hasPermission>
	<shiro:hasPermission name="person:update">
	<h1><a href="user/update.action">修改用户</a></h1>
	</shiro:hasPermission>
	<shiro:hasPermission name="person:delete">
	<h1><a href="user/delete.action">删除用户</a></h1>
	</shiro:hasPermission>
	<shiro:hasPermission name="person:export">
	<h1><a href="user/export.action">导出用户</a></h1>
	</shiro:hasPermission>
</body>
</html>