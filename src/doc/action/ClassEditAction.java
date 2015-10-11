package doc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.ClassList;
import doc.beo.ClassNodes;
import doc.commons.CommonMethod;
import doc.db.dao.ClassDao;

public class ClassEditAction extends ActionSupport {
	private ClassNodes classNodes;
	private List<ClassNodes> classList;
	private Map<Integer, List<ClassNodes>> subclassMap;
	private int class_id;
	private int subclass_id = 0;

	public ClassNodes getClassNodes() {
		return classNodes;
	}

	public void setClassNodes(ClassNodes classNodes) {
		this.classNodes = classNodes;
	}

	public List<ClassNodes> getClassList() {
		classList = ClassList.getList();
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

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public int getSubclass_id() {
		return subclass_id;
	}

	public void setSubclass_id(int subclass_id) {
		this.subclass_id = subclass_id;
	}

	//文档管理
	public String classAdmin() {
		ClassDao cd = new ClassDao();
		subclassMap = new LinkedHashMap<Integer, List<ClassNodes>>();
		classList = cd.getSubclassNodes(1);
		for(ClassNodes node : classList)
		{
			subclassMap.put(node.getId(), cd.getSubclassNodes(node.getId()));
		}
		return SUCCESS;
	}

	// 添加文件分类
	public String addClassNodes() {
		ClassDao cd = new ClassDao();

		if (cd.addClass(this.getClassNodes())) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	//编辑分类
	public String editClassNodes() throws IOException {
		ClassDao cd = new ClassDao();
		if(subclass_id == 0){
			classNodes.setId(class_id);
		}else{
			classNodes.setId(subclass_id);
		}
		PrintWriter out = CommonMethod.getWriter();
		if(cd.editClass(classNodes)){			
			out.print("<script>alert('修改成功！')</script>");
			out.print("<script>window.location='" + CommonMethod.getBasePath() + "admin/classAdmin.action'</script>");
			out.close();
		}else{
			out.print("<script>alert('修改失败！')</script>");
			out.print("<script>history.go(-1);</script>");
			out.close();
		}
		return NONE;
	}

	// 删除文件分类
	public String delClassNodes() throws IOException {
		ClassDao cd = new ClassDao();
		int id;
		if(subclass_id == 0){
			id = class_id;
		}else{
			id = subclass_id;
		}
		PrintWriter out = CommonMethod.getWriter();
		if (cd.delClass(id)) {
			out.print("<script>alert('删除成功！')</script>");
			out.print("<script>window.location='" + CommonMethod.getBasePath() + "admin/classAdmin.action'</script>");
			out.close();
		} else {
			out.print("<script>alert('删除失败！')</script>");
			out.print("<script>history.go(-1);</script>");
			out.close();
		}
		return NONE;
	}

}
