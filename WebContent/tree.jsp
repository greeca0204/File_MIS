<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>树形结构</title>
		<script type="text/javascript" src="js/dtree.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<link rel="stylesheet" href="styles/dtree.css" type="text/css"></link>
	
				<script type="text/javascript">
				 // alert(nodeId+"/"+parentId+"/"+nodeName+"/"+hrefAddress);
					tree = new dTree('tree');//创建一个对象.
								$.ajax({ 
										url:'classTreeAction', 
										type:'post', //数据发送方式 
										dataType:'xml', //接受数据格式 
										error:function(json){
				   							     alert("not valid!");
											  },
										async: false ,
										success: function(xml){
				 								    $(xml).find("node").each(function(){ 
														  var nodeId=$(this).attr("nodeId");  
					  									  var parentId=$(this).attr("parentId");  
														  var hrefAddress=$(this).attr("hrefAddress");  
														  var nodeName=$(this).text(); 
														  tree.add(nodeId,parentId,nodeName,hrefAddress,"","doclist","","",false);
								                     });
								                }
								  });
				      document.write(tree);
			    </script>
	</head>
	<body>
	<br/>
	<br/>
	</body>
</html>
