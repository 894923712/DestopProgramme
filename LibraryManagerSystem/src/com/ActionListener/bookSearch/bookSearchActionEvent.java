package com.ActionListener.bookSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.Const.Constant;
import com.Dao.Book_SearchDao;
import com.Frame.BookSearchFrame;
import com.bean.Book_Borrow;

public class bookSearchActionEvent implements ActionListener {  
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		BookSearchFrame bookSearchFrame=BookSearchFrame.getBookSearchFrame();		
		Book_SearchDao book_SearchDao=new Book_SearchDao();
		try {
			String bookISBN=bookSearchFrame.bookISBNfield.getText().trim();
			String bookName=bookSearchFrame.bookNamefield.getText().trim();
			String bookType=bookSearchFrame.bookTypebox.getSelectedItem().toString().trim();
			String readname=bookSearchFrame.readNamefield.getText().trim();
			String readISBN=bookSearchFrame.readISBNfield.getText().trim();
			String isback=bookSearchFrame.returnbox.getSelectedItem().toString().trim();
			String borrowDate=bookSearchFrame.borrowDatefield.getText().trim();
			String returnDate=bookSearchFrame.returnDatefield.getText().trim();
			String operatorName=bookSearchFrame.operatorNamefield.getText().trim();
			List<Book_Borrow> filterresult=book_SearchDao.selectSearchBookInfoAllByFilter(bookISBN,bookName,bookType,readname,readISBN,isback,borrowDate,returnDate,operatorName);
			DefaultTableModel tablemodel = new DefaultTableModel(bookSearchFrame.getSearchBookInfoAll(filterresult),Constant.searchDetailcolumnNames) {
 				@Override
 				public boolean isCellEditable(int arg0, int arg1) {
 					// TODO Auto-generated method stub
 					return false;
 				}
 			};//修改之后重新调用一次数据库渲染一下table中的数据
 			bookSearchFrame.table.setModel(tablemodel);
 			bookSearchFrame.table.getColumnModel().getColumn(0).setPreferredWidth(180);
 			bookSearchFrame.table.getColumnModel().getColumn(1).setPreferredWidth(180);
 			bookSearchFrame.table.getColumnModel().getColumn(4).setPreferredWidth(180);
 			bookSearchFrame.table.getColumnModel().getColumn(5).setPreferredWidth(180);
 			bookSearchFrame.table.getColumnModel().getColumn(6).setPreferredWidth(180);
 			bookSearchFrame.table.getColumnModel().getColumn(7).setPreferredWidth(120);
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
