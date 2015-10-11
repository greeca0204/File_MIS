package doc.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javassist.ClassMap;

import com.opensymphony.xwork2.ActionSupport;
import doc.beo.ClassNodes;
import doc.db.dao.ClassDao;
import doc.db.dao.DocumentDao;

public class DocAddPrepAction extends ActionSupport
{
	private int nodeId;
	private List<ClassNodes> classList;
	private String defaultNum;
	private Map<Integer, List<ClassNodes>> subclassMap;
	private int class_id;
	private int subclass_id;
	
	public int getNodeId()
	{
		return nodeId;
	}
	public void setNodeId(int nodeId)
	{
		this.nodeId = nodeId;
	}
	public List<ClassNodes> getClassList()
	{
		return classList;
	}
	public void setClassList(List<ClassNodes> classList)
	{
		this.classList = classList;
	}
	
	public String getDefaultNum() {
		DocumentDao dd = new DocumentDao();		
		return dd.getLastNumber(nodeId);
	}
	public void setDefaultNum(String defaultNum) {
		this.defaultNum = defaultNum;
	}
	
	public Map<Integer, List<ClassNodes>> getSubclassMap() {
		return subclassMap;
	}
	public void setSubclassMap(Map<Integer, List<ClassNodes>> subclassMap) {
		this.subclassMap = subclassMap;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int classId) {
		class_id = classId;
	}
	public int getSubclass_id() {
		return subclass_id;
	}
	public void setSubclass_id(int subclassId) {
		subclass_id = subclassId;
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
		if(subclassMap.containsKey(nodeId))
		{
			class_id = nodeId;
		}
		else
		{
			subclass_id = nodeId;
			class_id = cd.getParentId(nodeId);			
		}
		return super.execute();
	}

}
