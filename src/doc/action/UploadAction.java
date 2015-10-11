package doc.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import doc.beo.FileInfo;
import doc.beo.JnuDocument;
import doc.commons.Constant;
import doc.db.dao.DocumentDao;
import doc.db.dao.FileUploadDAO;

public class UploadAction extends ActionSupport 
{
	private File file;	
	private String fileFileName;	
	private String fileContentType;	
	private String name;		
	private String result;	//上传结果
	private int id;	//上传文件成功后生成的id 
	private int doc_id;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int docId) {
		doc_id = docId;
	}

	//生成存档的文件名
	public String generateFileName(String fileName)
    {
		String formatDate = new SimpleDateFormat("yyMMddHHmmss")
                .format(new Date());
        int random = new Random().nextInt(900) + 100;
//        int position = fileName.lastIndexOf(".");
//        String extension = fileName.substring(position);
//        return formatDate + random + extension;
        return formatDate + random + name;
    }


	public String docAddUpload() 
	{
		this.setResult("附件上传成功！");	
		System.out.println("upload start");
//		System.out.println("copy file from:" + file);
//		String rename = this.generateFileName(fileFileName);
		System.out.println("copy file from:" + name);
		String rename = this.generateFileName(name);	
		File target = new File(Constant.UPLOADDIR,rename);
		System.out.println("copy file to:" + target);
		try {
			FileUtils.copyFile(file, target);
		} catch (IOException e) {
			this.setResult("附件上传失败！");
			e.printStackTrace();
			return INPUT;
		}
		
		FileUploadDAO filedao = new FileUploadDAO();		
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName(rename);		
		fileInfo.setName(name);		
		fileInfo.setSize(file.length());		
		fileInfo.setType(fileContentType);		
		
		if(filedao.addFileInfo(fileInfo))
		{
		//设置上传文件的id
		this.setId(filedao.getFileId(rename));
		System.out.println(this.id);
		DocumentDao dd = new DocumentDao();
		JnuDocument doc = dd.getDocById(doc_id);
		doc.setFile_id(id);	
		}
		else
		{
			System.out.println("添加文件信息失败！");
		}
		
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out = response.getWriter();
//		out.print(fileFileName);
		System.out.println(fileFileName);
		
		return SUCCESS;

	}
	
	public String docEditUpload() 
	{
		this.setResult("附件上传成功！");	
		System.out.println("upload start");
		System.out.println("copy file from:" + file);
		String rename = this.generateFileName(fileFileName);		
		File target = new File(Constant.UPLOADDIR,rename);
		System.out.println("copy file to:" + target);
		
		try {
			FileUtils.copyFile(file, target);
		} catch (IOException e) {
			this.setResult("附件上传失败！");
			e.printStackTrace();
			return INPUT;
		}		
		FileUploadDAO filedao = new FileUploadDAO();		
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName(rename);		
		fileInfo.setName(name);		
		fileInfo.setSize(file.length());		
		fileInfo.setType(fileContentType);		
		
		if(filedao.addFileInfo(fileInfo))
		{
		//设置上传文件的id
		this.setId(filedao.getFileId(rename));
		System.out.println(this.id);
		DocumentDao dd = new DocumentDao();
		JnuDocument doc = dd.getDocById(doc_id);
		doc.setFile_id(id);
		if(!dd.editDocument(doc))
		{
			this.setResult("附件上传成功，文档信息更新失败！");			
		}
		
		}
		else
		{
			System.out.println("添加文件信息失败！");
		}
		
		System.out.println(fileFileName);
		
		return SUCCESS;

	}
	
	
	
}
