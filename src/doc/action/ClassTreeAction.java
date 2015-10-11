package doc.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.ClassNodes;
import doc.db.dao.TreeDao;

public class ClassTreeAction extends ActionSupport
{
	@Override
	public String execute() throws Exception
	{
		//System.out.println("classTreeAction执行开始……");
		ServletActionContext.getRequest().setCharacterEncoding("utf-8");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		TreeDao treeDao = new TreeDao();
		ArrayList<ClassNodes> list = treeDao.getNodeInfo();

		if(list!=null&&list.size()>0){
 	    	out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
 	        out.println("<nodes>");
 	    	for(int i=0;i<list.size();i++){
 	    		ClassNodes node = list.get(i);
 	    		out.println("<node nodeId='"+node.getId()+"' parentId='"+node.getParentId()+"' hrefAddress='../"+node.getHrefAddress()+"?nodeId=" + node.getId()+"'>"+node.getName()+"</node>");
 	    		//System.out.println("<node nodeId='"+node.getId()+"' parentId='"+node.getParentId()+"' hrefAddress='"+node.getHrefAddress()+"?nodeId=" + node.getId()+"'>"+node.getName()+"</node>");
 	    	}
 	    	out.println("</nodes>");
 	    }
		//System.out.println("classTreeAction执行结束……");
		return NONE;
	}

}
