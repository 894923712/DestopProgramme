package com.Vaildate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Const.SQLConst;
import com.Dao.Dao;
import com.Dao.Operator_Dao;
import com.bean.Operator;

public class AccountCheck {
	/**
	 * @author LMingX0103380
	 * @param username
	 * @param password
	 * @return boolean
	 * 对登陆的用户名和密码进行校验
	 * @throws Exception 
	 */
	
   public static boolean Check(String username,String password) throws Exception{
	   Operator_Dao odao=new Operator_Dao();
	   Operator operator=new Operator();
	   ResultSet rs=odao.selectOperatorAdmin(username,password);
	   while(rs.next()){
		   operator.setName(rs.getString("name"));
		   operator.setPassword(rs.getString("password"));
	   }
	   if(username.equals(operator.getName()) && password.equals(operator.getPassword())){
		   return true;
	   }else{
		   return false;
	   }
   }
   /**
    * 判断字符串是否为空或者null
    * @param str
    * @return
    */
   public static boolean IsEmptyOrNull(String str){
	   if(str.isEmpty() || str==null){
		   return true;
	   }else{
		   return false;
	   }
   }
   /**
    * 判断当前书本信息是否已存在
    * @param str
    * @return
    */
   public static boolean IsExistBookInfo(String ISBN) throws Exception{
	   PreparedStatement pstst=Dao.getPreparedStatement(SQLConst.SELECT_BOOK_INFO_IS_EXIST);
	   pstst.setString(1, ISBN);
	   return pstst.executeQuery().next()? true:false;
   }
   /**
    * 判断当前用户信息是否已存在
    * @param str
    * @return
    */
   public static boolean IsExistOperator(String username,String sex,int age,String identitycard) throws Exception{
	   PreparedStatement pstst=Dao.getPreparedStatement(SQLConst.SELECT_OPERATOR_IS_EXIST);
	   pstst.setString(1, username);
	   pstst.setString(2, sex);
	   pstst.setInt(3, age);
	   pstst.setString(4,identitycard);
	   return pstst.executeQuery().next()? true:false;
   }
   /**
    * 判断当前图书类别是否已存在
    * @param str
    * @return
    */
   public static boolean IsExistBookType(String typename,int days) throws Exception{
	   PreparedStatement pstst=Dao.getPreparedStatement(SQLConst.SELECT_BOOKTYPE_IS_EXIST);
	   pstst.setString(1, typename);
	   pstst.setInt(2, days);
	   return pstst.executeQuery().next()? true:false;
   }
   /**
    * 判断当前用户是管理员还是普通用户
    * @param str
    * @return
    */
   public static String IsAdminOrCommonOperator(String username,String password) throws Exception{
	 String result=null;
	 PreparedStatement pstst=Dao.getPreparedStatement(SQLConst.SELECT_OPERATOR_IS_ADMIN_OR_COMMEN);
	 pstst.setString(1, username);
	   pstst.setString(2, password);
	   ResultSet rs=pstst.executeQuery();
	   while(rs.next()){
		   if(rs.getInt("admin")==1){
			   result="管理员";
		   }else{
			   result="普通用户";
		   }
		   
	   }
	   return result;
   }
   /**
    * 判断读者是否存在
    * @param str
    * @return
    */
   public static boolean IsExistReader(String readerid) throws Exception{
	   PreparedStatement pstst=Dao.getPreparedStatement(SQLConst.IS_EXIST_READER);
	   pstst.setString(1, readerid);
	   return pstst.executeQuery().next()? true:false;	   
   }
   
}
