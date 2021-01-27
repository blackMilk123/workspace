<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">用户登陆</h2>
	<form action="${pageContext.request.contextPath}/login/login.action" method="post">
		<table width="30%" align="center" border="1" cellpadding="2" cellspacing="2">
			<tr>
				<td align="right">用户名:</td>
				<td>
					<input type="text" name="username" >
				</td>
			</tr>
			<tr>
				<td align="right">密码:</td>
				<td>
					<input type="password" name="pwd" >
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="登陆">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>