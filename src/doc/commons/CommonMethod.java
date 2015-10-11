package doc.commons;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class CommonMethod {
	
	/**
	 * 过滤字符串首尾的空格
	 *@param   str 	
	 *@return  String
	 *@exception 异常类名 说明
	 */	
	public static String  trimstr(String s){
		 int j=0,k=0,i=0;
		 int slength = s.length();
		 //参数是空
		 if(slength==0 || null==s || s.equals("")){
			 return "";
		 }
		 char[] stra=new char[slength];
		 s.getChars(0,slength,stra,0);
		 for(i=0;i<slength;i++) {
			 if(stra[i]==' '||stra[i]==' ') {
				 j=i+1;
			 } else {
				 break;
			 }
		 }
		 for(i=slength-1;i+1>0;i--){
			 if(stra[i]==' '||stra[i]==' '){
				 k=i;
		     } else{
			     break;
		     }
		 }
		 //参数是空格，防止new String(stra,j,k-j)out of bound 异常
		 if(k-j<0){
			 return "";
		 } else {
			 String strb=new String(stra,j,k-j);
			 return strb; 
		 }
	}
	/**
	 * 判断字符串是否为数字组成
	 *@param   str
	 *@return  boolean
	 *@exception 异常类名 说明
	 */	
	public static boolean isNumeric(String str)  {   
		boolean flag = true;  	     
		if(str.length() == 0)  { 	
			flag = false;   
		}else {
			for (int i = str.length();--i>=0;)  {   
				if (!Character.isDigit(str.charAt(i))) {  
					flag = false;    
				}    
			}
		}
		return flag;    
	} 
		
	/**
	 * 过滤单引号，用两个单引号代替单引号
	 *@param   str
	 *@return  boolean
	 *@exception 异常类名 说明
	 */	
	public static String filterSingleQuote (String str)  { 		
		
		str = str.replaceAll("'", "''");
		
		return str; 
		
	} 
	/**
	 * 恢复单引号
	 *@param   str
	 *@return  boolean
	 *@exception 异常类名 说明
	 */	
	public static String getSingleQuote (String str)  {   
		
		String result;
		
		result = str.replaceAll("''", "'");
		
		return result; 
		
	} 
	
	public static PrintWriter getWriter() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        return out;
	}
	
	public static String getBasePath(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		return basePath;
	}

}
