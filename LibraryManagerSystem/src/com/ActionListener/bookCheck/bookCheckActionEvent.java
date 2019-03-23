package com.ActionListener.bookCheck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.Const.Constant;
import com.Dao.Book_Order_Dao;
import com.Frame.BookOrderCheckFrame;
import com.Frame.OnloadFrame;
import com.Vaildate.AccountCheck;

public class bookCheckActionEvent implements ActionListener {
	//bookISBN ,operatorId ,readISBN ,isback ,borrowDate  
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookOrderCheckFrame bookOrderCheckFrame=BookOrderCheckFrame.getBookOrderCheckFrame();
		OnloadFrame onloadFrame=OnloadFrame.getOnloadFrame();
		Book_Order_Dao book_Order_Dao=new Book_Order_Dao();			
		try {
			String bookISBN=bookOrderCheckFrame.bookISBNfield.getText().trim();		
			String checkDate=bookOrderCheckFrame.checkDatefield.getText().trim();	
			String checkoperator=onloadFrame.usernamefield.getText().trim();
			int checkandaccept=1;
			if(AccountCheck.IsEmptyOrNull(bookISBN)){
				JOptionPane.showMessageDialog(null, "请输入图书编号");
			}else if(AccountCheck.IsEmptyOrNull(checkDate)){
				JOptionPane.showMessageDialog(null, "请输入订购日期");
			}else{
				if(book_Order_Dao.updateBook_Order(checkandaccept, checkoperator, checkDate, bookISBN)){
					DefaultTableModel tablemodel = new DefaultTableModel(bookOrderCheckFrame.getBookOrderInfoAll(new Book_Order_Dao().selectBookBorrow()),Constant.bookOrdercolumnNames) {
						@Override
						public boolean isCellEditable(int arg0, int arg1) {
							// TODO Auto-generated method stub
							return false;
						}
					};//修改之后重新调用一次数据库渲染一下table中的数据
					bookOrderCheckFrame.table.setModel(tablemodel);
					bookOrderCheckFrame.table.getColumnModel().getColumn(0).setPreferredWidth(180);
					bookOrderCheckFrame.table.getColumnModel().getColumn(1).setPreferredWidth(180);
					bookOrderCheckFrame.table.getColumnModel().getColumn(3).setPreferredWidth(120);
					bookOrderCheckFrame.table.getColumnModel().getColumn(5).setPreferredWidth(120);
					JOptionPane.showMessageDialog(null, "订购成功");
				}else{
					JOptionPane.showMessageDialog(null, "订购失败 ╮(╯▽╰)╭");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
