package doc.db.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;

import doc.beo.FileInfo;
import doc.db.DBConn;

public class FileDao
{
	// 删除磁盘文件
	public boolean delFile(int id, String path)
	{
		boolean result = true;
		if (id != 0)
		{
			FileInfo fInfo = new FileInfo();
			fInfo = this.getFileInfo(id);
			String fileName = fInfo.getFileName();
			try {
				// 删除磁盘文件
				File file = new File(path, fileName);
				System.out.println("delete file:" + file);

				if (FileUtils.deleteQuietly(file))
				{
					System.out.println("删除磁盘文件：" + fileName + "成功！");
				} else
				{
					result = false;
					System.out.println("删除磁盘文件：" + fileName + "失败！");
				}
			} catch (Exception e) {
				System.out.println("磁盘文件：" + fileName + "不存在！");
				return true;
			}
			
			String sql = "DELETE FROM file WHERE id=" + id;
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
		}
		else
		{
			System.out.println("无附件");
		}

		return result;
	}
	

	// 根据文件id获取文件信息
	public FileInfo getFileInfo(int id)
	{
		String sql = "SELECT * FROM file WHERE id like " + id;
		Connection conn = DBConn.getProxoolConn();
		Statement stat = null;
		ResultSet rs = null;
		FileInfo fInfo = new FileInfo();
		try
		{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next())
			{
				fInfo.setId(rs.getInt("id"));
				fInfo.setName(rs.getString("name"));
				fInfo.setFileName(rs.getString("file_name"));
				fInfo.setSize(rs.getLong("size"));
				fInfo.setType(rs.getString("type"));
				fInfo.setUpload_time(rs.getTimestamp("upload_time"));

			}
		} catch (SQLException e)
		{
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
		return fInfo;
	}
	

	public static void main(String[] args)
	{
		FileDao dao = new FileDao();
	}

}
