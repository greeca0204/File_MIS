package common.ip;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;

import common.ip.beo.IP;
import common.ip.db.dao.IPDAO;

public class IPUtil{ 
	
	/**
	 * 判断ip地址是否校内ip
	 * @param ipaddr
	 * @return
	 */
	public static boolean isAllow(String ipaddr){		
		//获得本地ip范围		
		List<IP>  list = new ArrayList<IP>();
		try {
			list =IPDAO.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//判断客户端ip是否在本地ip范围内
		boolean ipflag=false;
		int compareToStartip;
		int compareToEndip;
		for(int i=0; i<list.size(); i++){			
			//客户端ip和起始ip比较
			compareToStartip = IpConvert.StringToBigInt(ipaddr).compareTo(IpConvert.StringToBigInt(list.get(i).getStartip()));
			//客户端ip和结束ip比较
			compareToEndip = IpConvert.StringToBigInt(ipaddr).compareTo(IpConvert.StringToBigInt(list.get(i).getEndip()));			
			if( compareToStartip>=0 && compareToEndip<=0 ){				
				ipflag = true;
				break;
	        }					
		}
		if(ipaddr.equals("202.116.13.62")){
			ipflag = false;
		}
		return ipflag;
	}
	
	/**
	 * 获取客户端IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {    
		String ip = request.getHeader("x-forwarded-for");    
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
			ip = request.getHeader("Proxy-Client-IP"); 
			//System.out.println("Proxy-Client-IP:" + ip);
		}    
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
			ip = request.getHeader("WL-Proxy-Client-IP"); 
			//System.out.println("WL-Proxy-Client-IP:" + ip);
		}    
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
			ip = request.getRemoteAddr();
			//System.out.println("getRemoteAddr:" + ip);
		}
		if(ip != null){
			String[] ipaddr = ip.split(",");
			for(int i=0;i<ipaddr.length;i++){
				if(ipaddr[i] != null){
					return ipaddr[i];
				}
			}
		}
		return ip;    
    }
    
}