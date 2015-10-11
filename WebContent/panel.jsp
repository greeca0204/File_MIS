<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><decorator:title></decorator:title></title>
		<script type="text/javascript" src="<%=path %>/js/dtree.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<link rel="stylesheet" href="<%=path %>/styles/layout.css" type="text/css">
		<link rel="stylesheet" href="<%=path %>/styles/dtree.css" type="text/css"></link>
		<link rel="stylesheet" type="text/css" href="<%=path %>/styles/extremecomponents.css">
		<!-- 引入thickbox相关脚本 -->
		<script type="text/javascript" src="<%=path %>/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"  src="<%=path %>/js/thickbox.js"></script> 
		<link rel="stylesheet" href="<%=path %>/styles/thickbox.css" type="text/css" media="screen" />		
		<%--日历样式--%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/styles/datePicker.css" />
		<%--管理员样式--%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/styles/admin.css" />		
		<decorator:head></decorator:head>
		<style type="text/css">
			.nobr br {display: none;}
		</style>
	</head>
	<body>
		<div id="top">
  			<img src="<%=path %>/images/logo.gif" /><br/>
  			<img alt="" src="<%=path %>/images/blue.png" width="100%" height="6">
  		</div>
  		<s:if test="#session.roleid == 1">	
  		<div id="left">
  			<s:if test="#session.accname != null">			
  			<div style="margin-bottom: 20px;">
  				欢迎,
  				<s:if test="#session.nickname == null || #session.nickname == ''">
					<s:property value="#session.username"/>
				</s:if>
				<s:else>
					<s:property value="#session.nickname"/>
				</s:else>
				<s:a action="logout" namespace="/" title="退出登录">[退出]</s:a>
  			</div>	
  			<script type="text/javascript">
				 // alert(nodeId+"/"+parentId+"/"+nodeName+"/"+hrefAddress);
				tree = new dTree('tree');//创建一个对象.
					$.ajax({ 
						url:'classTreeAction', 
						type:'post', //数据发送方式 
						dataType:'xml', //接受数据格式222
						error:function(json){
					   		//alert("not valid!");
							},
							async: false ,
							success: function(xml){
					 			$(xml).find("node").each(function(){ 
									var nodeId=$(this).attr("nodeId");  
						  			var parentId=$(this).attr("parentId");  
									var hrefAddress= "doc/" + $(this).attr("hrefAddress");  
									var nodeName=$(this).text(); 
										tree.add(nodeId,parentId,nodeName,hrefAddress,"",
												"","","",false);
									                     });
									                }
							});
				document.write(tree);
			</script>
			<s:if test="#session.roleid == 1">
				<div style="font-size: 12px;margin-top: 10px;padding-left: 10px;">
					<s:a action="classAdmin" namespace="/admin">分类管理</s:a>
				</div>
				<div style="font-size: 12px;margin-top: 10px;padding-left: 10px;">
					<s:a action="ipList" namespace="/admin">IP访问限制管理</s:a>
				</div>
				<div style="font-size: 12px;margin-top: 10px;padding-left: 10px;">
					<s:a action="userList" namespace="/admin">用户管理</s:a>
				</div>
				<div style="font-size: 12px;margin-top: 10px;padding-left: 10px;">
					<s:a href="addDocPrep?nodeId=%{nodeId}">添加文档</s:a>
				</div>
				<div style="font-size: 12px;margin-top: 10px;padding-left: 10px;">
					<s:a href="import.jsp">数据导入</s:a>
				</div>
			</s:if>				
		</s:if>
  		</div>
  		<div id="right">
  			
  			<decorator:body></decorator:body>
  		</div>


		</s:if>
		
		<s:if test="#session.roleid == 2">
			<p>demo</p>
		</s:if>
	
	
	
	
	
	
	</body>
		
	
	
</html>