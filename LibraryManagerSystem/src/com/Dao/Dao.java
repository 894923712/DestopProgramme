package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.Const.SQLConst;

public class Dao {
	
	public static Statement getStatement() throws Exception{
		Statement stat=null;
		Connection conn=GetConnection.getConnection();
		stat=conn.createStatement();
		return stat;
	}
	public static PreparedStatement getPreparedStatement (String sql){
		PreparedStatement prep=null;
		try{
			Connection conn=GetConnection.getConnection();
			prep=conn.prepareStatement(sql);
			
		}catch(Exception e){			
			e.printStackTrace();
		}
		return prep;			
	}
	/**
	 * @author LMingX0103380
	 * 获取图书类型名称
	 * @return
	 * @throws Exception
	 */
	public static Vector getBookTypeName() throws Exception{
		Vector booktypelist=new Vector();
		PreparedStatement pstat=getPreparedStatement(SQLConst.SELECT_BOOKTYPE_NAME);
		ResultSet rs=pstat.executeQuery();
		while(rs.next()){
			booktypelist.add(rs.getString("typename"));
		}
		return booktypelist;
	}
}
