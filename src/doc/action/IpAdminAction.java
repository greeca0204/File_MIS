package doc.action;

import java.io.PrintWriter;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import common.ip.beo.IP;
import common.ip.db.dao.IPDAO;

import doc.commons.CommonMethod;

public class IpAdminAction extends ActionSupport {
	/**
	 * 增加
	 * @return
	 * @throws Exception
	 */
	public String addIpPre() throws Exception {
		
		return SUCCESS;
	}

	public String addIp() throws Exception {
		IPDAO ipdao = new IPDAO();
		PrintWriter out = CommonMethod.getWriter();
		if(ipdao.addIp(ip)){
			out.print("<script>alert('增加IP段成功！')</script>");
			out.print("<script>window.location='" + CommonMethod.getBasePath() + "admin/ipList.action'</script>");
			out.close();
		}else{
			out.print("<script>alert('增加IP段失败！')</script>");
			out.print("<script>history.go(-1);</script>");
			out.close();
		}
		return NONE;
	}
	
	/**
	 * 列表
	 * @return
	 * @throws Exception
	 */
	public String showIpList() throws Exception {
		IPDAO picitemdao = new IPDAO();
		row = picitemdao.countOfIpList(keyword);
		curPage = curPage<1 ? 1:curPage;
		totalPage = row%pageSize==0? row/pageSize : 1+row/pageSize;
		curPage = curPage >= totalPage? totalPage : curPage;
		ipList = picitemdao.getIpList(keyword, pageSize, curPage);
		return SUCCESS;
	}
	
	
	/**
	 * 修改
	 * @return
	 * @throws Exception
	 */
	public String editIpPre() throws Exception {
		IPDAO picitemdao = new IPDAO();
		ip = picitemdao.getIpById(id);
		return SUCCESS;
	}
	public String editIp() throws Exception {
		IPDAO picitemdao = new IPDAO();
		PrintWriter out = CommonMethod.getWriter();
		if(picitemdao.editIp(ip)){
			out.print("<script>alert('修改IP段成功！')</script>");
			out.print("<script>window.location='" + CommonMethod.getBasePath() + "admin/ipList.action?keyword=" + keyword + "&curPage=" + curPage + "'</script>");
			out.close();
		}else{
			out.print("<script>alert('修改IP段失败！')</script>");
			out.print("<script>history.go(-1);</script>");
			out.close();
		}
		return NONE;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delIp() throws Exception {
		IPDAO picitemdao = new IPDAO();
		ip = picitemdao.getIpById(id);
		PrintWriter out = CommonMethod.getWriter();
		if(picitemdao.delIp(id)){
			out.print("<script>alert('删除IP段成功！')</script>");
			out.print("<script>window.location='" + CommonMethod.getBasePath() + "admin/ipList.action?keyword=" + keyword + "&curPage=" + curPage + "'</script>");
			out.close();
		}else{
			out.print("<script>alert('删除IP段失败！')</script>");
			out.print("<script>history.go(-1);</script>");
			out.close();
		}
		return NONE;
	}
	
	private IP ip;
	private List<IP> ipList;
	private String keyword="";
	private int pageSize=15;
	private int curPage;
	private int totalPage;
	private int row;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	public IP getIp() {
		return ip;
	}
	public void setIp(IP ip) {
		this.ip = ip;
	}
	public List<IP> getIpList() {
		return ipList;
	}
	public void setIpList(List<IP> ipList) {
		this.ipList = ipList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
