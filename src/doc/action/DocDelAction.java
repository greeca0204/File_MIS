package doc.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.JnuDocument;
import doc.commons.Constant;
import doc.db.dao.DocumentDao;
import doc.db.dao.FileDao;

public class DocDelAction extends ActionSupport implements ServletResponseAware
{
	private int id;
	private HttpServletResponse response;
	private int nodeId;
	private int currentPage;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	//根据id获取文档对象
	public JnuDocument getDocument(int id)
	{
		DocumentDao dd = new DocumentDao();
		return dd.getDocById(id);
	}
	
	//删除附件
	public boolean delFile(int id)
	{
		FileDao fd = new FileDao();
		if(fd.delFile(id, Constant.UPLOADDIR))
		{
			return true;
		}
		return false;
	}
	

	@Override
	public String execute()
	{
		DocumentDao dd = new DocumentDao();
		int fileId = dd.getDocById(id).getFile_id();
		
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

		try {
			PrintWriter out = response.getWriter();
			if(this.delFile(fileId) && dd.delDocument(id))
			{
				out.print("<script>alert('删除文档成功！')</script>");
				out.print("<script>window.location.href='classSelected.action?nodeId=" + nodeId + "&ec_p=" + currentPage +  "'</script>");
				out.flush();
				out.close();
			}else
			{
				out.print("<script>alert('删除文档失败！')</script>");
				out.print("<script>window.location.href='classSelected.action?nodeId=" + nodeId + "&ec_p=" + currentPage +  "'</script>");
				out.flush();
				out.close();
			}				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return NONE;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;		
	}

}
