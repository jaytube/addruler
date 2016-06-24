<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; Charset=UTF-8">
<title>Upload A File</title>
</head>
<body>
	<form method="POST" enctype="multipart/form-data" action="upload">
		<table>
			<tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
			<tr><td>Name:</td><td><input type="text" name="name" /></td></tr>
			<tr><td>length:</td><td><input type="text" name="length" /></td></tr>
			<tr><td>width:</td><td><input type="text" name="width" /></td></tr>
			<tr><td></td><td><input type="submit" value="Upload" /></td></tr>
		</table>
	</form>
</body>
</html>