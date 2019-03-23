package com.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Const.Constant;
import com.Const.SQLConst;
import com.Vaildate.AccountCheck;
import com.bean.Book_Borrow;

public class Book_SearchDao {
	/**
	 * @author LMingX0103380
	 *  获取所有的借出和归还信息
	 * @return
	 * @throws Exception
	 */
	public List<Book_Borrow> selectSearchBookInfoAll() throws Exception{
		List<Book_Borrow> bookreturnList=new ArrayList<Book_Borrow>();		
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.SEARCH_BORROW_RETURN_INFO_ALL);
		ResultSet rs=pstas.executeQuery();		
		while(rs.next()){
			Book_Borrow book_Borrow=new Book_Borrow();
			book_Borrow.setBookISBN(rs.getString("ISBN"));			
			book_Borrow.setBookName(rs.getString("bookname"));
			book_Borrow.setBookTypeName(rs.getString("typename"));
			book_Borrow.setReaderName(rs.getString("name"));
			book_Borrow.setReadISBN(rs.getString("readerid"));
			book_Borrow.setIsback(rs.getInt("isback"));
			book_Borrow.setBackDate(rs.getTimestamp("backDate"));
			book_Borrow.setBorrowDate(rs.getTimestamp("borrowDate"));
			book_Borrow.setOperatorname(rs.getString("operatorname"));
			bookreturnList.add(book_Borrow);
		}
		return bookreturnList;	
	}
	
	/**
	 * @author LMingX0103380
	 *  根据过滤条件获取所有的借出和归还信息
	 * @return
	 * @throws Exception
	 */
	public List<Book_Borrow> selectSearchBookInfoAllByFilter(String bookISBN,String bookname,String typename,String name,String readerid, String isback,String backDate,String borrowDate,String operatorname) throws Exception{
		List<Book_Borrow> bookreturnList=new ArrayList<Book_Borrow>();	
		PreparedStatement pstas=null;
		String whereStr="";
		int num=1;
		if(!AccountCheck.IsEmptyOrNull(bookISBN)){
			whereStr+=" and a.bookISBN=?";
			pstas.setString(num, bookISBN);
			num=num+1;
		}
		if(!AccountCheck.IsEmptyOrNull(bookname)){
			whereStr+=" and b.bookname=?";
			pstas.setString(num, bookname);
			num=num+1;
		}
		if(!AccountCheck.IsEmptyOrNull(typename)){
			whereStr+=" and t.typename=?";
			pstas.setString(num, typename);
			num=num+1;
		}
		
		if(!AccountCheck.IsEmptyOrNull(name)){
			whereStr+=" and r.name=?";
			pstas.setString(num, name);
			num=num+1;
		}
		if(!AccountCheck.IsEmptyOrNull(readerid)){
			whereStr+=" and r.readerid=?";
			pstas.setString(num, readerid);
			num=num+1;
		}
		if(!isback.equals(Constant.returnboxContent[0])){
			whereStr+=" and a.isback=?";
			if(isback.equals(Constant.returnboxContent[2])){
				pstas.setInt(num, 1);
				num=num+1;
			}else{
				pstas.setInt(num, 0);
				num=num+1;
			}		
		}
		if(!AccountCheck.IsEmptyOrNull(backDate)){
			whereStr+=" and a.backDate=?";
			pstas.setObject(num, new java.sql.Timestamp(new Date(backDate).getTime()));
			num=num+1;
		}
		if(!AccountCheck.IsEmptyOrNull(borrowDate)){
			whereStr+=" and a.borrowDate=?";
			pstas.setObject(num, new java.sql.Timestamp(new Date(borrowDate).getTime()));
			num=num+1;
		}
		if(!AccountCheck.IsEmptyOrNull(operatorname)){
			whereStr+=" and o.name=?";
			pstas.setString(num, operatorname);
		}
		pstas=Dao.getPreparedStatement(SQLConst.SEARCH_BORROW_RETURN_INFO_ALL+whereStr);
		
		ResultSet rs=pstas.executeQuery();		
		while(rs.next()){
			Book_Borrow book_Borrow=new Book_Borrow();
			book_Borrow.setBookISBN(rs.getString("ISBN"));			
			book_Borrow.setBookName(rs.getString("bookname"));
			book_Borrow.setBookTypeName(rs.getString("typename"));
			book_Borrow.setReaderName(rs.getString("name"));
			book_Borrow.setReadISBN(rs.getString("readerid"));
			book_Borrow.setIsback(rs.getInt("isback"));
			book_Borrow.setBackDate(rs.getTimestamp("backDate"));
			book_Borrow.setBorrowDate(rs.getTimestamp("borrowDate"));
			book_Borrow.setOperatorname(rs.getString("operatorname"));
			bookreturnList.add(book_Borrow);
		}
		return bookreturnList;	
	}
}
