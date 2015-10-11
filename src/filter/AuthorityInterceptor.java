package filter;

import java.io.PrintWriter;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import doc.commons.CommonMethod;

public class AuthorityInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Map<String,Object> session = ServletActionContext.getContext().getSession();
		int roleid = (Integer) session.get("roleid");
		if(1 != roleid){
			PrintWriter out = CommonMethod.getWriter();
			out.print("<script>alert('您没有操作权限！')</script>");
			out.print("<script>history.go(-1);</script>");
			out.close();
		}else{
			return invocation.invoke();
		}
		return null;		
	}
}