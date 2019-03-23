package com.ActionListener.bookadd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import com.Dao.Book_Info_Dao;
import com.Frame.BookAddFrame;
import com.Vaildate.AccountCheck;

public class bookAddActionEvent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		  Book_Info_Dao book_Info_Dao=new Book_Info_Dao();
		  BookAddFrame bookAddFrame=BookAddFrame.getBookAddFrame();
		  String ISBN=bookAddFrame.booknumfield.getText().trim();
		  String TypeName=bookAddFrame.categorybox.getSelectedItem().toString().trim();
		  String BookName=bookAddFrame.booknamefield.getText().trim();
		  String Author=bookAddFrame.authorfield.getText().trim();
		  String Translator=bookAddFrame.translatorfield.getText().trim();
		  String Publish=bookAddFrame.publishbox.getSelectedItem().toString().trim();
		  String Pubdate=bookAddFrame.pubdatefield.getText().trim();		
		  String Perprice=bookAddFrame.perpricefield.getText().trim();
         try {
        	 if(AccountCheck.IsEmptyOrNull(ISBN)){
        		 JOptionPane.showMessageDialog(null, "请输入图书编号");       		 
        	 }else if(AccountCheck.IsEmptyOrNull(TypeName)){
        		 JOptionPane.showMessageDialog(null, "请输入图书类别");
        	 }else if(AccountCheck.IsEmptyOrNull(BookName)){
        		 JOptionPane.showMessageDialog(null, "请输入图书名称");
        	 }else if(AccountCheck.IsEmptyOrNull(Author)){
        		 JOptionPane.showMessageDialog(null, "请输入作者");
        	 }else if(AccountCheck.IsEmptyOrNull(Translator)){
        		 JOptionPane.showMessageDialog(null, "请输入译者");
        	 }else if(AccountCheck.IsEmptyOrNull(Publish)){
        		 JOptionPane.showMessageDialog(null, "请输入出版社");
        	 }else if(AccountCheck.IsEmptyOrNull(Pubdate)){
        		 JOptionPane.showMessageDialog(null, "请输入出版日期");
        	 }else if(AccountCheck.IsEmptyOrNull(Perprice)){
        		 JOptionPane.showMessageDialog(null, "请输入单价");
        	 }else if(AccountCheck.IsExistBookInfo(ISBN)){
        		 JOptionPane.showMessageDialog(null, "添加失败！╮(╯▽╰)╭, 该图书已存在！");
        	 }else{	 
        		 if(book_Info_Dao.insertBook_info(ISBN,TypeName,BookName,Author, Translator,Publish,Pubdate,Perprice)){
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
