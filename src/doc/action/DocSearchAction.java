package doc.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.ClassNodes;
import doc.beo.JnuDocument;
import doc.db.dao.ClassDao;
import doc.db.dao.DocSearchDao;

public class DocSearchAction extends ActionSupport
{
	private String key;
	private String searchType;
	private List<ClassNodes> classNodes;
	private List<JnuDocument> doclist;

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}
	
	
	public List<JnuDocument> getDoclist()
	{
		return doclist;
	}

	public void setDoclist(List<JnuDocument> doclist)
	{
		this.doclist = doclist;
	}
	

	public String getSearchType()
	{
		return searchType;
	}

	public void setSearchType(String searchType)
	{
		this.searchType = searchType;
	}
	
	public List<ClassNodes> getClassNodes()
	{
		ClassDao cd = new ClassDao();
		return cd.getAllClassNodes();
	}

	public void setClassNodes(List<ClassNodes> classNodes)
	{
		this.classNodes = classNodes;
	}
	
	public String getClassName(int class_id)
	{
		ClassDao cd = new ClassDao();
		return cd.getClassName(class_id);
	}
	

	@Override
	public String execute() throws Exception
	{
		DocSearchDao searchDao = new DocSearchDao();
		switch (Integer.parseInt(searchType))
		{
		case 1:doclist = searchDao.DocNameSearch(key);break;
		case 2:doclist = searchDao.NumberSearch(key);break;
		case 3:doclist = searchDao.KeywordsSearch(key);break;
		case 4:doclist = searchDao.KeywordsSearch(key);break;		
		default:
			break;
		}
			


		return super.execute();
	}

}
