package filter;
/**
 * 本拦截器用于判断IP地址是否在允许的ip范围当中，不允许的ip将被拦截。
 * 使用方法：配置以下拦截器
 *
 * 	<interceptors>
 * 		<interceptor name="ipinterceptor" class="filter.IPInterceptor"></interceptor>
 * 		<interceptor-stack name="ipStack">
 * 		<interceptor-ref name="ipinterceptor"></interceptor-ref>
 * 		<interceptor-ref name="basicStack"></interceptor-ref>
 * 		</interceptor-stack>	
 * 	</interceptors>
 * 
 * 在需要拦截的action中加入
 * <interceptor-ref name="ipStack"></interceptor-ref>
 * 
 * by 王小强 2011-12-06
 *			
 */



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import common.ip.IPUtil;
import doc.commons.CommonMethod;

public class IPInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//获得客户端ip信息
		HttpServletRequest request = ServletActionContext.getRequest();	
		String ipaddr = IPUtil.getIpAddr(request);
		//判断是否校内IP
		boolean ipflag = IPUtil.isAllow(ipaddr);
		if(ipflag){			
			return invocation.invoke();
		}else{						
	        PrintWriter out = CommonMethod.getWriter();  
			out.print("<script>alert('您的IP[" + ipaddr + "]不允许访问本页面，请联系管理员！')</script>");			
			out.print("<script>window.close();</script>");
			out.close();
		}
		return Action.NONE;
	}

}
