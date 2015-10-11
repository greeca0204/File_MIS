package doc.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import doc.beo.FileInfo;
import doc.db.DBConn;

public class FileDownloadDao 
{
	//根据文件id获取文件名
	public String getFileName(int id)
	{
		String sql = "SELECT file_name FROM file WHERE id like '" + id + "'";
		Connection conn = DBConn.getProxoolConn();
		Statement stat = null;
		ResultSet rs = null;
		String fileName = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				fileName = rs.getString("file_name");			
			}
		} catch (SQLException e) {
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
		return fileName;
	}
	
	//获取文件上传前的名称，用于对下载文件重命名
	public String getDownloadName(int id)
	{
		FileDao fd = new FileDao();
		FileInfo file = fd.getFileInfo(id);
		String name = file.getName();
		String fname = file.getFileName();
		int position = fname.lastIndexOf(".");
		String extension = fname.substring(position);
				
		return name + extension;
	}

}
