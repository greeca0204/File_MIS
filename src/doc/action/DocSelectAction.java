package doc.action;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.JnuDocument;
import doc.commons.Constant;

public class DocSelectAction extends ActionSupport
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private List<JnuDocument> doclist;
	
	
public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public HttpServletResponse getResponse() {
		return response;
	}


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}


	public List<JnuDocument> getDoclist() {
		return doclist;
	}


	public void setDoclist(List<JnuDocument> doclist) {
		this.doclist = doclist;
	}


@Override
public String execute() throws Exception
{
	System.out.println("start~~~");
	Collection selectedDocIds = SelectedDocsUtils.saveSelectedDocsIDs(request);
	Collection<JnuDocument> selectedDoclist = SelectedDocsUtils.getSelectedDocuments(doclist, selectedDocIds);
	request.setAttribute("doclist", selectedDoclist);
	request.getSession().removeAttribute(Constant.SELECTED_PRESIDENTS);
	
	return super.execute();
}
	

}
