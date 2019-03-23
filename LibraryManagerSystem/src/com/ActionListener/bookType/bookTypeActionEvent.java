package com.ActionListener.bookType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.Dao.Book_Type_Dao;
import com.Frame.BookTypeAddFrame;
import com.Vaildate.AccountCheck;

public class bookTypeActionEvent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Book_Type_Dao book_Type_Dao=new Book_Type_Dao();
		BookTypeAddFrame bookTypeAddFrame=BookTypeAddFrame.getBookTypeAddFrame();
		String typename=bookTypeAddFrame.typenamefield.getText().trim();
		String days=bookTypeAddFrame.daysfield.getText().trim();
		String fk=bookTypeAddFrame.fkfield.getText().trim();
        try {
        	if(AccountCheck.IsEmptyOrNull("typename")){
        		JOptionPane.showMessageDialog(null, "请输入图书类型名称");
        	}else if(AccountCheck.IsEmptyOrNull("days")){
        		JOptionPane.showMessageDialog(null, "请输入图书可借天数");
        	}else if(AccountCheck.IsEmptyOrNull("fk") || fk.equals("单位为角")){
        		JOptionPane.showMessageDialog(null, "请输入超时罚款金额");
        	}else if(AccountCheck.IsExistBookType(typename, Integer.parseInt(days))){
        		JOptionPane.showMessageDialog(null, "该图书类别已存在");
        	}else{
        		if(book_Type_Dao.insertBook_Type(typename,days,fk)){
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
