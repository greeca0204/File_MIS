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
    
    <title>文档列表</title>

  </head>
  <body>
  
	
  	<s:form action="searchDoc" theme="simple">
		<s:textfield name="key" size="40"></s:textfield>
		<s:select name="searchType" list="#{1:'文档名',2:'文档编号',3:'关键字',4:'文档描述'}"></s:select>
		<s:submit value="检索"></s:submit>
	</s:form>
	<br/>

  	<s:property value="%{nodeName}"/>:
  	
<%--  	<form id="docForm" action="docSelect">--%>
 
 	<ec:table items="doclist" var="doc" rowsDisplayed="20" 
 	imagePath="images/extremetable/compact/*.gif"
 	action="classSelected" width="95%" filterRowsCallback="limit" 
 	retrieveRowsCallback="limit"
 	sortRowsCallback="limit"
 	view="doc.extremetable.view.DocHtmlView"
 	method="get" 
 	>
 	
 	<ec:exportXls fileName="export.xls" tooltip="导出文档信息到EXCEL文件">导出</ec:exportXls>
 		<ec:row><%--
 		    <ec:column 
                 alias="checkbox"
                 title="选择" 
                 style="width:20px" 
                 filterable="false" 
                 sortable="false"
                 cell="doc.action.DocSelectedCell" 
                 width="30"/>
 			--%><ec:column property="number" title="文档编号" ></ec:column>

 			<ec:column property="name" title="文档名称">
 				<a href="viewDoc?id=${doc.id}&nodeId=${nodeId }">${doc.name}</a>
 			</ec:column>
 			<ec:column property="doc_time" cell="date" parse="yyyy-MM-dd" format="yyyy-MM-dd" title="文档时间">
 			</ec:column>
 			<ec:column property="file_id" filterable="false" title="附件" width="40">
 				<c:if test="${doc.file_id != 0 }">
 					<a href="download?fid=${doc.file_id}">下载</a>
 				</c:if>
 				  <c:if test="${doc.file_id == 0 }">
 				  	<a class="thickbox" href="uploadEdit.jsp?doc_id=${doc.id }&TB_iniframe=true&placeValuesBeforeTB_=savedValues&amp;TB_iframe=true&amp;height=200&amp;width=400&amp;modal=true">上传</a>
 				  </c:if>
 			</ec:column>
 			<ec:column property="opration" title="操作" sortable="false" filterable="false" viewsDenied="xls">
 				<a href="viewDoc?id=${doc.id}&nodeId=${nodeId }">查看</a>
 				<s:if test="#session.roleid == 1">
 					<a href="editDocPrep.action?id=${doc.id}&nodeId=${nodeId }&currentPage=${currentPage}">修改</a>
 					<a href="javascript:void(0)" onclick="if(confirm('确定要删除  ${doc.number}  吗？')) window.location.href='delDoc.action?id=${doc.id}&nodeId=${nodeId }&currentPage=${currentPage }'">删除</a>
 				</s:if>
 			</ec:column>
 		</ec:row> 	
 	</ec:table>	
  </body>
</html>
