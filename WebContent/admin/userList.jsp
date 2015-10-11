<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户列表</title>
</head> 
<body>
<div>
	<div class="tableTitle">
		用户列表&nbsp;&nbsp;
		<s:a action="addUserPre" namespace="/admin">增加用户</s:a>
	</div>	
	<div class="listTable">
    <table cellspacing="0" cellpadding="0" width="94%"> 
       	<tr>
       		<td width="5%" class="tableHeader">序号</td>
       		<td width="15%" class="tableHeader">账号</td>
       		<td width="15%" class="tableHeader">姓名</td>
       		<td width="15%" class="tableHeader">电话</td>
       		<td width="15%" class="tableHeader">部门</td> 
            <td width="15%" class="tableHeader">角色</td>
            <td width="20%" class="tableHeader">操作</td>
       	</tr>          
	    <s:iterator value="userList" status="status">				
	       	 <s:if test="#status.odd"><tr class="odd"></s:if>
	       	 <s:else><tr class="even"></s:else> 
	       	 	<td align="center"><s:property value="(curPage-1)*pageSize+#status.index+1"/> </td>
	       		<td><s:property value="accname"/></td>
	      		<td><s:property value="nickname"/></td>
	      		<td><s:property value="phone"/></td>
	      		<td><s:property value="deptname"/></td>
	      		<td><s:property value="rolename"/></td>  		
	    		<td>
	    			<s:a action="editUserPre?id=%{id}&curPage=%{curPage}" namespace="/admin">[修改]</s:a> 
	    			<s:a action="delUser?id=%{id}&curPage=%{curPage}" 
	    			onclick="return confirm('确认删除？')" namespace="/admin">[删除]</s:a>
	    		</td>
	       	</tr>          
		</s:iterator>
		</table>
		<div style="text-align: center;margin-top: 10px;">
			<!-- 显示页码 -->
			总共<s:property value="row"/>条&nbsp; 
			<s:property value="pageSize"/>条/页  
			第<s:property value="curPage"/>/<s:property value="totalPage"/>页
			<s:if test="curPage>1">
				【<s:a action="userList?curPage=1" namespace="/admin">首页</s:a>】									
				【<s:a action="userList?curPage=%{curPage-1}" namespace="/admin">上页</s:a>】
			</s:if>
			<s:else>
				【首页】【上页】		
			</s:else>
			<s:if test="curPage<totalPage"> 
				【<s:a action="userList?curPage=%{curPage+1}" namespace="/admin">下页</s:a>】					
				【<s:a action="userList?curPage=%{totalPage}" namespace="/admin">末页</s:a>】
			</s:if>	
			<s:else>
				【下页】【末页】
			</s:else> 
		</div>			          
	
	</div>
</div>		          
</body>
</html>