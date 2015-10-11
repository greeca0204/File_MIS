package doc.action;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.ClassNodes;
import doc.beo.FileInfo;
import doc.beo.JnuDocument;
import doc.db.dao.ClassDao;
import doc.db.dao.DocumentDao;
import doc.db.dao.FileDao;
import doc.db.dao.FileDownloadDao;

public class DocEditPrepAction extends ActionSupport
{
	private int id;
	private JnuDocument doc;
	private List<ClassNodes> classList;
	private int nodeId;
	private String fileName;
	private Map<Integer, List<ClassNodes>> subclassMap;
	private int currentPage;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}	
	public JnuDocument getDoc()
	{
		return doc;
	}
	public void setDoc(JnuDocument doc)
	{
		this.doc = doc;
	}
	public List<ClassNodes> getClassList()
	{
		return classList;
	}
	public void setClassList(List<ClassNodes> classList)
	{
		this.classList = classList;
	}
	
	public int getNodeId()
	{
		return nodeId;
	}
	public void setNodeId(int nodeId)
	{
		this.nodeId = nodeId;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
		ClassDao cd = new ClassDao();
		subclassMap = new LinkedHashMap<Integer, List<ClassNodes>>();
		classList = cd.getSubclassNodes(1);
		for(ClassNodes node : classList)
		{
			subclassMap.put(node.getId(), cd.getSubclassNodes(node.getId()));
		}
		
		DocumentDao dd = new DocumentDao();
		this.setDoc(dd.getDocById(id));
		int file_id = doc.getFile_id();
		if(file_id != 0)
		{
			FileDownloadDao fd = new FileDownloadDao();
			try {
				fileName = fd.getDownloadName(doc.getFile_id());
			} catch (Exception e) {
				doc.setFile_id(0);
				System.out.println("附件信息不存在");
			}
		}
		
		return super.execute();
	}
	
	

}
