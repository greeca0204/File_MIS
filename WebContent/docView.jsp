<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文档信息查看</title>
  </head>
  
  <body>
    <table align="center" style="font-size:14px;">
    
    	<s:label label="文档编号" name="number"></s:label>
    	<s:label label="文档名称" name="name"></s:label>
    	<s:label label="关键字" name="keywords"></s:label>
    	<tr>
    		<td class="tdLabel"><label for="doc_time" class="label">文档时间:</label></td>
   			<td><label id="doc_time"><s:date name="doc_time" format="yyyy-MM-dd"/></label></td>
    	</tr>
    	<tr>
    		<td>附件</td>
    		<td>
				<s:if test="file_id > 0">
					<s:a href="download?fid=%{file_id}">下载</s:a>
				</s:if>
				<s:else>无</s:else>
			</td>    	    	
    	</tr>
    	<s:label label="文档描述" name="description"></s:label>   	
    </table>
    <s:a href="javascript:void(0);" onclick="history.back()">返回</s:a>
  </body>
</html>
