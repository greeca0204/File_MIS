package doc.action;


import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.JnuDocument;
import doc.db.dao.DocumentDao;

public class DocViewAction extends ActionSupport
{
	private int id;
	private String number;
	private String name;
	private int file_id;
	private int class_id;
	private Date doc_time;
	private String description;
	private String keywords;
	private int nodeId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	public int getFile_id()
	{
		return file_id;
	}
	public void setFile_id(int file_id)
	{
		this.file_id = file_id;
	}
	public int getClass_id()
	{
		return class_id;
	}
	public void setClass_id(int class_id)
	{
		this.class_id = class_id;
	}
	public Date getDoc_time()
	{
		return doc_time;
	}
	public void setDoc_time(Date doc_time)
	{
		this.doc_time = doc_time;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getKeywords()
	{
		return keywords;
	}
	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}
	public int getNodeId()
	{
		return nodeId;
	}

	public void setNodeId(int nodeId)
	{
		this.nodeId = nodeId;
	}

	@Override
	public String execute() throws Exception
	{
		DocumentDao dd = new DocumentDao();
		JnuDocument doc = dd.getDocById(id);
		this.setName(doc.getName());
		this.setNumber(doc.getNumber());
		this.setClass_id(doc.getClass_id());
		this.setDescription(doc.getDescription());
		this.setDoc_time(doc.getDoc_time());
		this.setFile_id(doc.getFile_id());
		this.setKeywords(doc.getKeywords());
		return super.execute();
	}

}
