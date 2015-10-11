package filter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import doc.commons.CommonMethod;

public class LoginInterceptor extends AbstractInterceptor {	
	/**
	 * 登录拦截器
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String,Object> session = ServletActionContext.getContext().getSession();		
		if(null == session.get("accname") || "".equals(session.get("accname"))){						
			ServletActionContext.getResponse().sendRedirect(CommonMethod.getBasePath() + "login.jsp");
			return null;
		}
		return invocation.invoke();
	}
}
