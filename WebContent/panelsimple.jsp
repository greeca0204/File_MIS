<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><decorator:title></decorator:title></title>
		<script type="text/javascript" src="<%=path %>/js/dtree.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<link rel="stylesheet" href="<%=path %>/styles/layout.css" type="text/css">
		<link rel="stylesheet" href="<%=path %>/styles/dtree.css" type="text/css"></link>
		<link rel="stylesheet" type="text/css" href="<%=path %>/styles/extremecomponents.css">
		
		
		
		<!-- 引入thickbox相关脚本 -->
		<script type="text/javascript" src="<%=path %>/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"  src="<%=path %>/js/thickbox.js"></script> 
		<link rel="stylesheet" href="<%=path %>/styles/thickbox.css" type="text/css" media="screen" />
		
		<%--日历样式--%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/styles/datePicker.css" />
		<decorator:head></decorator:head>
	</head>
	<body>
		<div id="top">
  			<img src="<%=path %>/images/logo.gif" /><br/>
  			<img alt="" src="<%=path %>/images/blue.png" width="100%" height="6">
  		</div>
  		<div id="container">
  			<decorator:body></decorator:body>
  		</div>
	</body>
		
	
	
</html>