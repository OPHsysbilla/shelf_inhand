<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcom.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
	<table>
		<tr>
			<td>
				<form action="UserServlet?method=logout" method="post">
					<table>
						<tr>
							<td colspan="2">登录成功!</td>
						</tr>
						<tr>
							<td>欢迎你，</td>
							<td>${username }</td>
						</tr>
						<tr>
							<td><input type="button" value = "上传图片" onclick = "location.href = 'upload.jsp'"></td>
							<td><input type="button" value = "下载图片" onclick = "location.href = 'UserServlet?method=download'"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="退出" /></td>
						</tr>
					</table>
				</form></td>
		</tr>
	</table>
</body>
</html>
