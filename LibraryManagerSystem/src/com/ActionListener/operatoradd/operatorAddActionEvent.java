package com.ActionListener.operatoradd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import com.Dao.Book_Info_Dao;
import com.Dao.Operator_Dao;
import com.Frame.OperatorAddFrame;
import com.Vaildate.AccountCheck;

public class operatorAddActionEvent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Operator_Dao operator_Dao=new Operator_Dao();
		OperatorAddFrame operatorAddFrame=OperatorAddFrame.getOperatorAddFrame();
		String username=operatorAddFrame.usernamefield.getText().trim();
		String sex=operatorAddFrame.sexbox.getSelectedItem().toString().trim();
		String age=operatorAddFrame.agebox.getSelectedItem().toString().trim();
		String identitycard=operatorAddFrame.identitycardfield.getText().trim();
		String workdate=operatorAddFrame.workdatefield.getText().trim();		  		
		String tel=operatorAddFrame.telfield.getText().trim();
		String password=operatorAddFrame.passwordfield.getText().trim();
		String admin=operatorAddFrame.adminbox.getSelectedItem().toString().trim();
        try {
        	 if(AccountCheck.IsEmptyOrNull(username)){
        		 JOptionPane.showMessageDialog(null, "请输入用户名");       		 
        	 }else if(AccountCheck.IsEmptyOrNull(sex)){
        		 JOptionPane.showMessageDialog(null, "请选择性别");
        	 }else if(AccountCheck.IsEmptyOrNull(age)){
        		 JOptionPane.showMessageDialog(null, "请选择年龄");
        	 }else if(AccountCheck.IsEmptyOrNull(identitycard)){
        		 JOptionPane.showMessageDialog(null, "请输入证件号");
        	 }else if(AccountCheck.IsEmptyOrNull(workdate)){
        		 JOptionPane.showMessageDialog(null, "请输入工作时间");
        	 }else if(AccountCheck.IsEmptyOrNull(tel)){
        		 JOptionPane.showMessageDialog(null, "请输入电话号码");
        	 }else if(AccountCheck.IsEmptyOrNull(admin)){
        		 JOptionPane.showMessageDialog(null, "请选择是否为管理员");
        	 }else if(AccountCheck.IsEmptyOrNull(password)){
        		 JOptionPane.showMessageDialog(null, "请输入密码");
        	 }else if(AccountCheck.IsExistOperator(username,sex,Integer.parseInt(age),identitycard)){
        		 JOptionPane.showMessageDialog(null, "添加失败！╮(╯▽╰)╭, 该用户已存在！");
        	 }else{
        		 if(operator_Dao.insertOperation(username,sex,Integer.parseInt(age),identitycard,workdate,tel,admin,password)){
    				 JOptionPane.showMessageDialog(null, "添加成功");
    			 }else{
    				 JOptionPane.showMessageDialog(null, "添加失败！╮(╯▽╰)╭");
    			 }
        	 }	 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
