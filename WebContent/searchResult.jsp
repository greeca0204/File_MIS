<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="doc.beo.*"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>检索结果</title>
	</head>

	<body>

		<%--  检索表单--%>
		<s:form action="searchDoc" theme="simple">
			<s:textfield name="key" size="40"></s:textfield>
			<s:select name="searchType" list="#{1:'文档名',2:'文档编号',3:'关键字',4:'文档描述'}"></s:select>
			<s:submit value="检索"></s:submit>
		</s:form>

		<ec:table items="doclist" var="doc" rowsDisplayed="20" 
			imagePath="images/extremetable/compact/*.gif"
 			action="searchDoc" width="95%"
 			view="doc.extremetable.view.DocHtmlView">
 		<ec:exportXls fileName="export.xls" tooltip="导出文档信息到EXCEL文件">导出</ec:exportXls>
 		<ec:row>
 			<ec:column property="number" title="文档编号" ></ec:column>
 			<ec:column property="name" title="文档名称">
 				<a href="viewDoc?id=${doc.id}&nodeId=${doc.class_id }">${doc.name}</a>
 			</ec:column>
 			<ec:column property="doc_time" cell="date" parse="yyyy-MM-dd" format="yyyy-MM-dd" title="文档时间">
 			</ec:column>

 			<ec:column property="opration" title="操作" sortable="false" filterable="false" viewsDenied="xls">
 				<a href="editDocPrep?id=${doc.id}&nodeId=${doc.class_id }">修改</a>
 				<a href="javascript:void(0)" onclick="if(confirm('确定要删除  ${doc.number}  吗？')) window.location.href='delDoc?id=${doc.id}'">删除</a>
 			</ec:column>
 		</ec:row> 		
 		</ec:table>
 		</body>

</html>
