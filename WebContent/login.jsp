<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录窗口</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
	<form action="UserServlet?method=login" method="post">
		<table>
			<tr>
				<td colspan="2">欢迎进入掌上衣柜</td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username" />
				</td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="登录" /><input type="button" value = "注册" onclick = "location.href = 'register.jsp'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
