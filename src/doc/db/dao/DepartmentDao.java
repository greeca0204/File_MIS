package doc.db.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import doc.beo.Department;
import doc.db.DBConn;

public class DepartmentDao {	
	/**	
	 * 所有角色		
	 *@param 
	 *@return List<Departments>
	 *@exception 异常类名 说明
	 */	
	public List<Department> getAll() throws SQLException {			
		List<Department> list = new ArrayList<Department>();		
		String sql = "select * from department";
		Statement stat = null;
		ResultSet rs = null;		
		Connection conn = DBConn.getProxoolConn();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) { 
				Department Department = mappingDepartment(rs);
				list.add(Department);
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
		String sql = "SELECT count(*) as count FROM department";		
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
	 *@param int Departmentid
	 *@return Departments
	 *@exception 异常类名 说明
	 */	
	public Department getById(int id) throws SQLException {
		List<Department> list = new ArrayList<Department>();		
		String sql = "SELECT * FROM department WHERE id=?";
		PreparedStatement pstat = null;
		ResultSet rs = null;		
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, id);
			rs = pstat.executeQuery();
			while (rs.next()) {
				Department Department = mappingDepartment(rs);
				list.add(Department);
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
	 *@param Srm Department
	 *@return boolean
	 *@exception 异常类名 说明
	 */	
	public int addDepartment(Department dept) throws SQLException {		
		String sql = "INSERT INTO department(name,pid,memo) VALUES(?,?)";
		PreparedStatement pstat = null;		
		Connection conn = DBConn.getProxoolConn();
		ResultSet rs = null;
		int id = 0;
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dept.getName());
			pstat.setInt(2, dept.getPid());
			pstat.setString(3, dept.getMemo());			
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
	 *@param Srm Department
	 *@return boolean
	 *@exception 异常类名 说明
	 */	
	public boolean updateDepartment(Department dept) throws SQLException {		
		String sql = "UPDATE department set name=?,pid=?,memo=? WHERE id=?";
		PreparedStatement pstat = null;
		//Connection conn = DBConn.getConn_jdbc();
		Connection conn = DBConn.getProxoolConn();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dept.getName());
			pstat.setInt(2, dept.getPid());
			pstat.setString(3, dept.getMemo());
			pstat.setInt(4, dept.getId());
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
	public boolean delDepartment(int id) throws SQLException {		
		String sql = "DELETE FROM department WHERE id=?";
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
	
	private Department mappingDepartment(ResultSet rs){
		Department dept = new Department();
		try {
			dept.setId(rs.getInt("id"));
			dept.setName(rs.getString("name"));
			dept.setPid(rs.getInt("pid"));
			dept.setMemo(rs.getString("memo"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("rs mapping Department error: " + e.getMessage());
		}			
		return dept;		
	}
	
}
