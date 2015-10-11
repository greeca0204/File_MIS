<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>添加文档</title>	
  </head>
  
  <body>
  	<%--日历相关设置--%>
	<script src="js/jquery.datePicker-min.js" type="text/javascript"></script>
	<script type="text/javascript">
    	$(window).ready(function(){
		$('.doc_time').datePicker({clickInput:true});
		$('.doc_time').datePicker({startDate:'1996-01-01'});
	});
	</script>
	<div align="center" style="color:red">
		<s:fielderror></s:fielderror>
	</div>
 	 

		<s:form action="addDoc" method="post" theme="simple" name="addDoc">

			<table align="center">
			<tr>
				<td>文档类别：</td>
				<td>
<%--					<s:select id="class_id" name="class_id" list="classList" listKey="id" listValue="name" value="%{nodeId}"></s:select>--%>
					<s:doubleselect doubleList="subclassMap.get(top.id)" list="classList" 
						doubleName="subclass_id" name="class_id"
						listKey="id" listValue="name"
						doubleListKey="id" doubleListValue="name">
					</s:doubleselect>
				</td>
			</tr>

				<tr>
					<td>
						文档编号：
					</td>
					<td>
						<s:textfield name="number" value="%{defaultNum}"></s:textfield>
					</td>
				</tr>

				<tr>
					<td>
						文档名称：
					</td>
					<td>
						<s:textfield name="name" size="50" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						关键字：
					</td>
					<td>
						<s:textfield name="keywords"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						产生时间：
					</td>
					<td>						
						<input type="text" name="doc_time" class="doc_time" />
						
					</td>
				</tr>
				
				<tr>
					<td>
						描述：
					</td>
					<td>
						<s:textarea name="description"></s:textarea>
					</td>
				</tr>
				
				<tr>
					<td>
						附件：
					</td>
					<td>
						<%--获取附件的文件名和文件id--%>
						<input name="filename" type="text" id="filename" size="30" />
						<a class="thickbox" href="uploadAdd.jsp?TB_iniframe=true&placeValuesBeforeTB_=savedValues&amp;TB_iframe=true&amp;height=200&amp;width=400&amp;modal=true">选择附件</a>
					</td>
				</tr>
				<tr>
					<td>
						<s:submit value="提交"></s:submit>
					</td>
					<td>
						<s:reset value="重置"></s:reset>
					</td>
				</tr>
			</table>
			<s:hidden name="file_id" id="file_id"/>
		</s:form>
		
		<div align="left">
			<s:a href="classSelected?nodeId=%{nodeId}">返回</s:a>		
		</div>		
		
  </body>
</html>
