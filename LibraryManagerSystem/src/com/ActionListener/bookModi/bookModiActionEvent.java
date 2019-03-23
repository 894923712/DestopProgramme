package com.ActionListener.bookModi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.Const.Constant;
import com.Dao.Book_Info_Dao;
import com.Dao.Operator_Dao;
import com.Frame.BookAddFrame;
import com.Frame.BookModiFrame;
import com.Frame.OperatorModiFrame;
import com.Vaildate.AccountCheck;

public class bookModiActionEvent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Book_Info_Dao bookinfo_Dao=new Book_Info_Dao();
		BookModiFrame bookModiFrame=BookModiFrame.getBookModiFrame();		 
		String TypeName=bookModiFrame.categorybox.getSelectedItem().toString().trim();
		String BookName=bookModiFrame.booknamefield.getText().trim();
		String Author=bookModiFrame.authorfield.getText().trim();
		String Translator=bookModiFrame.translatorfield.getText().trim();
		String Publish=bookModiFrame.publishbox.getSelectedItem().toString().trim();
		String Pubdate=bookModiFrame.pubdatefield.getText().trim();		
		String Perprice=bookModiFrame.perpricefield.getText().trim();
		String ISBN=null;
        try {
          if(AccountCheck.IsEmptyOrNull(TypeName)){
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
       	 }else{
       		if(!AccountCheck.IsEmptyOrNull(bookModiFrame.booknumfield.getText())){
        		ISBN=bookModiFrame.booknumfield.getText();
        	}else{
        		int rownum= bookModiFrame.table.getSelectedRow();
            	ISBN=bookModiFrame.table.getValueAt(rownum, 0).toString().trim();
        	}       	
			if(bookinfo_Dao.updateBook_info(ISBN)){			 
				 DefaultTableModel tablemodel=new DefaultTableModel(BookModiFrame.getBookInfoAll(bookinfo_Dao.selectBookInfo()), Constant.bookinfocolumnNames){
					 @Override
					public boolean isCellEditable(int arg0, int arg1) {
						// TODO Auto-generated method stub
						return false;
					}
					 
				 };//修改之后重新调用一次数据库渲染一下table中的数据
				 bookModiFrame.table.setModel(tablemodel);
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
