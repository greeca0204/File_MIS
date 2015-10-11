package doc.action;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {
	/**	 
	 *  
	 *  退出登录
    */
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {			
		ActionContext.getContext().getSession().clear();//session清空
		//ServletActionContext.getRequest().getSession().invalidate();//session失效
		return SUCCESS;
	}	
	
}
