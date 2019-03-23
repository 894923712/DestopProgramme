package com.ActionListener.bookOrder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.Const.Constant;
import com.Dao.Book_Borrow_Dao;
import com.Dao.Book_Order_Dao;
import com.Frame.BookOrderFrame;

public class bookOrderTableListener extends MouseAdapter {
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
   				BookOrderFrame bookOrderFrame=BookOrderFrame.getBookOrderFrame();
   				JTable table=bookOrderFrame.table;
   				int[] rows = table.getSelectedRows();
   				String[] bookISBN =new String[rows.length];  				
   				for (int i = 0; i < rows.length; i++) {
   					bookISBN[i]=table.getValueAt(rows[i], 0).toString().trim(); 					
   				}
   				Book_Order_Dao book_Order_Dao=new Book_Order_Dao();
   				try {						
   					if(book_Order_Dao.deleteBook_Order(bookISBN)){
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

