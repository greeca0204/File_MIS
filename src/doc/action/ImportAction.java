package doc.action;

import java.io.File;

import org.apache.struts2.components.FieldError;

import com.opensymphony.xwork2.ActionSupport;

import doc.db.dao.ExcelDao;

public class ImportAction extends ActionSupport
{
	private File file;
	
	public File getFile()
	{
		return file;
	}
	public void setFile(File file)
	{
		this.file = file;
	}
	@Override
	public String execute() throws Exception
	{
		ExcelDao ed = new ExcelDao();
//		ed.getDocData(file);
		if(!ed.importDoc(file))
		{
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Override
	public void validate() {
		if(null == file || "".equals(file))
		{
			this.addFieldError("file", "请选择一个Excel文件！");
		}
		super.validate();
	}
	

}
