package com.Vaildate;

import com.Dao.Book_Borrow_Dao;

public class DaoCheck {
	/**
	 * @author LMingX0103380
	 * 判断当前读者借书量是否超过最大借书量
	 * @return
	 * @throws Exception
	 */
     public static boolean  isMoreThanMaxNum(String readISBN) throws Exception{
    	 Book_Borrow_Dao borrowdao=new Book_Borrow_Dao();
    	 int result=borrowdao.selectIsMoreThanMaxNum(readISBN);
    	 return result==1?true:false;
     }
     /**
 	 * @author LMingX0103380
 	 * 判断当前读者的会员证是否在有效期内
 	 * @return
 	 * @throws Exception
 	 */
     public static boolean isReaderEffectiveVip(String readISBN) throws Exception{
    	 Book_Borrow_Dao borrowdao=new Book_Borrow_Dao();
    	 int result=borrowdao.selectReaderEffectiveVip(readISBN);
    	 return result==1?true:false;
     }
}
