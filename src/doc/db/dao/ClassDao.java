package doc.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import doc.beo.ClassNodes;
import doc.db.DBConn;

public class ClassDao 
{
	//根据分类id获取分类名称
	public String getClassName(int id)
	{
		String nodeName = null;
		
		String sql = "SELECT name FROM class WHERE id like " + id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = DBConn.getProxoolConn();
		
		try 
		{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				nodeName = rs.getString("name");
			}
			
		} catch (SQLException e) {
			System.out.println("error msg:" + e.getMessage());
			e.printStackTrace();
		}
		finally
		{			
			try {
					if(conn != null)
						new DBConn().freeConnection(conn);//conn.close();
					if(stat != null)
						stat.close();
					if(rs != null)
						rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		
		return nodeName;
	}	
	
	//根据分类id获取父分类id
	public int getParentId(int id)
	{
		int parentId = 1;		
		String sql = "SELECT parentId FROM class WHERE id=" + id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = DBConn.getProxoolConn();
		
		try 
		{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				parentId = rs.getInt("parentId");
			}
			
		} catch (SQLException e) {
			System.out.println("error msg:" + e.getMessage());
			e.printStackTrace();
		}
		finally
		{			
			try {
					if(conn != null)
						new DBConn().freeConnection(conn);//conn.close();
					if(stat != null)
						stat.close();
					if(rs != null)
						rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		
		return parentId;
	}
	
	//获取所有分类
	public List<ClassNodes> getAllClassNodes()
	{
		List<ClassNodes> list = new ArrayList<ClassNodes>();
		
		String sql = "SELECT * FROM class WHERE parentId=1 ORDER BY name";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = DBConn.getProxoolConn();
		
		try 
		{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				ClassNodes cls = new ClassNodes();
				cls.setId(rs.getInt("id"));
				cls.setName(rs.getString("name"));
				cls.setParentId(rs.getInt("parentId"));
				cls.setHrefAddress(rs.getString("hrefAddress"));
				list.add(cls);
			}
			
		} catch (SQLException e) {
			System.out.println("error msg:" + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if (rs != null)
					rs.close();
				if (stat != null)
					stat.close();
				if (conn != null)
					new DBConn().freeConnection(conn);//conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return list;
	}
	
	//根据id获取二级分类
	public List<ClassNodes> getSubclassNodes(int nodeId)
	{
		List<ClassNodes> list = new ArrayList<ClassNodes>();
		
		String sql = "SELECT * FROM class WHERE parentId=" + nodeId + " ORDER BY name ";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = DBConn.getProxoolConn();
		
		try 
		{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				ClassNodes cls = new ClassNodes();
				cls.setId(rs.getInt("id"));
				cls.setName(rs.getString("name"));
				cls.setParentId(rs.getInt("parentId"));
				cls.setHrefAddress(rs.getString("hrefAddress"));
				list.add(cls);
			}
			
		} catch (SQLException e) {
			System.out.println("error msg:" + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if (rs != null)
					rs.close();
				if (stat != null)
					stat.close();
				if (conn != null)
					new DBConn().freeConnection(conn);//conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return list;
	}
		
	//添加文件分类--暂时没有考虑超链接字段
	public boolean addClass(ClassNodes classNodes)
	{
		String sql = "INSERT INTO class (name,parentId) VALUES(?,?) ";
		
		PreparedStatement pstat = null;

		Connection conn = DBConn.getProxoolConn();

		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, classNodes.getName());
			pstat.setInt(2, classNodes.getParentId());
			pstat.execute();
			
		} catch (SQLException e) {
			System.out.println("error msg:" + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if (pstat != null)
					pstat.close();
				if (conn != null)
					new DBConn().freeConnection(conn);//conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return true;
	}
	
	//修改文件分类
	public boolean editClass(ClassNodes classNodes)
	{
		String sql = "UPDATE class SET name=? WHERE id=? ";
		
		PreparedStatement pstat = null;

		Connection conn = DBConn.getProxoolConn();

		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, classNodes.getName());
			pstat.setInt(2, classNodes.getId());
			pstat.execute();
			
		} catch (SQLException e) {
			System.out.println("error msg:" + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if (pstat != null)
					pstat.close();
				if (conn != null)
					new DBConn().freeConnection(conn);//conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return true;
	}
	
	//删除文件分类
	public boolean delClass(int id)
	{
		boolean result = true;
		
		String sql = "delete from class where id=" + id;
		
		Statement stat = null;

		Connection conn = DBConn.getProxoolConn();

		try {
			stat = conn.createStatement();
			stat.execute(sql);
			
		} catch (SQLException e) {
			System.out.println("error msg:" + e.getMessage());
			e.printStackTrace();
			result = false;
		}
		finally
		{
			try {
				if (stat != null)
					stat.close();
				if (conn != null)
					new DBConn().freeConnection(conn);//conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return result;
	}
	

	public static void main(String[] args) 
	{
		ClassDao classDao = new ClassDao();
		System.out.println(classDao.getClassName(3));		
	}

}
