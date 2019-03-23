package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.Const.SQLConst;
import com.bean.Book_Borrow;
import com.bean.Book_Order;

public class Book_Order_Dao {
	/**
	 * @author LMingX0103380
	 * 获取当前图书没有验收的订购信息
	 * @return
	 * @throws Exception
	 */
	public List<Book_Order> selectBookBorrow() throws Exception{
		List<Book_Order> bookorderList=new ArrayList<Book_Order>();		
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.SELECT_ORDER_BOOK);		
		ResultSet rs=pstas.executeQuery();		
		while(rs.next()){
			Book_Order book_Order=new Book_Order();
			book_Order.setISBN(rs.getString("ISBN"));			
			book_Order.setOrder_date(rs.getTimestamp("order_date"));
			book_Order.setNumber(rs.getInt("number"));
			book_Order.setOperator(rs.getString("orderOperator"));
			book_Order.setCheckandaccept(rs.getInt("checkandaccept"));
			book_Order.setZk(rs.getDouble("zk"));
			book_Order.setCheckOperator(rs.getString("checkOperator"));
			book_Order.setCheck_date(rs.getTimestamp("check_date"));
			bookorderList.add(book_Order);
		}
		return bookorderList;	
	}
	
	/**
	 * 订购新书信息插入
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	//bookISBN ,operatorId ,readISBN ,isback ,borrowDate ,backDate 
	public boolean insertBook_Order(String bookISBN,String orderDate,String orderNum,String operator,int checkandaccept,String zk) throws Exception{
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.INSERT_ORDER_BOOK);
		pstas.setString(1, bookISBN);
		pstas.setObject(2, new java.sql.Date(new Date(orderDate).getTime()));
		pstas.setInt(3, Integer.parseInt(orderNum));
		pstas.setString(4, operator);			
		pstas.setInt(5, checkandaccept);		
		pstas.setDouble(6, Double.parseDouble(zk));
		return pstas.executeUpdate()>0;		
	}
	/**
	 * 批量删除当前未验收的新订书籍
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteBook_Order(String[] bookISBN) throws Exception{
		Connection conn=GetConnection.getConnection();
		int[] result=new int[bookISBN.length];
		int count=0;
		try {	
			PreparedStatement pstas=conn.prepareStatement(SQLConst.DELETE_ORDER_BOOK);
			conn.setAutoCommit(false);//把Auto commit设置为false
			for (int i = 0; i < bookISBN.length; i++) {
				pstas.setString(1, bookISBN[i]);
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
	
	/**
	 * 订购新书信息修改
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	//bookISBN ,operatorId ,readISBN ,isback ,borrowDate ,backDate 
	public boolean updateBook_Order(int checkandaccept,String checkOperator ,String check_date,String ISBN) throws Exception{
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.UPDATE_ORDER_BOOK);	
		pstas.setInt(1, checkandaccept);
		pstas.setString(2, checkOperator);
		pstas.setObject(3, new java.sql.Timestamp(new Date(check_date).getTime()));
		pstas.setString(4, ISBN);					
		return pstas.executeUpdate()>0;		
	}
}
