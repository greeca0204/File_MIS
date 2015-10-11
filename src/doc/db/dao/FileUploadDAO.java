package doc.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import doc.beo.FileInfo;
import doc.db.DBConn;

public class FileUploadDAO 
{

	// 添加文件信息
	public boolean addFileInfo(FileInfo fileInfo) {
		String sql = "INSERT INTO file(name,file_name,size,type) "
				+ " VALUES(?,?,?,?)";

		PreparedStatement pstat = null;

		Connection conn = DBConn.getProxoolConn();

		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, fileInfo.getName());
			pstat.setString(2, fileInfo.getFileName());
			pstat.setLong(3, fileInfo.getSize());			
			pstat.setString(4, fileInfo.getType());
			pstat.execute();

		} catch (SQLException e) {						
			System.out.println("error msg:" + e.getMessage());
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (conn != null)
				new DBConn().freeConnection(conn);//conn.close();			
		}
		return true;
	}
	
	//根据文件名获取文件ID
	public int getFileId(String fileName)
	{
		String sql = "SELECT id FROM file WHERE file_name like '" + fileName + "'";
		Connection conn = DBConn.getProxoolConn();
		Statement stat = null;
		ResultSet rs = null;
		int id = 0;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				id = rs.getInt("id");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return id;
	}
	
	//测试数据库操作
	public static void main(String[] args) 
	{
		FileUploadDAO fdao= new FileUploadDAO();
		String fileName = "091217172429337.rar";
		int testId = fdao.getFileId(fileName);
		System.out.println(testId);
		
	}

}
