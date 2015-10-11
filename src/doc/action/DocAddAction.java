package doc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.w3c.dom.views.AbstractView;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.ClassNodes;
import doc.beo.JnuDocument;
import doc.db.dao.ClassDao;
import doc.db.dao.DocumentDao;

public class DocAddAction extends ActionSupport
{
	private String number;
	private String name;
	private int file_id;
	private int class_id;
	private int subclass_id;
	private Date doc_time;
	private String description;
	private String keywords;
	private String tip;
	private List<ClassNodes> classList;
	private Map<Integer, List<ClassNodes>> subclassMap;
	
	public String getKeywords()
	{
		return keywords;
	}
	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public Date getDoc_time() {
		return doc_time;
	}
	public void setDoc_time(Date doc_time) {
		this.doc_time = doc_time;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	public List<ClassNodes> getClassList() {
		return classList;
	}
	public void setClassList(List<ClassNodes> classList) {
		this.classList = classList;
	}
	
	public int getSubclass_id() {
		return subclass_id;
	}
	public void setSubclass_id(int subclassId) {
		subclass_id = subclassId;
	}
	public Map<Integer, List<ClassNodes>> getSubclassMap() {
		return subclassMap;
	}
	public void setSubclassMap(Map<Integer, List<ClassNodes>> subclassMap) {
		this.subclassMap = subclassMap;
	}
	@Override
	public String execute() throws Exception
	{
		JnuDocument doc = new JnuDocument();
		DocumentDao docDao = new DocumentDao();	
	
		doc.setName(name);
		doc.setNumber(number);
		doc.setDoc_time(doc_time);
		doc.setDescription(description);
		doc.setKeywords(keywords);
		if(file_id != 0)
		{
			doc.setFile_id(file_id);
		}
		doc.setClass_id(class_id);
		doc.setSubclass_id(subclass_id);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
        PrintWriter out = response.getWriter();
		if(docDao.addDocument(doc))
		{
			out.print("<script>alert('添加文档成功！')</script>");				
			out.print("<script>window.location.href='addDocPrep.action?nodeId=" + class_id + "'</script>");
			out.close();
			System.out.println("添加文档成功！");		
		}
		else
		{
			out.print("<script>alert('添加文档失败！')</script>");
			out.close();
			System.out.println("添加文档失败！");
		}
		return NONE;
	}

	
	@Override
	public void validate() {
		ClassDao cd = new ClassDao();
		subclassMap = new LinkedHashMap<Integer, List<ClassNodes>>();
		classList = cd.getSubclassNodes(1);
		for(ClassNodes node : classList)
		{
			subclassMap.put(node.getId(), cd.getSubclassNodes(node.getId()));
		}
		
		super.validate();
	}
	

}
