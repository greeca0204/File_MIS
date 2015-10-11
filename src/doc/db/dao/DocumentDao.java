package doc.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import doc.beo.JnuDocument;
import doc.db.DBConn;

public class DocumentDao
{
    private final static String sql = " SELECT * FROM document ";
    private final static String totalSql = " SELECT COUNT(*) as count FROM document ";
    
    public String getSql() {
    	return sql;
    }
    public String getTotalSql()  {
    	return totalSql;
    }
    
	// 添加文档
	public Boolean addDocument(JnuDocument document)
	{
		String sql = "INSERT INTO document(name,file_id,number,class_id,doc_time,description,keywords,subclass_id) "
				+ " VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement pstat = null;
		Connection conn = DBConn.getProxoolConn();

		try
		{
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, document.getName());
			pstat.setInt(2, document.getFile_id());
			pstat.setString(3, document.getNumber());
			pstat.setInt(4, document.getClass_id());
			pstat.setDate(5, document.getDoc_time());
			pstat.setString(6, document.getDescription());
			pstat.setString(7, document.getKeywords());
			pstat.setInt(8, document.getSubclass_id());
			System.out.println("subclassid:" + document.getSubclass_id());
			pstat.execute();
			return true;
		} catch (SQLException e)
		{
			System.out.println("error msg:" + e.getMessage());
			e.printStackTrace();
			return false;
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

	}
	
	//修改文档
	public boolean editDocument(JnuDocument doc)
	{
		boolean result = true;
		String sql = "UPDATE document SET number=?,name=?,keywords=?,doc_time=?,file_id=?," +
				"class_id=?,description=?,subclass_id=? WHERE id='" + doc.getId() + "'";
		Connection conn = DBConn.getProxoolConn();
		PreparedStatement pstat = null;
		try
		{
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, doc.getNumber());
			pstat.setString(2, doc.getName());
			pstat.setString(3, doc.getKeywords());
			pstat.setDate(4, doc.getDoc_time());
			pstat.setInt(5, doc.getFile_id());
			pstat.setInt(6, doc.getClass_id());
			pstat.setString(7, doc.getDescription());
			pstat.setInt(8, doc.getSubclass_id());
			
			pstat.execute();
			
		} catch (Exception e)
		{
			result = false;
			System.out.println("sql error: " + e.getMessage());
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
		return result;
	}

