<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="doc.beo.*"%>
<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 
  <s:url id="delClass" action="classEdit" method="delClassNodes">
 	<s:param name="classNodes.id" value="%{nodeId}"></s:param>
 </s:url>
 
<s:a href="addClass.jsp">添加分类</s:a>
<s:if test="nodeId != 1">
<s:a href="#" onclick="if(confirm('确定要删除  %{nodeName}  吗？')) window.location.href='%{delClass}'">删除分类</s:a>
</s:if>
<br>  
<br>
<s:form action="searchDoc" theme="simple">
	<s:textfield name="key"></s:textfield>
	<s:select name="searchType" list="#{1:'文档名',2:'文档编号',3:'关键词'}"></s:select>
	<s:submit value="检索"></s:submit>
</s:form>
<br/>
<s:property value="%{nodeName}"/><br>

<s:include value="docList.jsp"></s:include>
<br/>
<br/>
<s:a href="addDocPrep?nodeId=%{nodeId}" target="doclist" >添加文档</s:a>
<a href="import.jsp" target="doclist">数据导入</a>
</body>
</html>