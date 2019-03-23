package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Const.SQLConst;
import com.Frame.OnloadFrame;
import com.Frame.OperatorAddFrame;
import com.Frame.OperatorModiFrame;
import com.bean.Operator;

public class Operator_Dao {
	/**
	 * 管理员用户名和密码查询
	 * @return
	 * @throws Exception 
	 */
	public  ResultSet selectOperatorAdmin(String username,String password) throws Exception{		
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.SELECT_OPERATOR);
		pstas.setString(1, username);
		pstas.setString(2, password);
		return pstas.executeQuery();		
	}
	/**
	 * 修改指定的用户名和密码
	 * @return
	 * @throws Exception 
	 */
	public boolean updateOperationPassWord(String username,String password) throws Exception{
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.UPDATEOPERATORPASSWORD);
		pstas.setString(1, password);
		pstas.setString(2, username);
		return pstas.executeUpdate()>0;	
	}
	
	/**
	 * 添加用户
	 * @return
	 * @throws Exception 
	 */
	public boolean insertOperation(String username,String sex,int age,String identitycard,String workdate,String tel,String admin,String password) throws Exception{
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.INSERTOPERATOR);
		pstas.setString(1, username);
		pstas.setString(2, sex);
		pstas.setInt(3, age);
		pstas.setString(4, identitycard);		
		pstas.setObject(5,  new java.sql.Timestamp(new Date(workdate).getTime()));		
		pstas.setString(6, tel);
		if(admin.equals("管理员")){
			pstas.setInt(7, 1);
		}else{
			pstas.setInt(7, 0);
		}
		pstas.setString(8, password);
		return pstas.executeUpdate()>0;	
	}
	/**
	 * 修改用户
	 * @return
	 * @throws Exception 
	 */
	public boolean updateOperation(int id) throws Exception{
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.UPDATEOPERATOR);
		OperatorModiFrame operatorModiFrame=OperatorModiFrame.getOperatorModiFrame();
		pstas.setString(1, operatorModiFrame.usernamefield.getText());
		pstas.setString(2, operatorModiFrame.sexbox.getSelectedItem().toString());
		pstas.setInt(3, Integer.parseInt(operatorModiFrame.agebox.getSelectedItem().toString()));
		pstas.setString(4, operatorModiFrame.identitycardfield.getText());
		String workdate=operatorModiFrame.workdatefield.getText();
		pstas.setObject(5,  new java.sql.Timestamp(new Date(workdate).getTime()));		
		pstas.setString(6, operatorModiFrame.telfield.getText());
		if(operatorModiFrame.adminbox.getSelectedItem().toString().equals("管理员")){
			pstas.setInt(7, 1);
		}else{
			pstas.setInt(7, 0);
		}
		pstas.setString(8, OperatorModiFrame.getOperatorModiFrame().passwordfield.getText());
		pstas.setInt(9, id);
		return pstas.executeUpdate()>0;	
	}
	
	/**
	 * 查询所有的用户
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	public List<Operator> selectOperation() throws Exception{
		List<Operator> operatorList=new ArrayList<Operator>();		
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.SELECTOPERATORALL);
		ResultSet rs=pstas.executeQuery();		
		while(rs.next()){
			Operator operator=new Operator();
			operator.setId(rs.getInt("id"));
			operator.setName(rs.getString("name"));
			operator.setSex(rs.getString("sex"));
			operator.setAge(rs.getInt("age"));
			operator.setIdentitycard(rs.getString("identitycard"));
			operator.setTel(rs.getString("tel"));
			operator.setWorkDate(rs.getTimestamp("workdate"));
			operator.setAdmin(rs.getInt("admin"));
			operator.setPassword(rs.getString("password"));
			operatorList.add(operator);
		}
		return operatorList;	
	}
	/**
	 * 根据id查询指定的用户
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	public Operator selectOperationById(int id) throws Exception{
		Operator operator=new Operator();
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.SELECTOPERATORBYID);
		pstas.setInt(1, id);
		ResultSet rs=pstas.executeQuery();		
		while(rs.next()){			
			operator.setId(rs.getInt("id"));
			operator.setName(rs.getString("name"));
			operator.setSex(rs.getString("sex"));
			operator.setAge(rs.getInt("age"));
			operator.setIdentitycard(rs.getString("identitycard"));
			operator.setTel(rs.getString("tel"));
			operator.setWorkDate(rs.getDate("workdate"));
			operator.setAdmin(rs.getInt("admin"));
			operator.setPassword(rs.getString("password"));			
		}		
		return operator;	
	}
	/**
	 * 批量删除指定的用户
	 * @author LMingX0103380
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteOperationByIds(int[] ids) throws Exception{
		Connection conn=GetConnection.getConnection();
		int[] result=new int[ids.length];
		int count=0;
		try {
			
			PreparedStatement pstas=conn.prepareStatement(SQLConst.DELETEOPERATORALL);
			conn.setAutoCommit(false);//把Auto commit设置为false
			for (int i = 0; i < ids.length; i++) {
				pstas.setInt(1, ids[i]);
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
	 * @param name
	 * @param passWord
	 * @return
	 * @throws Exception
	 * 根据用户名和密码查询当前登录用户的ID
	 */
	public int selectOperatorIdByNameAndPassWord(String name,String passWord) throws Exception{
		int id=0;
		PreparedStatement pstas=Dao.getPreparedStatement(SQLConst.SELECT_OPERATORID_BY_NAME_AND_PASSWORD);
		pstas.setString(1, name);
		pstas.setString(2, passWord);
		ResultSet rs=pstas.executeQuery();
		while(rs.next()){
			id=rs.getInt("id");
		}
		return id;
	}
}
