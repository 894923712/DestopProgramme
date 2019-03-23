package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Const.SQLConst;
import com.Frame.BookTypeAddFrame;
import com.Frame.BookTypeModiFrame;
import com.Frame.OperatorModiFrame;
import com.bean.Book_Info;
import com.bean.Book_Type;
import com.bean.Operator;

public class Book_Type_Dao {
	/**
	 * 图书类别插入
	 * @return
	 * @throws Exception 
	 */
	public boolean insertBook_Type(String typename,String days,String fk) throws Exception{
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.INSERT_BOOKTYPE);
		pstas.setString(1, typename);
		pstas.setInt(2, Integer.parseInt(days));
		pstas.setDouble(3, Double.parseDouble(fk));
		return pstas.executeUpdate()>0;		
	}
	/**
	 * 查询所有的图书类别信息
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	public List<Book_Type> selectBooktype() throws Exception{
		List<Book_Type> Book_Type_List=new ArrayList<Book_Type>();		
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.SELECT_BOOK_TYPE_ALL);
		ResultSet rs=pstas.executeQuery();		
		while(rs.next()){
			Book_Type book_Info=new Book_Type();
			book_Info.setTypeId(rs.getInt("typeid"));			
			book_Info.setTypename(rs.getString("typename"));
			book_Info.setDays(rs.getInt("days"));
			book_Info.setFk(rs.getDouble("fk"));			
			Book_Type_List.add(book_Info);
		}
		return Book_Type_List;	
	}
	/**
	 * 修改图书类型
	 * @return
	 * @throws Exception 
	 * @throws Exception 
	 */
	public boolean updateBookType(int typeid) throws Exception{
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.UPDATE_BOOK_TYPE_BY_TYPEID);
		BookTypeModiFrame bookTypeModiFrame=BookTypeModiFrame.getBookTypeModiFrame();
		pstas.setString(1, bookTypeModiFrame.typenamefield.getText().trim());
		pstas.setInt(2, Integer.parseInt(bookTypeModiFrame.daysfield.getText().trim()));
		pstas.setDouble(3, Double.parseDouble(bookTypeModiFrame.fkfield.getText().trim()));
		pstas.setInt(4, typeid);
		return pstas.executeUpdate()>0;	
	}
	/**
	 * 批量删除指定的图书类型
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteBookTypeByTypeids(int[] typeids) throws Exception{
		Connection conn=GetConnection.getConnection();
		int[] result=new int[typeids.length];
		int count=0;
		try {
			
			PreparedStatement pstas=conn.prepareStatement(SQLConst.DELETEBOOKTYPEBYTYPEIDS);
			conn.setAutoCommit(false);//把Auto commit设置为false
			for (int i = 0; i < typeids.length; i++) {
				pstas.setInt(1, typeids[i]);
				pstas.addBatch();   
			}
			result=pstas.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			GetConnection.Close(conn);
			GetConnection.ClosePStat(pstas);
		} catch (Exception e) {
			// TODO: handle exception
			if(!conn.isClosed()){
				conn.rollback();
				conn.setAutoCommit(true);   
			}
		}
		for (int i = 0; i < result.length; i++) {
			count=count+result[i];
		}
		if(count==result.length){
			return true;
		}else{
			return false;
		}		 
		
	}
}
