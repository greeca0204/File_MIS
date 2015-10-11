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
    <title>修改文档</title>	
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
		<s:form action="editDoc" method="post" theme="simple" name="editDoc">

			<table align="center">
			<tr>
				<td>文档类别：</td>
				<td>
<%--					<s:select id="class_id" name="class_id" list="classList" listKey="id" listValue="name" value="%{doc.class_id}"--%>
<%--					headerKey="0" headerValue="--请选择分类--"></s:select>--%>
					<s:doubleselect doubleList="subclassMap.get(top.id)" list="classList" 
						doubleName="subclass_id" name="class_id"
						listKey="id" listValue="name"
						doubleListKey="id" doubleListValue="name"
						value="%{doc.class_id}" doubleValue="%{doc.subclass_id}">
					</s:doubleselect>
				</td>
			</tr>

				<tr>
					<td>
						文档编号
					</td>
					<td>
						<s:textfield name="number" value="%{doc.number}"></s:textfield>
				<%--记录文档id--%>
					<s:textfield cssStyle="display:none" name="id" value="%{doc.id}"></s:textfield>

					</td>
				</tr>

				<tr>
					<td>
						文档名称：
					</td>
					<td>
						<s:textfield name="name" value="%{doc.name}" size="50"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						关键字：
					</td>
					<td>
						<s:textfield name="keywords" value="%{doc.keywords}"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						产生时间：
					</td>
					<td>
						<s:textfield name="doc_time" cssClass="doc_time">
							<s:param name="value">
								<s:date name="doc.doc_time" format="yyyy-MM-dd"/>
							</s:param>
						</s:textfield>	

						
					</td>
				</tr>
				
				<tr>
					<td>
						描述：
					</td>
					<td>
						<s:textarea name="description" value="%{doc.description}"></s:textarea>
					</td>
				</tr>
				
				<tr>
					<td>
						附件：
					</td>
					<td>
					<input name="file_id" style="display:none" type="text" id="file_id" value="<s:text name="%{doc.file_id}"></s:text>">
					<s:if test="doc.file_id == 0">
						<%--获取附件的文件名和文件id--%>
						<input name="filename" type="text" id="filename" size="30" />					
						
						<a class="thickbox" href="uploadEdit.jsp?doc_id=${doc.id}&TB_iniframe=true
						&placeValuesBeforeTB_=savedValues&amp;TB_iframe=true&amp;
						height=200&amp;width=400&amp;modal=true">选择附件</a>
					</s:if>
					<s:else>
						<s:property value="fileName"/> &nbsp;&nbsp;	
						<s:a href="delFile?id=%{doc.file_id}&doc_id=%{doc.id}
						&nodeId=%{nodeId}&currentPage=%{currentPage}">删除</s:a>				
					</s:else>


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
			<s:hidden id="currentPage" name="currentPage" value="%{currentPage}"></s:hidden>
		</s:form>
		<br/>
		<div align="left">
			<s:a href="classSelected?nodeId=%{nodeId}&ec_p=%{currentPage}">返回</s:a>		
		</div>

  </body>
</html>