	//删除文档
	public boolean delDocument(int id)
	{
		boolean result = true;
		String sql = "DELETE FROM document WHERE id LIKE " + id;
		Statement stat = null;
		Connection conn = DBConn.getProxoolConn();
		try
		{
			stat = conn.createStatement();
			stat.execute(sql);

		} catch (SQLException e)
		{
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
	
	// 获取文档列表
	public List<JnuDocument> getDocList(int class_id)
	{
		List<JnuDocument> list = new ArrayList<JnuDocument>();
		ClassDao cd = new ClassDao();
		String sql = null;
		if(class_id == 1)
		{
			sql = "SELECT * FROM document ORDER BY doc_time DESC";
		}
		else
		{
			sql = "SELECT * FROM document WHERE class_id=" + class_id 
			+ " OR subclass_id=" + class_id + " ORDER BY doc_time DESC";
		}
		Connection conn = DBConn.getProxoolConn();
		Statement stat = null;
		ResultSet rs = null;
		try
			{
				stat = conn.createStatement();
				rs = stat.executeQuery(sql);
				while (rs.next())
				{
					JnuDocument doc = new JnuDocument();
					doc.setId(rs.getInt("id"));
					doc.setNumber(rs.getString("number"));
					doc.setName(rs.getString("name"));
					doc.setKeywords(rs.getString("keywords"));
					doc.setClass_id(rs.getInt("class_id"));
					doc.setAdd_time(rs.getTimestamp("add_time"));
					doc.setDoc_time(rs.getDate("doc_time"));
					doc.setDescription(rs.getString("description"));
					doc.setFile_id(rs.getInt("file_id"));
					doc.setSubclass_id(rs.getInt("subclass_id"));
					list.add(doc);
				}
			} catch (SQLException e)
			{
				System.out.println("sql error: " + e.getMessage());
				e.printStackTrace();
			} finally
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
	
	//获取文档列表（使用limit）
	public List<JnuDocument> getDocList(String modSql)
	{
		List<JnuDocument> list = new ArrayList<JnuDocument>();
		ClassDao cd = new ClassDao();
		String query = modSql;
		
		Connection conn = DBConn.getProxoolConn();
		Statement stat = null;
		ResultSet rs = null;
		try
			{
				stat = conn.createStatement();
				rs = stat.executeQuery(query);
				while (rs.next())
				{
					JnuDocument doc = new JnuDocument();
					doc.setId(rs.getInt("id"));
					doc.setNumber(rs.getString("number"));
					doc.setName(rs.getString("name"));
					doc.setKeywords(rs.getString("keywords"));
					doc.setClass_id(rs.getInt("class_id"));
					doc.setAdd_time(rs.getTimestamp("add_time"));
					doc.setDoc_time(rs.getDate("doc_time"));
					doc.setDescription(rs.getString("description"));
					doc.setFile_id(rs.getInt("file_id"));
					doc.setSubclass_id(rs.getInt("subclass_id"));
					list.add(doc);
					//System.out.println(doc.getName());
				}
			} catch (SQLException e)
			{
				System.out.println("sql error: " + e.getMessage());
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
		
			System.out.println("DocumentDao:getDocList():" + query);
			return list;		
	}
	
	//获取指定文档
	public JnuDocument getDocById(int id)
	{
		JnuDocument doc = new JnuDocument();
		String sql = "SELECT * FROM document WHERE id LIKE " + id;
		Connection conn = DBConn.getProxoolConn();
		Statement stat = null;
		ResultSet rs = null;
		try
		{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				doc.setId(id);
				doc.setNumber(rs.getString("number"));
				doc.setName(rs.getString("name"));
				doc.setKeywords(rs.getString("keywords"));
				doc.setClass_id(rs.getInt("class_id"));
				doc.setAdd_time(rs.getTimestamp("add_time"));
				doc.setDoc_time(rs.getDate("doc_time"));
				doc.setDescription(rs.getString("description"));
				doc.setFile_id(rs.getInt("file_id"));
				doc.setSubclass_id(rs.getInt("subclass_id"));
			}
		} catch (Exception e)
		{
			System.out.println("sql error at :" + sql);
			System.out.println("sql error: " + e.getMessage());
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
		return doc;
	}
	
	//获取文档总数
	public int getTotalRows(String totalSql)
	{
		int totalRows = 0;
		String sql = totalSql;
		Connection conn = DBConn.getProxoolConn();
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				totalRows = rs.getInt("count");				
			}

		} catch (SQLException e) {
			System.out.println("sql error at :" + sql);
			System.out.println("sql error: " + e.getMessage());
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
		System.out.println("DocumentDao:getTotalRows():" + sql);
		return totalRows;
	}
	
	//查找某个分类最后一个提交的文档编号
	public String getLastNumber(int nodeId)
	{
		String lastNum = new String();
		String sql = "SELECT number FROM document WHERE class_id=" + nodeId + " OR subclass_id=" 
		+ nodeId + " ORDER BY doc_time DESC,id DESC LIMIT 1";
		Connection conn = DBConn.getProxoolConn();
		Statement stat = null;
		ResultSet rs = null;
		try
		{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				lastNum = rs.getString("number");
			}
		} catch (Exception e)
		{
			System.out.println("sql error at :" + sql);
			System.out.println("sql error: " + e.getMessage());
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
		return lastNum;
	}
	
	//查询文档编号是否存在
	public boolean isEnableDocNumber(String number)
	{
		int count = 0;
		String sql = filterQuery(totalSql, "number", number);
		Connection conn = DBConn.getProxoolConn();
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			stat = conn.createStatement();
			rs =stat.executeQuery(sql);
			while(rs.next())
			{
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count == 0)
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
	
//	sql语句  + class_id
	public String classQuery(String sql,int class_id)
	{
		return sql + " WHERE (class_id=" + class_id + " OR subclass_id=" + class_id + ")";
	}
	
//	sql语句	+ filter
    public String filterQuery(String sql, String property, String value) {
        StringBuffer result = new StringBuffer(sql);
        if (sql.indexOf("WHERE") == -1) {
            result.append(" WHERE 1 = 1 "); //stub WHERE clause so can just append AND clause
        }
        if(property.equals("class_id"))
        {
        	result.append(" AND (class_id=" + Integer.parseInt(value) + 
        			" OR subclass_id=" + Integer.parseInt(value) + ")");
        }else
        {
        	result.append(" AND " + property + " like '%" + value + "%'");  
        }
        return result.toString();
    }
//   sql语句  + sort
    public String sortQuery(String sql, String property, String sortOrder) 
    {
        StringBuffer result = new StringBuffer(sql + " ORDER BY ");
        result.append(property + " " + sortOrder);
        return result.toString();
    }
//   sql语句  + limit  
    public String limitQuery(int rowEnd, String sql) {
        return sql + " LIMIT 0 " + "," + rowEnd;
    } 
 
//    默认排序
	public String getDefaultSortOrder() {
		return " ORDER BY doc_time DESC ";
	}
	// 测试数据库函数



}
