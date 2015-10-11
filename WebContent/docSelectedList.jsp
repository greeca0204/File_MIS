<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'docSelectedList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     	<ec:table items="selected" var="doc" rowsDisplayed="20" imagePath="document/images/extremetable/compact/*.gif"
 	action="classSelected" width="95%">
 	<ec:exportXls fileName="export.xls" tooltip="导出文档信息到EXCEL文件">导出</ec:exportXls>
 		<ec:row>
 			<ec:column property="number" title="文档编号" ></ec:column>
 			<ec:column property="name" title="文档名称">
 				<a href="viewDoc?id=${doc.id}">${doc.name}</a>
 			</ec:column>
 			<ec:column property="doc_time" cell="date" parse="yyyy-MM-dd" format="yyyy-MM-dd" title="文档时间">
 			</ec:column>
 			<ec:column property="file_id" sortable="false" filterable="false" title="附件" width="40">
 				<c:if test="${doc.file_id != 0 }">
 					<a href="download?fid=${doc.file_id}">下载</a>
 				</c:if>
 				  <c:if test="${doc.file_id == 0 }">
 				  	<c:out value="无"></c:out>
 				  </c:if>
 			</ec:column>
 			<ec:column property="opration" title="操作" sortable="false" filterable="false" viewsDenied="xls">
 				<a href="editDocPrep?id=${doc.id}&nodeId=${nodeId }">修改</a>
 				<a href="javascript:void(0)" onclick="if(confirm('确定要删除  ${doc.number}  吗？')) window.location.href='delDoc?id=${doc.id}'">删除</a>
 			</ec:column>

 		</ec:row> 		
 	</ec:table>
  </body>
</html>
