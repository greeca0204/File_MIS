package doc.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.ClassNodes;
import doc.beo.JnuDocument;
import doc.db.dao.ClassDao;
import doc.db.dao.DocumentDao;

public class DocEditAction extends ActionSupport implements ServletResponseAware
{
	private int id;
	private String number;
	private String name;
	private int file_id;
	private int class_id;
	private int subclass_id;
	private Date doc_time;
	private String description;
	private String keywords;
	private List<ClassNodes> classList;
	private Map<Integer, List<ClassNodes>> subclassMap;
		
	private HttpServletResponse response;
	private int currentPage;
	
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
	
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public int getSubclass_id() {
		return subclass_id;
	}
	public void setSubclass_id(int subclassId) {
		subclass_id = subclassId;
	}	
	public List<ClassNodes> getClassList() {
		return classList;
	}
	public void setClassList(List<ClassNodes> classList) {
		this.classList = classList;
	}
	public Map<Integer, List<ClassNodes>> getSubclassMap() {
		return subclassMap;
	}
	public void setSubclassMap(Map<Integer, List<ClassNodes>> subclassMap) {
		this.subclassMap = subclassMap;
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
		JnuDocument doc = new JnuDocument();
		DocumentDao dd = new DocumentDao();

		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		doc.setId(id);
		doc.setNumber(number);
		doc.setName(name);
		doc.setKeywords(keywords);
		doc.setClass_id(class_id);
		doc.setFile_id(file_id);
		System.out.println("附件id更新为：" + file_id);
		doc.setDoc_time(doc_time);
		doc.setDescription(description);
		doc.setSubclass_id(subclass_id);
		
		if(dd.editDocument(doc))
		{
			out.print("<script>alert('文档修改成功！')</script>");
			out.print("<script>window.location.href='classSelected.action?nodeId=" + class_id + "&ec_p=" + currentPage + "'</script>");
			out.flush();
			out.close();
		}
		else
		{
			out.print("<script>alert('文档修改失败！')</script>");
			out.print("<script>window.location.href='classSelected.action?nodeId=" + class_id + "&ec_p=" + currentPage + "'</script>");
			out.flush();
			out.close();
		}
		
		ServletActionContext.getRequest().setAttribute("file_id", file_id);

		return super.execute();
	}
	
	@Override
	public void validate() 
	{
		ClassDao cd = new ClassDao();
		subclassMap = new LinkedHashMap<Integer, List<ClassNodes>>();
		classList = cd.getSubclassNodes(1);
		for(ClassNodes node : classList)
		{
			subclassMap.put(node.getId(), cd.getSubclassNodes(node.getId()));
		}
		super.validate();
	}
	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
		
	}
	

}
