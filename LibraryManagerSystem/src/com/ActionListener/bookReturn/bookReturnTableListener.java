package com.ActionListener.bookReturn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.Const.Constant;
import com.Dao.Book_Borrow_Dao;
import com.Dao.Book_Info_Dao;
import com.Frame.BookBorrowFrame;
import com.Frame.BookModiFrame;
import com.Frame.BookReturnFrame;

public class bookReturnTableListener extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		  /**
         * 判断当前是否是鼠标右击
         */       
       if (SwingUtilities.isRightMouseButton(e)) {
			JPopupMenu popMenu =null;
			JTable table = (JTable) e.getComponent();
			//获取鼠标右键选中的行
			int row = table.rowAtPoint(e.getPoint());
			if (row == -1) {
				return ;
			}
			//获取已选中的行
			int[] rows = table.getSelectedRows();
			boolean inSelected = false ;
			//判断当前右键所在行是否已选中
			for(int r : rows){
				if(row == r){
					inSelected = true ;
					break ;
				}
			}
			//当前鼠标右键点击所在行不被选中则高亮显示选中行
			if(!inSelected){
				table.setRowSelectionInterval(row, row);
			}
			//生成右键菜单
			popMenu =  makePopup();			
			popMenu.show(e.getComponent(), e.getX(), e.getY());
       }
	}
	
	 /**
   	 * 生成一个右键菜单栏
   	 * @author LMingX0103380
   	 * @return
   	 */
   	public static JPopupMenu makePopup(){
   		JPopupMenu popmenu=new JPopupMenu();
   		JMenuItem delete=new JMenuItem("删除");
       	delete.addActionListener(new ActionListener() {
   			
   			@Override
   			public void actionPerformed(ActionEvent e) {
   				// TODO Auto-generated method stub
   				BookReturnFrame bookReturnFrame=BookReturnFrame.getBookReturnFrame();
   				JTable table=bookReturnFrame.table;
   				int[] rows = table.getSelectedRows();
   				String[] bookISBN =new String[rows.length];
   				String[] readISBN =new String[rows.length];
   				Map<String,String> map=new HashMap<>();
   				for (int i = 0; i < rows.length; i++) {
   					bookISBN[i]=table.getValueAt(rows[i], 0).toString().trim();
   					readISBN[i]=bookReturnFrame.readISBNfield.getText().trim();
   					map.put(bookISBN[i], readISBN[i]);
   				}
   				Book_Borrow_Dao book_Borrow_Dao=new Book_Borrow_Dao();
   				try {						
   					if(book_Borrow_Dao.deleteBook_Borrow(map)){
   						DefaultTableModel tablemodel = new DefaultTableModel(bookReturnFrame.getBookReturnInfoAll(new Book_Borrow_Dao().selectBookReturn(readISBN[0])),Constant.returnDetailcolumnNames) {
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
   						JOptionPane.showMessageDialog(null, "删除成功");
   						
   					}else{
   						 JOptionPane.showMessageDialog(null, "删除失败！╮(╯▽╰)╭");
   					}
   				} catch (Exception e1) {
   					// TODO Auto-generated catch block
   					e1.printStackTrace();
   				}
   			}
   		});
       	popmenu.add(delete);
   		  
   		return popmenu;
   	}
}

