<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>数据导入</title>

	</head>

	<body>
		请选择你要导入的Excel文件，点击“提交”按钮：
		<br />
		<br />
		<div style="color: red">
			<s:fielderror></s:fielderror>
		</div>
		<s:form method="post" theme="simple" action="import"
			enctype="multipart/form-data" name="import">
			<table>
				<tr>
					<td>
						文件
					</td>
					<td>
						<s:file name="file" id="file" accept="application/msexcel" onchange="vidataFile();"></s:file>
					</td>
					<td>
						<s:submit value="提交"></s:submit>
					</td>
				</tr>
			</table>
		</s:form>

		<script>
		function vidataFile()
		{  
			var filepath=document.getElementById("file").value;//得到文件控件内的路径  
			if(filepath!="")
			{     
				var filetype=filepath.substr(filepath.length-3);//得到文件的类型     
				if(filetype!="xls"){//检查文件格式       
					alert("对不起,您上传的文件不是XLS格式");       
					document.getElementById("file").outerHTML = document.getElementById("file").outerHTML;//重绘控件(将控件的值清空)       
					return;     
				}else{       //提交表单     
					}  
				}
			}
		</script>

	</body>
</html>
