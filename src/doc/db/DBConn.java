package doc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.logicalcobwebs.proxool.*;
import org.logicalcobwebs.proxool.admin.SnapshotIF;

public class DBConn{
	
	//定义conn
	private static Connection conn=null;
	
	//定义proxool连接池
	public static Connection getProxoolConn(){
		try{
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");// proxool驱动类   
            conn = DriverManager.getConnection("proxool.DBPool");   
            // 此处的DBPool是在proxool.xml中配置的连接池别名   
            //showSnapshotInfo();   		
		}catch(Exception e){
			System.err.println("数据库连接异常：" + e.getMessage());
			return conn;			
		}
		return conn;
	}
	//关闭连接
    public void freeConnection(Connection conn) {   
        if (conn != null) {   
            try {   
                conn.close(); 
                conn=null;
            } catch (SQLException e) {   
                e.printStackTrace();   
            }   
        }   
    }
    
 // 释放链接和相关资源
 	public static void free(ResultSet rs, Statement st, Connection conn) {
 		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(st!=null){
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
 	}
    
    //取得连接池信息
    private static void showSnapshotInfo() {   
        try {   
            SnapshotIF snapshot = ProxoolFacade.getSnapshot("DBPool", true);   
            int curActiveCount = snapshot.getActiveConnectionCount();//获得活动连接数   
            int availableCount = snapshot.getAvailableConnectionCount();//获得可得到连接数   
            int maxCount = snapshot.getMaximumConnectionCount();//获得总连接数   
            if (curActiveCount != availableCount)  {  //当活动连接数变化时输出的信息    
                System.out.println("活动连接数:" + curActiveCount   
                        + "(active)  可得到的连接数:" + availableCount   
                        + "(available)  总连接数:" + maxCount + "(max)");   
                availableCount = curActiveCount;   
            }   
        } catch (ProxoolException e) {   
            e.printStackTrace();   
        }   
    } 
	//测试数据库连接
	public static void main(String[] args) {
		conn = DBConn.getProxoolConn();
		System.out.println("successful!");
		//测试段
	}
	
}