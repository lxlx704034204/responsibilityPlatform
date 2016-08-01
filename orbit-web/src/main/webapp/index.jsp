<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>" />
        <meta http-equiv="refresh" content="5000;url=/regular/levelthree/index.do"></s:url>"/>
        <title>首页</title>
    </head>
    <body></body>
</html>
