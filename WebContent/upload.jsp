<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = "/Users/apple/Documents/Database";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload</title>
</head>
<body>
	<form name = "uploadForm" method = "POST" enctype = "MULTIPART/FORM-DATA" action="UploadServlet?method=upload">
		<table>
		<tr>
			<td>${username }</td>
		</tr>
		<tr>
			<td><div align = "right">上传图片</div></td>
			<td><input type = "file" name = "file1"/> </td>
		</tr>
		<tr>
			<td><input type = "submit" name = "submit" value = "提交"></td>
			<td><input type = "reset" name = "reset" value = "重置"> </td>
		</tr>
		</table>
	</form>
</body>
</html>