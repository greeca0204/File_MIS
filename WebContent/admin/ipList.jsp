<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>IP列表</title>
</head> 
<body>
<div>
	<div class="tableTitle">
		允许IP列表&nbsp;&nbsp;
		<s:a action="addIpPre" namespace="/admin">增加IP段</s:a>
	</div>	
	<div class="listTable">
    <table cellspacing="0" cellpadding="0" width="90%"> 
       	<tr>
       		<td width="5%" class="tableHeader"><strong>编号</strong></td>
       		<td width="40%" class="tableHeader"><strong>起始IP</strong></td>
       		<td width="40%" class="tableHeader">终止IP</td> 
            <td width="15%" class="tableHeader"><strong>操作</strong></td>
       	</tr>          
	    <s:iterator value="ipList" status="status">				
	       	 <s:if test="#status.odd"><tr class="odd"></s:if>
	       	 <s:else><tr class="even"></s:else> 
	       	 	<td align="center"><s:property value="(curPage-1)*pageSize+#status.index+1"/> </td>
	       		<td class="datatd">
	       			<s:property value="startip"/>
	      		</td>
	      		<td><s:property value="endip"/></td>   		
	    		<td class="datatd center">
	    			<s:a action="editIpPre?id=%{id}&curPage=%{curPage}" namespace="/admin">[修改]</s:a> 
	    			<s:a action="delIp?id=%{id}&curPage=%{curPage}" 
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
				【<s:a action="ipList?curPage=1" namespace="/admin">首页</s:a>】									
				【<s:a action="ipList?curPage=%{curPage-1}" namespace="/admin">上页</s:a>】
			</s:if>
			<s:else>
				【首页】【上页】		
			</s:else>
			<s:if test="curPage<totalPage"> 
				【<s:a action="ipList?curPage=%{curPage+1}" namespace="/admin">下页</s:a>】					
				【<s:a action="ipList?curPage=%{totalPage}" namespace="/admin">末页</s:a>】
			</s:if>	
			<s:else>
				【下页】【末页】
			</s:else> 
		</div>			          
	
	</div>
</div>		          
</body>
</html>