package common.ip.db.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import common.ip.beo.IP;
import doc.db.DBConn;

public class IPDAO {	
	/**	
	 * 所有角色		
	 *@param 
	 *@return List<Roles>
	 *@exception 异常类名 说明
	 */	
	public static List<IP> getAll() throws SQLException {			
		List<IP> list = new ArrayList<IP>();		
		String sql = "select * from commons_ip order by id";
		Statement stat = null;
		ResultSet rs = null;		
		Connection conn = DBConn.getProxoolConn();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) { 
				IP ip = new IP();
				ip.setId(rs.getInt("id"));
				ip.setStartip(rs.getString("startip"));
				ip.setEndip(rs.getString("endip"));
				list.add(ip);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.free(rs, stat, conn);			
		}
		return list;
	}
	
	/**
	 * 增加
	 * @param ip
	 * @return
	 */
	public boolean addIp(IP ip) {
		String sql = "INSERT INTO commons_ip(startip,endip,remark) VALUES(?,?,?)";
		PreparedStatement ps = null;	
		Connection conn = DBConn.getProxoolConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ip.getStartip());
			ps.setString(2, ip.getEndip());
			ps.setString(3, ip.getRemark());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBConn.free(null, ps, conn);			
		}
	}
	
	/**
	 * 编辑
	 * @param ip
	 * @return
	 */
	public boolean editIp(IP ip){
		String sql = "UPDATE commons_ip SET startip=?,endip=?,remark=? WHERE id=?";
		PreparedStatement pstat = null;
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, ip.getStartip());
			pstat.setString(2, ip.getEndip());
			pstat.setString(3, ip.getRemark());
			pstat.setInt(4, ip.getId());
			pstat.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("add ip sql error: " + e.getMessage());
			return false;
		} finally {
			DBConn.free(null, pstat, conn);	
		}
	}
	
	/**
	 * 根据id获取内容
	 * @param id
	 * @return
	 */
	public IP getIpById(int id) {
		IP ip = new IP();
		String sql = "SELECT * FROM commons_ip WHERE id=?";
		PreparedStatement pstat = null;
		ResultSet rs = null;
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, id);
			rs = pstat.executeQuery();			
			while (rs.next()) {	
				ip = mappingIp(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get ip sql error: " + e.getMessage());
		} finally {
			DBConn.free(rs, pstat, conn);	
		}			
		return ip;
	}


	/**
	 * 列表
	 * @param keyword 关键词
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<IP> getIpList(String keyword, int pageSize, int page) {
		List<IP> list  = new ArrayList<IP>();
		int min = (page-1)*pageSize>0 ? (page-1)*pageSize : 0;
		String sql = "SELECT * FROM commons_ip";
		if(null != keyword && 0 != keyword.length()){
			sql = sql + " WHERE startip LIKE '%"+keyword+"%' OR endip LIKE '%"+keyword+"%'";			
		}
		sql = sql + " ORDER BY id DESC LIMIT ?,?";
		PreparedStatement pstat = null;
		ResultSet rs = null;
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);			
			pstat.setInt(1, min);
			pstat.setInt(2, pageSize);
			rs = pstat.executeQuery();			
			while (rs.next()) {	
				list.add(mappingIp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get iplist sql error: " + e.getMessage());
		} finally {
			DBConn.free(rs, pstat, conn);	
		}			
		return list;
	}
	
	/**
	 * 获取列表数量
	 * @param keyword 关键词
	 * @return
	 */
	public Integer countOfIpList(String keyword) {
		int count = 0;
		String sql = "SELECT count(id) count FROM commons_ip";
		if(null != keyword && 0 != keyword.length()){
			sql = sql + " WHERE startip LIKE '%"+keyword+"%' OR endip LIKE '%"+keyword+"%'";			
		}
		PreparedStatement pstat = null;
		ResultSet rs = null;
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();			
			while (rs.next()) {	
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get count of ip list sql error: " + e.getMessage());
		} finally {
			DBConn.free(rs, pstat, conn);	
		}			
		return count;
	}
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean delIp(int id){
		String sql = "DELETE FROM commons_ip WHERE id=?";
		PreparedStatement pstat = null;
		Connection conn = DBConn.getProxoolConn();
		try {
		pstat = conn.prepareStatement(sql);
		pstat.setInt(1, id);
		pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("delete ip sql error: " + e.getMessage());
			return false;
		} finally {
			DBConn.free(null, pstat, conn);	
		}
		return true;
	}
	
	
	private IP mappingIp(ResultSet rs){
		IP ip = new IP();
		try {
			ip.setId(rs.getInt("id"));
			ip.setStartip(rs.getString("startip"));
			ip.setEndip(rs.getString("endip"));
			ip.setRemark(rs.getString("remark"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("rs mapping ip error: " + e.getMessage());
		}			
		return ip;		
	}
	
	
}
