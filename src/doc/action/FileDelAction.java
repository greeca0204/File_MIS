package doc.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.JnuDocument;
import doc.commons.Constant;
import doc.db.dao.DocumentDao;
import doc.db.dao.FileDao;

public class FileDelAction extends ActionSupport implements ServletResponseAware
{
	private int id;
	private int doc_id;
	private int nodeId;
	private HttpServletResponse response;
	private int currentPage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int docId) {
		doc_id = docId;
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

	@Override
	public String execute() throws Exception 
	{
		FileDao fDao = new FileDao();
		DocumentDao dd = new DocumentDao();
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JnuDocument doc = dd.getDocById(doc_id);
		doc.setFile_id(0);
		if(fDao.delFile(id, Constant.UPLOADDIR) && dd.editDocument(doc))
		{
			out.print("<script>alert('删除附件成功！')</script>");
			out.print("<script>window.location.href='editDocPrep?id=" + doc_id + "&nodeId=" + nodeId + "&currentPage=" + currentPage + "'</script>");
			out.flush();
			out.close();
		}
		else
		{
			out.print("<script>alert('删除附件失败！')</script>");
			out.print("<script>window.location.href='editDocPrep?id=" + doc_id + "&nodeId=" + nodeId + "&currentPage=" + currentPage +  "'</script>");
			out.flush();
			out.close();			
		}
		return super.execute();
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	

}
