package com.ActionListener.bookborrow;

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
import com.Frame.OnloadFrame;
import com.Vaildate.AccountCheck;
import com.Vaildate.DaoCheck;

public class bookBorrowActionEvent implements ActionListener {
	//bookISBN ,operatorId ,readISBN ,isback ,borrowDate  
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookBorrowFrame bookBorrowFrame=BookBorrowFrame.getBookBorrowFrame();
		OnloadFrame onloadFrame=OnloadFrame.getOnloadFrame();
		Book_Borrow_Dao book_Borrow_Dao=new Book_Borrow_Dao();
		Operator_Dao operator_Dao=new Operator_Dao();	
		String username=onloadFrame.usernamefield.getText().trim();
		String password=new String(onloadFrame.passwordfield.getPassword());
		try {
			String bookISBN=bookBorrowFrame.bookISBNfield.getText().trim();
			int operatorId=operator_Dao.selectOperatorIdByNameAndPassWord(username, password);
			String readISBN=bookBorrowFrame.readISBNfield.getText().trim();
			String isback=bookBorrowFrame.returnbox.getSelectedItem().toString().trim();
			String borrowDate=bookBorrowFrame.borrowDatefield.getText().trim();
			if(AccountCheck.IsEmptyOrNull(bookISBN)){
				JOptionPane.showMessageDialog(null, "请输入图书编号");
			}else if(AccountCheck.IsEmptyOrNull(readISBN)){
				JOptionPane.showMessageDialog(null, "请输入读者编号");
			}else if(DaoCheck.isReaderEffectiveVip(readISBN)){
				JOptionPane.showMessageDialog(null, "当前读者会员已过期");
			}else if(DaoCheck.isMoreThanMaxNum(readISBN)){
				JOptionPane.showMessageDialog(null, "当前读者借书量已满，不能在借书了");
			}else{
				if(book_Borrow_Dao.insertBook_Boorrow(bookISBN, operatorId, readISBN, isback, borrowDate)){
					DefaultTableModel tablemodel = new DefaultTableModel(bookBorrowFrame.getBookBorrowInfoAll(new Book_Borrow_Dao().selectBookBorrow(readISBN)),Constant.borrowDetailcolumnNames) {
		 				@Override
		 				public boolean isCellEditable(int arg0, int arg1) {
		 					// TODO Auto-generated method stub
		 					return false;
		 				}
		 			};//修改之后重新调用一次数据库渲染一下table中的数据
		 			bookBorrowFrame.table.setModel(tablemodel);
		 			bookBorrowFrame.table.getColumnModel().getColumn(0).setPreferredWidth(180);
		 			bookBorrowFrame.table.getColumnModel().getColumn(4).setPreferredWidth(180);
		 			bookBorrowFrame.table.getColumnModel().getColumn(5).setPreferredWidth(180);
		 			bookBorrowFrame.table.getColumnModel().getColumn(6).setPreferredWidth(120);
					JOptionPane.showMessageDialog(null, "借出成功");
				}else{
					JOptionPane.showMessageDialog(null, "借出失败 ╮(╯▽╰)╭");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
