package doc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import doc.commons.Constant;
import doc.db.dao.FileDownloadDao;

public class DownloadAction extends ActionSupport 
{
	private int fid;
	private String fname;
	private String downloadName;
			
	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFname() 
	{
		FileDownloadDao fdd = new FileDownloadDao();
		fname = fdd.getFileName(fid);		
		try 
		{
			fname = new String(fname.getBytes(),"ISO-8859-1");
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("获取到的附件名称为空！");
		}
		System.out.println("fileName:" + fname);
		return fname;
	}

	public void setFname(String fname) 
	{	

		this.fname = fname;
	}
	
//对下载文件进行重命名
	public String getDownloadName() 
	{
		try {
			downloadName =  new String(new FileDownloadDao().getDownloadName(fid).getBytes(),"ISO-8859-1");
//			downloadName = new String(new FileDownloadDao().getDownloadName(fid).getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		downloadName = new FileDownloadDao().getDownloadName(fid);
		System.out.println("downloadName:" + downloadName);
		return downloadName;
		
	}

	public void setDownloadName(String downloadName) 
	{

		this.downloadName = downloadName;
	}

//获取要下载的文件流
	public InputStream getInputStream() throws FileNotFoundException  
	{
		return new FileInputStream(new File(Constant.UPLOADDIR, this.getFname()));
	}
	
	@Override
	public String execute() throws Exception
	{	
		if(null != this.getFname() && !"".equals(this.getFname()) && this.getFname().length() != 0)
		{
			File file = new File(Constant.UPLOADDIR, this.getFname());
			System.out.println("downloadFile:" + file);
			if(file.exists())
			{
				return SUCCESS;
			}
			else
			{
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=UTF-8");
			    PrintWriter out = response.getWriter();
			    out.write("您要下载的文件不存在，请联系管理员！<br><br>");
			    out.write("<a href='#' onclick='history.back(-1)'>点击这里返回</a>");
			    out.close();
			    return NONE;
			}
		}		
		else
		{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.write("您下载的文件不存在，请联系管理员！<br><br>");
		    out.write("<a href='#' onclick='history.back(-1)'>点击这里返回</a>");
		    out.close();
		    return NONE;
		}
	}
	
	

}
