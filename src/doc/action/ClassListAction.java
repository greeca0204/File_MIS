package doc.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.ClassList;
import doc.beo.ClassNodes;

public class ClassListAction extends ActionSupport
{
	private List<ClassNodes> classList;

	public List<ClassNodes> getClassList() {
		return classList;
	}

	public void setClassList(List<ClassNodes> classlist) {
		this.classList = classlist;
	}
	@Override
	public String execute() throws Exception {
		classList = new ClassList().getList();
		return NONE;
	}

}
