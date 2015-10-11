package doc.db.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import doc.beo.Sys_users;
import doc.db.DBConn;

public class Sys_usersDAO {	

	/**
	 * 
	 * 查找用户		
	 *@param 
	 *@return List<Sys_users>
	 *@exception 异常类名 说明
	 */	
	public List<Sys_users> searchUser(String type, String keyword, int pageSize, int page) throws SQLException {		
		int min = (page-1)* pageSize;	
		min = min>0? min:0;
		List<Sys_users> list = new ArrayList<Sys_users>();	
		String sql = "SELECT * FROM vsys_users ";
		//System.out.println("searchUser type=" + type+"--");
		if(type.length()==0){    	
			sql = sql + "  LIMIT ?,? ";
    	}else{
			sql = sql + " WHERE "+type+" like '%"+keyword+"%'  LIMIT ?,? ";
    		
    	}
		PreparedStatement pstat = null;
		ResultSet rs = null;		
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);		
			pstat.setInt(1, min);
			pstat.setInt(2, pageSize);
			rs = pstat.executeQuery();			
			while (rs.next()) { 
				list.add(mappingUser(rs));
			}
		} catch (SQLException e) {
			System.out.println("searchUser error=" + e.getMessage());
			e.printStackTrace();
		} finally {
			DBConn.free(rs, pstat, conn);	
		}
		return list;
	}
	
	public Integer countOfUser(String type, String keyword) throws SQLException {
		int result = 0;
		String sql = "SELECT count(id) as count FROM vsys_users ";
		if(type.length()>0){
			sql = sql + " WHERE "+type+" like '%"+keyword+"%'";	
    	}
		PreparedStatement pstat = null;
		ResultSet rs = null;		
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);	
			rs = pstat.executeQuery();			
			while (rs.next()) { 
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			System.out.println("countOfUser error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DBConn.free(rs, pstat, conn);	
		}
		return result;
	}

	/**
	 * 
	 * 根据ID取得用户	 	
	 *@param  int userid
	 *@return Sys_users 对象
	 *@exception 异常类名 说明
	 */	
	public Sys_users getUserById(int userid) throws Exception {
		List<Sys_users> list = new ArrayList<Sys_users>();
		String sql = "SELECT * FROM vsys_users WHERE id=?";
		PreparedStatement pstat = null;
		ResultSet rs = null;
		//Connection conn = DBConn.getConn_jdbc();
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, userid);
			rs = pstat.executeQuery();
			while (rs.next()) {
				list.add(mappingUser(rs));
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
	 * 根据账号取得用户	 	
	 *@param  int userid
	 *@return Sys_users 对象
	 *@exception 异常类名 说明
	 */	
	public Sys_users getUserByAccname(String accname) throws Exception {
		Sys_users user = null;
		String sql = "SELECT * FROM vsys_users WHERE accname=?";
		PreparedStatement pstat = null;
		ResultSet rs = null;
		//Connection conn = DBConn.getConn_jdbc();
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, accname);
			rs = pstat.executeQuery();
			while (rs.next()) {					
				user = mappingUser(rs);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.free(rs, pstat, conn);
		}				
		return user;
	}
	
	/**
	 * 
	 * 验证用户登录		
	 *@param  String accno, String MD5password
	 *@return Sys_users
	 *@exception 异常类名 说明
	 */	
	public Sys_users checkUser(String accname, String password) throws SQLException {		
		//String sql = "SELECT * FROM sys_users WHERE (accno=? OR personnelno=? OR email=?) AND PASSWORD=?";	
		String sql = "SELECT * FROM vsys_users WHERE accname=? AND password=?";	
		List<Sys_users> list = new ArrayList<Sys_users>();
		PreparedStatement pstat = null;
		ResultSet rs = null;
		//Connection conn = DBConn.getConn_jdbc();
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, accname);
			pstat.setString(2, password);
			rs = pstat.executeQuery();
			while (rs.next()) {
				Sys_users user = mappingUser(rs);
				list.add(user);
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
	 * 用户登录时，更新LASTLOGINTIME，LASTLOGINIP		
	 *@param  int userid, String lastLoginIp
	 *@return boolean
	 *@exception 异常类名 说明
	 */	
	public boolean updateTimeAndIp(int userid, String lastLoginIp) throws SQLException {		
		String sql = "UPDATE sys_users SET LASTLOGINIP=? , lastlogintime=now() WHERE ID=?";
		PreparedStatement pstat = null;
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);			
			pstat.setString(1, lastLoginIp);
			pstat.setInt(2, userid);						
			pstat.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateTimeAndIp error : " + e.getMessage());
			return false;
		} finally {
			DBConn.free(null, pstat, conn);	
		}	
	}
	
	/**
	 * 
	 * 更新用户密码
	 *@param  int userid, String password
	 *@return boolean
	 *@exception 异常类名 说明
	 */	
	public boolean updatePWD(int userid, String password) throws SQLException {		
		String sql = "UPDATE sys_users SET password=? WHERE id=?";
		PreparedStatement pstat = null;
		//Connection conn = DBConn.getConn_jdbc();
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);			
			pstat.setString(1, password);
			pstat.setInt(2, userid);						
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
	 * 添加用户
	 * 管理员增加用户	
	 *@param  Sys_users
	 *@return boolean
	 *@exception 异常类名 说明
	 */	
	public boolean addUser(Sys_users user) throws SQLException {		
		String sql = "INSERT INTO sys_users (accname,password,nickname,email,phone,deptid,roleid,memo) VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement pstat = null;
		//Connection conn = DBConn.getConn_jdbc();
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, user.getAccname());
			pstat.setString(2, user.getPassword());
			pstat.setString(3, user.getNickname());
			pstat.setString(4, user.getEmail());
			pstat.setString(5, user.getPhone());
			pstat.setInt(6, user.getDeptid());
			pstat.setInt(7, user.getRoleid());
			pstat.setString(8, user.getMemo());
			pstat.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("adduser sql error: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DBConn.free(null, pstat, conn);	
		}
	}
	/**
	 * 修改用户	
	 *@param  Sys_users
	 *@return boolean
	 *@exception 异常类名 说明
	 */	
	public boolean updateUser(Sys_users user) throws SQLException {	
		boolean bool = false;
		String sql = "UPDATE sys_users SET  email=?, nickname=? ,phone=?,deptid=?,roleid=?,memo=? WHERE id=?";
		
		PreparedStatement pstat = null;
		//Connection conn = DBConn.getConn_jdbc();
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, user.getEmail());				
			pstat.setString(2, user.getNickname());	
			pstat.setString(3, user.getPhone());
			pstat.setInt(4, user.getDeptid());
			pstat.setInt(5, user.getRoleid());
			pstat.setString(6, user.getMemo());
			pstat.setInt(7, user.getId());
			pstat.executeUpdate();
			bool = true;
		} catch (SQLException e) {
			bool = false;
			System.out.println("error" + e.getMessage()	);
			e.printStackTrace();
		} finally {
			DBConn.free(null, pstat, conn);	
		}
		return bool;
	}
	
	/**
	 * 
	 * 删除用户	 
	 *@param  int id
	 *@return boolean
	 *@exception 异常类名 说明
	 */	
	public boolean delUser(int id) throws SQLException {		
		String sql = "DELETE FROM sys_users WHERE id=?";
		PreparedStatement pstat = null;
		//Connection conn = DBConn.getConn_jdbc();
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, id);							
			pstat.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBConn.free(null, pstat, conn);	
		}
	}	
	
	/**
	 * 将结果集映射到user对象
	 * @param rs
	 * @return
	 */
	private Sys_users mappingUser(ResultSet rs){
		Sys_users u = new Sys_users();				
		try {
			u.setId(rs.getInt("id"));
			u.setPassword(rs.getString("password"));
			u.setAccname(rs.getString("accname"));
			u.setEmail(rs.getString("email"));
			u.setLastlogintime(rs.getDate("lastlogintime"));
			u.setLastloginip(rs.getString("lastloginip"));
			u.setNickname(rs.getString("nickname"));
			u.setRegtime(rs.getDate("regtime"));
			u.setState(rs.getInt("state"));
			u.setRoleid(rs.getInt("roleid"));
			u.setRolename(rs.getString("rolename"));
			u.setDeptid(rs.getInt("deptid"));
			u.setDeptname(rs.getString("deptname"));
			u.setPhone(rs.getString("phone"));
			u.setMemo(rs.getString("memo"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
}
