package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Const.SQLConst;
import com.Frame.BookAddFrame;
import com.Frame.BookModiFrame;
import com.Frame.OperatorAddFrame;
import com.bean.Book_Info;
import com.bean.Operator;

public class Book_Info_Dao {
	/**
	 * 图书信息插入
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	public boolean insertBook_info(String ISBN,String TypeName,String BookName,String Author,String Translator,String Publish,String Pubdate,String Perprice) throws Exception{		
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.INSERT_BOOK_INFO);
		pstas.setString(1, ISBN);
		pstas.setInt(2, selectBookTypeId(TypeName));
		pstas.setString(3, BookName);
		pstas.setString(4, Author);
		pstas.setString(5, Translator);
		pstas.setString(6, Publish);
		pstas.setObject(7, new java.sql.Timestamp(new Date(Pubdate).getTime()));
		pstas.setDouble(8, Double.parseDouble(Perprice));
		return pstas.executeUpdate()>0;		
	}
	/**
	 * 图书信息修改
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	public boolean updateBook_info(String ISBN) throws Exception{		
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.UPDATE_BOOK_INFO);
		BookModiFrame bookModiFrame=BookModiFrame.getBookModiFrame();		
		pstas.setInt(1, selectBookTypeId(bookModiFrame.categorybox.getSelectedItem().toString().trim()));
		pstas.setString(2, bookModiFrame.booknamefield.getText().trim());
		pstas.setString(3, bookModiFrame.authorfield.getText().trim());
		pstas.setString(4, bookModiFrame.translatorfield.getText().trim());
		pstas.setString(5, bookModiFrame.publishbox.getSelectedItem().toString().trim());
		String pubdate=bookModiFrame.pubdatefield.getText();
		pstas.setObject(6, new java.sql.Timestamp(new Date(pubdate).getTime()));
		pstas.setDouble(7, Double.parseDouble(bookModiFrame.perpricefield.getText().trim()));
		pstas.setString(8, ISBN);
		return pstas.executeUpdate()>0;		
	}
	/**
	 * 图书信息删除
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteBook_info() throws Exception{		
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.DELETE_BOOK_INFO);		
		return pstas.executeUpdate()>0;		
	}
	/**
	 * 图书类别ID查询
	 * @return
	 * @throws Exception 
	 */
	public static int selectBookTypeId(String typename) throws Exception{
		int typeId=-1;
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.SELECT_BOOKTYPEID);
		pstas.setString(1, typename);
		ResultSet rs=pstas.executeQuery();
		while(rs.next()){
			typeId=rs.getInt("typeid");
		}
		return typeId;
	}
	
	/**
	 * 查询所有的图书信息
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	public List<Book_Info> selectBookInfo() throws Exception{
		List<Book_Info> bookinfoList=new ArrayList<Book_Info>();		
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.SELECT_BOOK_INFOALL);
		ResultSet rs=pstas.executeQuery();		
		while(rs.next()){
			Book_Info book_Info=new Book_Info();
			book_Info.setISBN(rs.getString("iSBN"));			
			book_Info.setTypeName(rs.getString("typename"));
			book_Info.setBookName(rs.getString("bookname"));
			book_Info.setWriter(rs.getString("writer"));
			book_Info.setTranslator(rs.getString("translator"));
			book_Info.setPublisher(rs.getString("publisher"));
			book_Info.setPubllishDate(rs.getTimestamp("publishdate"));
			book_Info.setPrice(rs.getDouble("price"));
			bookinfoList.add(book_Info);
		}
		return bookinfoList;	
	}
	/**
	 * 批量删除指定的图书信息
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteBookInfoByISBNs(String[] ISBNs) throws Exception{
		Connection conn=GetConnection.getConnection();
		int[] result=new int[ISBNs.length];
		int count=0;
		try {
			
			PreparedStatement pstas=conn.prepareStatement(SQLConst.DELETE_BOOK_INFO);
			conn.setAutoCommit(false);//把Auto commit设置为false
			for (int i = 0; i < ISBNs.length; i++) {
				pstas.setString(1, ISBNs[i]);
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
