<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<base href="<%=basePath%>">
	<title>用户登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="styles/login.css">
	</head>
	<body>
    <div id="login" style="margin: 50px auto;">
    	<div style="height: 50px;font-size: 16px;text-align: center;">
    		欢迎使用恒电电子档案管理系统，请先登录！
    	</div>
      <s:form id="loginform" action="login" namespace="/" theme="simple">
        <table class="login" width="100%" align="center">
          <tr>
          	<td id="loginerr" colspan="2"><s:fielderror/></td>
          </tr>
          <tr>
            <td width="25%" align="right" height="40">用户名：</td>
            <td width="75%" align="left">
            	<s:textfield id="account" name="account" cssClass="text" tabindex="1"></s:textfield>
            </td>
          </tr>
          <tr>
            <td align="right">密码：</td>
            <td align="left"><s:password name="password"  cssClass="text" tabindex="2"></s:password></td>
          </tr>
          <tr valign="top">
            <td colspan="2" align="center" height="40" valign="middle">
            <s:submit tabindex="3" type="image" src="images/login.jpg"></s:submit>         
              &nbsp; <img src="images/return.png" border="0" style="cursor: hand"></img>
            </td>
          </tr>
        </table>
      </s:form>
    </div>
</body>
</html>
