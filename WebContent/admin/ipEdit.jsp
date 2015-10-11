<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑IP段</title>
	
</head> 
<body>
<div>
   <s:form action="editIp" namespace="/admin"  theme="simple"> 
        <div class="tableTitle"><a href="javascript:history.go(-1)">返回列表</a></div>         
   		<table class="detailTable" width="500">
   			<tr>
   				<td colspan="2" class="header">编辑IP段</td>
   			</tr>		
   			<tr>
  				<td  width="20%" align="right" height="40"><strong>起始IP：</strong></td>
  				<td  width="80%" align="left" height="40"><s:textfield id="title" name="ip.startip" value="%{ip.startip}" cssStyle="width:90%;"></s:textfield></td>
  			</tr> 
  			<tr>
  				<td align="right" height="40"><strong>终止IP：</strong></td>
  				<td align="left" height="40">
  					<s:textfield name="ip.endip" value="%{ip.endip}"  cssStyle="width:90%;"></s:textfield>
  				</td>
  			</tr>
  			<tr>
  				<td align="right"><strong>备注：</strong></td>
  				<td align="left">						
					<s:textarea name="ip.remark" cssClass="longabstract" value="%{ip.remark}" cssStyle="width:90%;height:100px;"></s:textarea>
				</td>
  			</tr> 													
			<tr>
				<td colspan="2" align="center" valign="middle" height="40">
				<s:hidden name="ip.id" value="%{ip.id}"></s:hidden>
				<s:hidden name="curPage" value="%{curPage}"></s:hidden>
				<s:submit value="修改"/><s:reset value="重置"/>
				</td>
			</tr>
		</table>
	</s:form>
</div>
</body>
</html>