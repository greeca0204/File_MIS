<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>增加IP段</title>	
</head> 
<body>
<div>
    <s:form action="addIp" namespace="/admin"  theme="simple"> 
        <div class="tableTitle"><a href="javascript:history.go(-1)">返回列表</a></div>        
   		<table class="detailTable" width="500">
   			<tr>
   				<td colspan="2" class="header">增加IP段</td>
   			</tr>		
   			<tr>
  				<td  width="20%" align="right" height="40"><strong>起始IP：</strong></td>
  				<td  width="80%" align="left" height="40"><s:textfield name="ip.startip" cssStyle="width:90%;"></s:textfield></td>
  			</tr> 
  			<tr>
  				<td align="right" height="40"><strong>终止IP：</strong></td>
  				<td align="left" height="40">
  					<s:textfield name="ip.endip" cssStyle="width:90%;"></s:textfield>
  				</td>
  			</tr>
  			<tr>
  				<td align="right" height="40"><strong>附加说明：</strong></td>
  				<td align="left" height="40">						
					<s:textarea name="ip.remark" cssStyle="width:90%;height:100px;"></s:textarea>
				</td>
  			</tr> 													
			<tr>
				<td colspan="2" align="center" valign="middle" height="40">
				<s:submit value="增加 "/><s:reset value="重置"/>
				</td>
			</tr>
		</table>
	</s:form>
</div>
</body>
</html>