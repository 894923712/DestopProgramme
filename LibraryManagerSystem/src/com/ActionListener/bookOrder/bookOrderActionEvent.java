package com.ActionListener.bookOrder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.Const.Constant;
import com.Dao.Book_Order_Dao;
import com.Frame.BookOrderFrame;
import com.Frame.OnloadFrame;
import com.Vaildate.AccountCheck;

public class bookOrderActionEvent implements ActionListener {
	//bookISBN ,operatorId ,readISBN ,isback ,borrowDate  
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookOrderFrame bookOrderFrame=BookOrderFrame.getBookOrderFrame();
		OnloadFrame onloadFrame=OnloadFrame.getOnloadFrame();
		Book_Order_Dao book_Order_Dao=new Book_Order_Dao();			
		try {
			String bookISBN=bookOrderFrame.bookISBNfield.getText().trim();		
			String orderDate=bookOrderFrame.orderDatefield.getText().trim();
			String orderNum=bookOrderFrame.orderNumfield.getText().trim();
			String operator=onloadFrame.usernamefield.getText().trim();
			int checkandaccept=0;
			String zk=bookOrderFrame.zkfield.getText().trim();
			if(AccountCheck.IsEmptyOrNull(bookISBN)){
				JOptionPane.showMessageDialog(null, "请输入图书编号");
			}else if(AccountCheck.IsEmptyOrNull(orderDate)){
				JOptionPane.showMessageDialog(null, "请输入订购日期");
			}else if(AccountCheck.IsEmptyOrNull(orderNum)){
				JOptionPane.showMessageDialog(null, "请输入订购数量");
			}else if(AccountCheck.IsEmptyOrNull(zk)){
				JOptionPane.showMessageDialog(null, "请输入书籍折扣");
			}else{
				if(book_Order_Dao.insertBook_Order(bookISBN, orderDate, orderNum, operator, checkandaccept,zk)){
					DefaultTableModel tablemodel = new DefaultTableModel(bookOrderFrame.getBookOrderInfoAll(new Book_Order_Dao().selectBookBorrow()),Constant.bookOrdercolumnNames) {
						@Override
						public boolean isCellEditable(int arg0, int arg1) {
							// TODO Auto-generated method stub
							return false;
						}
					};//修改之后重新调用一次数据库渲染一下table中的数据
					bookOrderFrame.table.setModel(tablemodel);
					bookOrderFrame.table.getColumnModel().getColumn(0).setPreferredWidth(180);
					bookOrderFrame.table.getColumnModel().getColumn(1).setPreferredWidth(180);
					bookOrderFrame.table.getColumnModel().getColumn(3).setPreferredWidth(120);
					bookOrderFrame.table.getColumnModel().getColumn(5).setPreferredWidth(120);
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
