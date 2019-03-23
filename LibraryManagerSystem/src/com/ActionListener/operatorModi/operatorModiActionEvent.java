package com.ActionListener.operatorModi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.Const.Constant;
import com.Dao.Operator_Dao;
import com.Frame.OperatorAddFrame;
import com.Frame.OperatorModiFrame;
import com.Vaildate.AccountCheck;

public class operatorModiActionEvent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Operator_Dao operator_Dao=new Operator_Dao();		
		OperatorModiFrame operator=OperatorModiFrame.getOperatorModiFrame();
		String username=operator.usernamefield.getText().trim();
		String sex=operator.sexbox.getSelectedItem().toString().trim();
		String age=operator.agebox.getSelectedItem().toString().trim();
		String identitycard=operator.identitycardfield.getText().trim();
		String workdate=operator.workdatefield.getText().trim();		  		
		String tel=operator.telfield.getText().trim();
		String password=operator.passwordfield.getText().trim();
		String admin=operator.adminbox.getSelectedItem().toString().trim();
		String id=null;		
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
        	 }else{
        		 if(!AccountCheck.IsEmptyOrNull(operator.id)){
             		id=operator.id;
             	}else{
             		int rownum= operator.table.getSelectedRow();
                 	id=operator.table.getValueAt(rownum, 0).toString().trim();;
             	}       
             	
     			if(operator_Dao.updateOperation(Integer.parseInt(id))){			 
     				 DefaultTableModel tablemodel=new DefaultTableModel(OperatorModiFrame.getOperatorAll(new Operator_Dao().selectOperation()), Constant.operatorcolumnNames){
     					 
     					 @Override
     					public boolean isCellEditable(int arg0, int arg1) {
     						// TODO Auto-generated method stub
     						return false;
     					}
     				 };//修改之后重新调用一次数据库渲染一下table中的数据
     				 operator.table.setModel(tablemodel);
     				 operator.table.getColumnModel().getColumn(4).setPreferredWidth(180);
     				 operator.table.getColumnModel().getColumn(5).setPreferredWidth(180);
     				 operator.table.getColumnModel().getColumn(6).setPreferredWidth(120);
     				 JOptionPane.showMessageDialog(null, "修改成功");
     				
     				 
     			 }else{
     				 JOptionPane.showMessageDialog(null, "修改失败！╮(╯▽╰)╭");
     			 }
        	 }
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
