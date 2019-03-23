package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.Const.SQLConst;
import com.bean.Book_Borrow;


public class Book_Borrow_Dao {

	/**
	 * @author LMingX0103380
	 * 获取当前读者的借阅图书信息
	 * @return
	 * @throws Exception
	 */
	public List<Book_Borrow> selectBookBorrow(String readISBN) throws Exception{
		List<Book_Borrow> bookborrowList=new ArrayList<Book_Borrow>();		
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.SELECT_BOOK_BORROW_ALL);
		pstas.setString(1, readISBN);
		ResultSet rs=pstas.executeQuery();		
		while(rs.next()){
			Book_Borrow book_Borrow=new Book_Borrow();
			book_Borrow.setBookISBN(rs.getString("ISBN"));			
			book_Borrow.setBookName(rs.getString("bookname"));
			book_Borrow.setReaderName(rs.getString("name"));
			book_Borrow.setIsback(rs.getInt("isback"));
			book_Borrow.setBorrowDate(rs.getTimestamp("borrowDate"));
			book_Borrow.setBackDate(rs.getTimestamp("backDate"));
			book_Borrow.setOperatorname(rs.getString("operatorname"));
			bookborrowList.add(book_Borrow);
		}
		return bookborrowList;	
	}
	/**
	 * 借书信息插入
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	//bookISBN ,operatorId ,readISBN ,isback ,borrowDate ,backDate 
	public boolean insertBook_Boorrow(String bookISBN,int  operatorId,String readISBN,String isback,String borrowDate) throws Exception{
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.INSERT_BOOK_BORROW);
		pstas.setString(1, bookISBN);
		pstas.setInt(2, operatorId);
		pstas.setString(3, readISBN);
		if(isback.equals("借出")){
			pstas.setInt(4, 0);//借出为0
		}else{
			pstas.setInt(4, 1);
		}		
		pstas.setObject(5, new java.sql.Timestamp(new Date(borrowDate).getTime()));
		return pstas.executeUpdate()>0;		
	}
	
	/**
	 * 批量删除用户借阅信息
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteBook_Borrow(Map<String,String> map ) throws Exception{
		Connection conn=GetConnection.getConnection();
		int[] result=new int[map.size()];
		int count=0;
		try {	
			PreparedStatement pstas=conn.prepareStatement(SQLConst.DELETE_BOOK_BORROW);
			conn.setAutoCommit(false);//把Auto commit设置为false
			for (Map.Entry<String, String> entry : map.entrySet()) {
				pstas.setString(1, entry.getKey());
				pstas.setString(2, entry.getValue());
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
	 * @author LMingX0103380
	 * 获取归还图书信息
	 * @return
	 * @throws Exception
	 */
	public List<Book_Borrow> selectBookReturn(String readISBN) throws Exception{
		List<Book_Borrow> bookreturnList=new ArrayList<Book_Borrow>();		
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.SELECT_BOOK_RETURN_ALL);
		pstas.setString(1, readISBN);
		pstas.setString(2, readISBN);
		ResultSet rs=pstas.executeQuery();		
		while(rs.next()){
			Book_Borrow book_Borrow=new Book_Borrow();
			book_Borrow.setBookISBN(rs.getString("bookISBN"));			
			book_Borrow.setBookName(rs.getString("bookname"));
			book_Borrow.setReaderName(rs.getString("name"));
			book_Borrow.setIsback(rs.getInt("isback"));
			book_Borrow.setBackDate(rs.getTimestamp("backDate"));
			book_Borrow.setFk(rs.getDouble("fk"));
			book_Borrow.setAmountfk(rs.getDouble("amountfk"));
			book_Borrow.setBorrowDate(rs.getTimestamp("borrowDate"));
			book_Borrow.setOperatorname(rs.getString("operatorname"));
			bookreturnList.add(book_Borrow);
		}
		return bookreturnList;	
	}
	/**
	 * @author LMingX0103380
	 * 修改借出信息，将图书归还
	 * @return
	 * @throws Exception
	 */
	public boolean updateBookBorrowToReturn(String isback,String backDate,String bookISBN,String  readISBN) throws Exception{
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.UPDATE_BOOK_BORROW_TO_RETURN);
		if(isback.equals("归还")){
			pstas.setInt(1, 1);
		}else{
			pstas.setInt(1, 0);
		}		
		pstas.setObject(2, new java.sql.Timestamp(new Date(backDate).getTime()));
		pstas.setString(3, bookISBN);
		pstas.setString(4, readISBN);		
		return pstas.executeUpdate()>0;
	}
	/**
	 * @author LMingX0103380
	 * 判断当前用户借书量是否超过最大借书量
	 * @return
	 * @throws Exception
	 */
	public int selectIsMoreThanMaxNum(String readISBN) throws Exception{
		int result=-1;
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.IS_MORE_THAN_MAX_NUM);
		pstas.setString(1, readISBN);
		pstas.setString(2, readISBN);
		ResultSet rs=pstas.executeQuery();
		while(rs.next()){
			result=rs.getInt("ismorethanmaxnum");
		}
		return result;
	}
	/**
	 * @author LMingX0103380
	 * 判断当前读者会员是否有效
	 * @return
	 * @throws Exception
	 */
	public int selectReaderEffectiveVip(String readISBN) throws Exception{
		int result=-1;
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.IS_EFFECTIVE_VIP);
		pstas.setString(1, readISBN);
		ResultSet rs=pstas.executeQuery();
		while(rs.next()){
			result=rs.getInt("iseffectiveVip");
		}
		return result;
	}
}
