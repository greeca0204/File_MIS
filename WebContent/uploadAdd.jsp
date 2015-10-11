<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="doc.db.dao.*"%>
<%@ page import="doc.beo.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">	
	function closee()
	{
		//parent.document.getElementById("filename"). = document.getElementById("fileName").Value;
		$(window.parent.document).find("#filename").val(document.getElementById("fileFileName").value);
		$(window.parent.document).find("#file_id").val(document.getElementById("fileId").value);
		//alert($(window.frames("hidden_frame").document).text());
		self.parent.tb_remove();
	}		
	</script>	
	
	<script language="javascript">
		function setName(){
			var fileName = document.getElementById("file").value;
			var fileNames=fileName.split("\\");
			fileName=fileNames[fileNames.length-1];
			fileNames=fileName.split(".");
			fileName=fileNames[0];			
			document.getElementById("name").value = fileName;
		}
	</script>
	
	<%--<script type="text/javascript">
    	$(window).ready(function(){
        	if(window.parent.document.addDoc.class_id.value == 0)
            	{alert("请选择分类！");
            	closee();}
	});
	</script>		--%>

	</head>
	<body>

		<table align="center">
			<tr>
				<td>
					<s:fielderror></s:fielderror>
					<s:actionerror/>				
				</td>
			</tr>
		</table>
		
		<s:form method="post" theme="simple" action="uploadAdd"
			enctype="multipart/form-data" name="upload">
			<%--文件上传结果提示--%>					
			<s:property value="%{result}"/>

			<table align="center">
			
				<tr>
					<td>文件</td>
					<td><s:file name="file" id="file" onchange="setName();"></s:file></td>
				</tr>
				
				<tr>
					<td>名称</td>
					<td><s:textfield id="name" name="name" size="35"></s:textfield></td>
				</tr>
								
				<tr align="center">
					<td><s:submit value="提交"></s:submit></td>
					<td>
						<%--获取已上传的文件名--%> 
						<s:hidden id="fileFileName" name="fileFileName" value="%{fileFileName}"></s:hidden>
						<%--获取已上传的文件id --%>
						<s:hidden id="fileId" value="%{id}"></s:hidden>
 						<INPUT id="close" onclick="closee();" type="submit" value="关闭">
					</td>
				</tr>
			</table>
		</s:form>
		

	</body>
</html>