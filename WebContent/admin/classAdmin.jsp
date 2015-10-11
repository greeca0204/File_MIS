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
    
    <title>分档分类管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body>

	<fieldset style="width: 600px; padding:0px 20px 10px; line-height:200%;margin-left: 30px;">
		<legend><b>增加分类</b></legend>
	  	<s:form name="addClass" action="addClass" namespace="/admin" theme="simple">
	  		<p>
	  		<label>所属分类：</label>
	  		<s:select name="classNodes.parentId" list="classList" listKey="id" listValue="name" headerKey="1" headerValue="一级分类" cssStyle="width:280px;"></s:select>
	  		</p>
	  		<p>
	  		<label>分类名称：</label>
	  		<s:textfield name="classNodes.name" size="40"></s:textfield>&nbsp;<s:submit value="提交" cssStyle="width:60px;height:26px;"></s:submit>
	  		</p>
	  	</s:form>
    </fieldset>
    <fieldset style="width: 600px; padding: 0px 20px 10px; line-height:200%;margin-left: 30px; margin-top: 30px;">
		<legend><b>修改分类</b></legend>
	  	<s:form id="editClass" name="editClass" action="editClass" namespace="/admin" method="post" theme="simple">	  		
	  		<div class="nobr">
		  		<label>选择分类：</label>
		  		<s:doubleselect doubleList="subclassMap.get(top.id)" list="classList" 
							doubleName="subclass_id" name="class_id"
							listKey="id" listValue="name"
							doubleListKey="id" doubleListValue="name"
							doubleHeaderKey="0" doubleHeaderValue="修改上级分类" cssStyle="width:280px;">
				</s:doubleselect>
	  		</div>
	  		<p>
	  		<label>新名称：</label>
	  		<s:textfield name="classNodes.name" size="40"></s:textfield>&nbsp;<s:submit value="提交" cssStyle="width:60px;height:26px;"></s:submit>
	  		</p>
	  	</s:form>
    </fieldset>
    
    <fieldset style="width: 600px; padding: 0px 20px 10px; line-height:200%;margin-left: 30px; margin-top: 30px;">
		<legend><b>删除分类</b></legend>
	  	<s:form name="delClass" action="delClass" namespace="/admin" method="post" theme="simple">
	  		<div class="nobr">
		  		<label>选择分类：</label>
		  		<s:doubleselect doubleList="subclassMap.get(id)" list="classList" 
							doubleName="subclass_id" name="class_id"
							listKey="id" listValue="name"
							doubleListKey="id" doubleListValue="name"
							doubleHeaderKey="0" doubleHeaderValue="修改上级分类" cssStyle="width:250px;">
				</s:doubleselect>
				&nbsp;<s:submit value="提交" cssStyle="width:60px;height:26px;"></s:submit>
	  		</div>
	  	</s:form>
    </fieldset>
  </body>
</html>
