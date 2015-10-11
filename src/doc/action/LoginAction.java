package doc.action;

import java.sql.SQLException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import common.ip.IPUtil;

import doc.beo.Sys_users;
import doc.db.dao.Sys_usersDAO;

public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		Sys_users user = new Sys_users();
		Sys_usersDAO udao = new Sys_usersDAO();
		
		//判断用户输入的用户名是否为空
		if (isInvalid(account))  {	
			addFieldError("account", "用户名不能为空");
			return INPUT;
		}	
		//判断用户输入的密码是否为空
		if (isInvalid(password))  {	
			addFieldError("password", "密码不能为空");
			return INPUT;
		}		
		account = account.trim();
		user = udao.checkUser(account,password);
		if(user != null){
			if(null != user.getAccname()){ //true 验证通过			
				saveSession(user,password);								
				//获得登录用户的IP地址
		        String ipAddr = IPUtil.getIpAddr(ServletActionContext.getRequest()); 			                
		        udao.updateTimeAndIp(user.getId(), ipAddr);	
				//System.out.println("login success"); 			
				
			}else{//false  验证不通过
				addFieldError("errormsg", "用户名或密码错误");	
				return INPUT;
			}
		}else{
			addFieldError("errormsg", "用户名或密码错误");	
			return INPUT;
		}
		return SUCCESS;
					
	}
	
	public static void saveSession(Sys_users user, String password){		
		//保存session
		ActionContext.getContext().getSession().put("accname", user.getAccname());
		ActionContext.getContext().getSession().put("userid", user.getId());
		ActionContext.getContext().getSession().put("nickname", user.getNickname());
		ActionContext.getContext().getSession().put("email", user.getEmail());
		ActionContext.getContext().getSession().put("roleid", user.getRoleid());
	}
	
	
	/**
	 * 用户输入数据是否为空	 
	 *@param   value,用户输入的数据 	
	 *@return  boolean，是否为空
	 *@exception 异常类名 说明
	 */	
	private boolean isInvalid(String value) {
		return (value == null || value.trim().length() == 0);
	}	
	
	private String msg = "";//"您好";	
	private String account;	
	private String password;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}		
}
