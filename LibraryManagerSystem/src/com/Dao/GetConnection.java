package com.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.Const.Constant;


public class GetConnection {
		  /**
		   * 这是获取ubuntu下的mysql的连接
		   * @author LMingX0103380
		   * @return
		   */
      public static Connection getConnection() {
    	  Connection conn=null;
    	  try {
			Class.forName(Constant.DRIVER);
			conn=DriverManager.getConnection(Constant.URL, Constant.USERNAME, Constant.PASSWORD);			
			
		    } catch (Exception e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		    }
		return conn;		  	    	  
      }
      /**
	   * 这是关闭ubuntu下的mysql的连接
	   * @author LMingX0103380
	   * @return
	   */
      public static void Close(Connection conn){
    	  try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn=null;
		}
    	  
      }
      /**
	   * 这是关闭ubuntu下的mysql的连接
	   * @author LMingX0103380
	   * @return
	   */
      public static void ClosePStat(PreparedStatement pstat){
    	  try {
    		  pstat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			pstat=null;
		}
    	  
      }
}
