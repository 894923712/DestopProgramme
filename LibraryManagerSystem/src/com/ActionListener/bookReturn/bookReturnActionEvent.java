package com.ActionListener.bookReturn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.Const.Constant;
import com.Dao.Book_Borrow_Dao;
import com.Dao.Book_Info_Dao;
import com.Dao.Operator_Dao;
import com.Frame.BookBorrowFrame;
import com.Frame.BookModiFrame;
import com.Frame.BookReturnFrame;
import com.Frame.OnloadFrame;
import com.Vaildate.AccountCheck;

public class bookReturnActionEvent implements ActionListener {
	//bookISBN ,operatorId ,readISBN ,isback ,borrowDate  
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookReturnFrame bookReturnFrame=BookReturnFrame.getBookReturnFrame();		
		Book_Borrow_Dao book_Borrow_Dao=new Book_Borrow_Dao();
		try {
			String bookISBN=bookReturnFrame.bookISBNfield.getText().trim();
			
			String readISBN=bookReturnFrame.readISBNfield.getText().trim();
			String isback=bookReturnFrame.returnbox.getSelectedItem().toString().trim();
			String returnDate=bookReturnFrame.borrowDatefield.getText().trim();
			if(AccountCheck.IsEmptyOrNull(bookISBN)){
				JOptionPane.showMessageDialog(null, "请输入图书编号");
			}else if(AccountCheck.IsEmptyOrNull(readISBN)){
				JOptionPane.showMessageDialog(null, "请输入读者编号");
			}else{
				if(book_Borrow_Dao.updateBookBorrowToReturn(isback,returnDate, bookISBN,readISBN)){
					DefaultTableModel tablemodel = new DefaultTableModel(bookReturnFrame.getBookReturnInfoAll(new Book_Borrow_Dao().selectBookReturn(readISBN)),Constant.returnDetailcolumnNames) {
		 				@Override
		 				public boolean isCellEditable(int arg0, int arg1) {
		 					// TODO Auto-generated method stub
		 					return false;
		 				}
		 			};//修改之后重新调用一次数据库渲染一下table中的数据
		 			bookReturnFrame.table.setModel(tablemodel);
		 			bookReturnFrame.table.getColumnModel().getColumn(0).setPreferredWidth(180);
		 			bookReturnFrame.table.getColumnModel().getColumn(4).setPreferredWidth(180);
		 			bookReturnFrame.table.getColumnModel().getColumn(7).setPreferredWidth(180);
		 			bookReturnFrame.table.getColumnModel().getColumn(8).setPreferredWidth(120);
					JOptionPane.showMessageDialog(null, "归还成功");
				}else{
					JOptionPane.showMessageDialog(null, "归还失败 ╮(╯▽╰)╭");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
