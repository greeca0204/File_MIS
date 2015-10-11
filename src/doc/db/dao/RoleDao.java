package doc.db.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import common.ip.beo.IP;

import doc.beo.Role;
import doc.db.DBConn;

public class RoleDao {	
	/**	
	 * 所有角色		
	 *@param 
	 *@return List<Roles>
	 *@exception 异常类名 说明
	 */	
	public List<Role> getAll() throws SQLException {			
		List<Role> list = new ArrayList<Role>();		
		String sql = "select * from role";
		Statement stat = null;
		ResultSet rs = null;		
		Connection conn = DBConn.getProxoolConn();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) { 
				Role role = mappingRole(rs);
				list.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.free(rs, stat, conn);			
		}
		return list;
	}
	/**
	 * 
	 * 得到总条数		
	 *@param 
	 *@return int
	 *@exception 异常类名 说明
	 */	
	public int Count()  throws SQLException {		
		int count=0;
		String sql = "SELECT count(*) as count FROM role";		
		Statement stat = null;
		ResultSet rs = null;		
		Connection conn = DBConn.getProxoolConn();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				count=rs.getInt("count");					
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.free(rs, stat, conn);	
			
		}		
		return count;	
	}
	/**
	 * 
	 * 根据ID取得		
	 *@param int roleid
	 *@return Roles
	 *@exception 异常类名 说明
	 */	
	public Role getById(int id) throws SQLException {
		List<Role> list = new ArrayList<Role>();		
		String sql = "SELECT * FROM role WHERE id=?";
		PreparedStatement pstat = null;
		ResultSet rs = null;		
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, id);
			rs = pstat.executeQuery();
			while (rs.next()) {
				Role role = mappingRole(rs);
				list.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.free(rs, pstat, conn);
		}
		if(list.size()==1 && null!=list) {
			return list.get(0);
		}
		return null;
	}
	
	
	/**
	 * 
	 * 增加		
	 *@param Srm role
	 *@return boolean
	 *@exception 异常类名 说明
	 */	
	public int addRole(Role role) throws SQLException {		
		String sql = "INSERT INTO role(name,memo) VALUES(?,?)";
		PreparedStatement pstat = null;		
		Connection conn = DBConn.getProxoolConn();
		ResultSet rs = null;
		int id = 0;
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, role.getName());
			pstat.setString(2, role.getMemo());			
			pstat.execute();
			pstat.close();
			
			sql = "SELECT @@IDENTITY as id";			
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();			
			if(rs.next())
			{
				id = rs.getInt("id");				
			}				
			rs.close();				
			pstat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.free(rs, pstat, conn);	
		}
		return id;
	}
	/**
	 * 
	 * 修改		
	 *@param Srm role
	 *@return boolean
	 *@exception 异常类名 说明
	 */	
	public boolean updateRole(Role role) throws SQLException {		
		String sql = "UPDATE role set name=?,memo=? WHERE id=?";
		PreparedStatement pstat = null;
		//Connection conn = DBConn.getConn_jdbc();
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, role.getName());
			pstat.setString(2, role.getMemo());
			pstat.setInt(3, role.getId());
			pstat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.free(null, pstat, conn);	
		}
		return true;
	}
	/**
	 * 
	 * 删除		
	 *@param int id
	 *@return boolean
	 *@exception 异常类名 说明
	 */	
	public boolean delRole(int id) throws SQLException {		
		String sql = "DELETE FROM role WHERE id=?";
		PreparedStatement pstat = null;
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, id);							
			pstat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBConn.free(null, pstat, conn);	
		}
		return true;
	}
	
	private Role mappingRole(ResultSet rs){
		Role role = new Role();
		try {
			role.setId(rs.getInt("id"));
			role.setName(rs.getString("name"));
			role.setMemo(rs.getString("memo"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("rs mapping role error: " + e.getMessage());
		}			
		return role;		
	}
	
}
