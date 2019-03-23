package com.ActionListener.bookTypeModi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.Const.Constant;
import com.Dao.Book_Type_Dao;
import com.Dao.Operator_Dao;
import com.Frame.BookTypeAddFrame;
import com.Frame.BookTypeModiFrame;
import com.Frame.OperatorModiFrame;
import com.Vaildate.AccountCheck;

public class bookTypeModiActionEvent implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Book_Type_Dao book_Type_Dao=new Book_Type_Dao();
		BookTypeModiFrame bookTypeModiFrame=BookTypeModiFrame.getBookTypeModiFrame();
		String typename=bookTypeModiFrame.typenamefield.getText().trim();
		String days=bookTypeModiFrame.daysfield.getText().trim();
		String fk=bookTypeModiFrame.fkfield.getText().trim();
		String typeid=null;		
        try {
        	if(AccountCheck.IsEmptyOrNull("typename")){
        		JOptionPane.showMessageDialog(null, "请输入图书类型名称");
        	}else if(AccountCheck.IsEmptyOrNull("days")){
        		JOptionPane.showMessageDialog(null, "请输入图书可借天数");
        	}else if(AccountCheck.IsEmptyOrNull("fk") || fk.equals("单位为角")){
        		JOptionPane.showMessageDialog(null, "请输入超时罚款金额");
        	}else {
        		if(!AccountCheck.IsEmptyOrNull(bookTypeModiFrame.typeid)){
            		typeid=bookTypeModiFrame.typeid;
            	}else{
            		int rownum= bookTypeModiFrame.table.getSelectedRow();
            		typeid=bookTypeModiFrame.table.getValueAt(rownum, 0).toString().trim();;
            	}       
            	
    			if(book_Type_Dao.updateBookType(Integer.parseInt(typeid))){			 
    				DefaultTableModel tablemodel=new DefaultTableModel(BookTypeModiFrame.getBookTypeAll(book_Type_Dao.selectBooktype()), Constant.booktypelumnNames){
    					 
    					 @Override
    					public boolean isCellEditable(int arg0, int arg1) {
    						// TODO Auto-generated method stub
    						return false;
    					}
    				 };//修改之后重新调用一次数据库渲染一下table中的数据
    				 bookTypeModiFrame.table.setModel(tablemodel);
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
