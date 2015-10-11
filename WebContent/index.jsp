<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>电子文档管理软件</title>
		
		<link rel="stylesheet" href="/document/styles/subcss.css" type="text/css">
		<link rel="stylesheet" href="/document/styles/papers.css" type="text/css">
	</head>
	
<%--	<frameset rows="125,*" border="0">	--%>
<%--		<frame name="menu" src="document/top.jsp">--%>
<%--		<frameset cols="205,*">--%>
<%--			<frame name="top" src="document/tree.jsp">--%>
<%--			<frame name="doclist" src="classSelected?nodeId=1">		--%>
<%--		</frameset>		--%>
<%--	</frameset>	--%>

	<body> 
		<s:action name="classSelected" executeResult="true">
			<s:param name="nodeId" value="1"></s:param>
		</s:action>
	</body>
</html>