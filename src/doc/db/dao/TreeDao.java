package doc.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import doc.beo.ClassNodes;
import doc.db.DBConn;


public class TreeDao
{
	public ArrayList<ClassNodes> getNodeInfo() {
		String sql = "select id ,parentId ,hrefAddress ,name from class order by convert(name using gbk) ";
		PreparedStatement pre = null;
		Connection conn = DBConn.getProxoolConn();
		ResultSet rs = null;
		ArrayList<ClassNodes> list = new ArrayList<ClassNodes>();
		try {
			pre = conn.prepareStatement(sql);
			rs =pre.executeQuery();
			while (rs.next()){
				ClassNodes node = new ClassNodes();
				node.setHrefAddress(rs.getString("hrefAddress"));
				node.setId(rs.getInt("id"));
				node.setParentId(rs.getInt("parentId"));
				node.setName(rs.getString("name"));
				list.add(node);
			}
		} catch (SQLException e) {
			System.out.println("查询数据库出错");
			e.printStackTrace();
		}
		finally
		{
			try {
				if (rs != null)
					rs.close();
				if (pre != null)
					pre.close();
				if (conn != null)
					new DBConn().freeConnection(conn);//conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
        return list;
	}
	
	

}
