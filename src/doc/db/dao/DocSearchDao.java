package doc.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import doc.beo.JnuDocument;
import doc.db.DBConn;

public class DocSearchDao
{
	//根据文档编号检索文档
	public List<JnuDocument> NumberSearch(String number_key)
	{
		List<JnuDocument> docList = new ArrayList<JnuDocument>();
		String sql = "SELECT * FROM document WHERE number like '%" + number_key + "%'";
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
				doc.setAdd_time(rs.getTimestamp("add_time"));
				doc.setClass_id(rs.getInt("class_id"));
				doc.setDescription(rs.getString("description"));
				doc.setDoc_time(rs.getDate("doc_time"));
				doc.setFile_id(rs.getInt("file_id"));
				doc.setId(rs.getInt("id"));
				doc.setName(rs.getString("name"));
				doc.setNumber(rs.getString("number"));
				docList.add(doc);				
			}
		} catch (Exception e)
		{
			System.out.println("sql error at :" + sql);
			System.out.println("sql error: " + e);
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
		return docList;
		
	}
	
	//根据文档名称检索文档
	public List<JnuDocument> DocNameSearch(String name_key)
	{
		List<JnuDocument> docList = new ArrayList<JnuDocument>();
		String sql = "SELECT * FROM document WHERE name LIKE '%" + name_key + "%'";
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
				doc.setAdd_time(rs.getTimestamp("add_time"));
				doc.setClass_id(rs.getInt("class_id"));
				doc.setDescription(rs.getString("description"));
				doc.setDoc_time(rs.getDate("doc_time"));
				doc.setFile_id(rs.getInt("file_id"));
				doc.setId(rs.getInt("id"));
				doc.setName(rs.getString("name"));
				doc.setNumber(rs.getString("number"));

				docList.add(doc);				
			}
		} catch (Exception e)
		{
			System.out.println("sql error at :" + sql);
			System.out.println("sql error: " + e);
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
		return docList;
		
	}
	
	//根据关键词检索文档
	public List<JnuDocument> KeywordsSearch(String keywords_key)
	{
		List<JnuDocument> docList = new ArrayList<JnuDocument>();
		String sql = "SELECT * FROM document WHERE keywords LIKE '%" + keywords_key + "%'";
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
				doc.setAdd_time(rs.getTimestamp("add_time"));
				doc.setClass_id(rs.getInt("class_id"));
				doc.setDescription(rs.getString("description"));
				doc.setDoc_time(rs.getDate("doc_time"));
				doc.setFile_id(rs.getInt("file_id"));
				doc.setId(rs.getInt("id"));
				doc.setName(rs.getString("name"));
				doc.setNumber(rs.getString("number"));
				doc.setKeywords(rs.getString("keywords"));
				docList.add(doc);				
			}
		} catch (Exception e)
		{
			System.out.println("sql error at :" + sql);
			System.out.println("sql error: " + e);
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
		return docList;		
	}
	
	//根据描述检索文档
	public List<JnuDocument> DescriptionSearch(String description_key)
	{
		List<JnuDocument> docList = new ArrayList<JnuDocument>();
		String sql = "SELECT * FROM document WHERE description LIKE '%" + description_key + "%'";
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
				doc.setAdd_time(rs.getTimestamp("add_time"));
				doc.setClass_id(rs.getInt("class_id"));
				doc.setDescription(rs.getString("description"));
				doc.setDoc_time(rs.getDate("doc_time"));
				doc.setFile_id(rs.getInt("file_id"));
				doc.setId(rs.getInt("id"));
				doc.setName(rs.getString("name"));
				doc.setNumber(rs.getString("number"));
				doc.setKeywords(rs.getString("keywords"));
				docList.add(doc);				
			}
		} catch (Exception e)
		{
			System.out.println("sql error at :" + sql);
			System.out.println("sql error: " + e);
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
		return docList;		
	}
	
	public static void main(String[] args)
	{
		DocSearchDao testDao = new DocSearchDao();
		System.out.println(testDao.NumberSearch("123"));
	}
	
	

}
