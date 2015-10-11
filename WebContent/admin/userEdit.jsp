<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>修改用户</title>
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var validator = $("#userform").validate({
				onkeyup : false,
				//debug : true,
				rules: {
					"user.accname": {
						required : true,
						remote : {
							url : "admin/users/checkAccname.action",
							type : "post",
							dataType : "json",
							data : {
								accname : function(){return $("#accname").val();}
							}
						}
					},
					"user.password" : {
						required : true,
						minlength : 6
					},
					"user.email" : {
						email : true
					}
					
				},
				messages: {
					"user.accname": {
						required: "用户名不能为空",
						remote : "该用户已经存在"
					},
					"user.password" : {
						required : "密码不能为空",
						minlength : "密码不能小于{0}个字符"
					},
					"user.email" : {
						email : "请输入有效的邮件地址"
					}
					
				}
			});
		});
	
	</script>	
</head> 
<body>
<div>
    <s:form id="userform" action="editUser" namespace="/admin"  theme="simple"> 
        <div class="tableTitle"><a href="javascript:history.go(-1)">返回列表</a></div>        
   		<table class="detailTable" width="600">
   			<tr>
   				<td colspan="2" class="header">修改用户信息</td>
   			</tr>		
   			<tr>
  				<td  width="20%" align="right" height="30">*账号：</td>
  				<td  width="80%" align="left" height="30">
  					<s:property value="user.accname"/>
  				</td>
  			</tr> 
  			<tr>
  				<td align="right" height="30">姓名：</td>
  				<td align="left" height="30">
  					<s:textfield name="user.nickname" value="%{user.nickname}" cssStyle="width:60%;"></s:textfield>
  				</td>
  			</tr>
  			<tr>
  				<td align="right" height="30">部门：</td>
  				<td align="left" height="30">
  					<s:select name="user.deptid" value="%{user.deptid}" list="deptList" listKey="id" listValue="name"></s:select>
  				</td>
  			</tr>
  			<tr>
  				<td align="right" height="30">邮件：</td>
  				<td align="left" height="30">
  					<s:textfield name="user.email" value="%{user.email}" cssStyle="width:60%;"></s:textfield>
  				</td>
  			</tr>
  			<tr>
  				<td align="right">电话：</td>
  				<td align="left">
  					<s:textfield name="user.phone" value="%{user.phone}" cssStyle="width:60%;"></s:textfield>
  				</td>
  			</tr>
  			<tr>
  				<td align="right" height="40">备注：</td>
  				<td align="left" height="40">						
					<s:textarea name="user.memo" value="%{user.memo}" cssStyle="width:96%;height:100px;"></s:textarea>
				</td>
  			</tr>
  			<tr>
  				<td align="right" height="30">角色：</td>
  				<td align="left" height="30">
  					<s:select name="user.roleid" value="%{user.roleid}" list="roleList" listKey="id" listValue="name"></s:select>
  				</td>
  			</tr> 													
			<tr>
				<td colspan="2" align="center" valign="middle" height="40">
					<s:hidden name="user.id" value="%{user.id}"></s:hidden>
					<s:submit value="修改 "/><s:reset value="恢复"/>
				</td>
			</tr>
		</table>
	</s:form>
	<s:form id="pwdform" action="editUserPwd" namespace="/admin"  theme="simple">
		<table class="detailTable" width="600" style="margin-top: 10px;">
   			<tr>
   				<td colspan="2" class="header">修改用户密码</td>
   			</tr>
   			<tr>
  				<td align="right" height="30">新密码：</td>
  				<td align="left" height="30">
  					<s:textfield name="password" cssStyle="width:50%;"></s:textfield>
  				</td>
  			</tr>
   			<tr>
				<td colspan="2" align="center" valign="middle" height="40">
					<s:hidden name="id" value="%{user.id}"></s:hidden>
					<s:submit value="修改 "/><s:reset value="重置"/>
				</td>
			</tr>
		</table>
   	</s:form>		
</div>
</body>
</html>