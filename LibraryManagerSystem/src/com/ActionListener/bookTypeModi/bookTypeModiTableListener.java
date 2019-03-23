package com.ActionListener.bookTypeModi;

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
import com.Dao.Book_Info_Dao;
import com.Dao.Book_Type_Dao;
import com.Frame.BookModiFrame;
import com.Frame.BookTypeModiFrame;

public class bookTypeModiTableListener extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		BookTypeModiFrame bookTypeModiFrame=BookTypeModiFrame.getBookTypeModiFrame();
        int rownum= bookTypeModiFrame.table.getSelectedRow();
        bookTypeModiFrame.typeid=bookTypeModiFrame.table.getValueAt(rownum, 0).toString().trim();
        bookTypeModiFrame.typenamefield.setText(bookTypeModiFrame.table.getValueAt(rownum, 1).toString().trim());       
        bookTypeModiFrame.daysfield.setText(bookTypeModiFrame.table.getValueAt(rownum, 2).toString().trim());
        bookTypeModiFrame.fkfield.setText(bookTypeModiFrame.table.getValueAt(rownum, 3).toString().trim());
        
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
   				JTable table=BookTypeModiFrame.getBookTypeModiFrame().table;
   				int[] rows = table.getSelectedRows();
   				int[] typeids=new int[rows.length];
   				for (int i = 0; i < rows.length; i++) {
   					typeids[i]=Integer.parseInt(table.getValueAt(rows[i], 0).toString().trim());
   				}
   				Book_Type_Dao bookTypeDao=new Book_Type_Dao();
   				try {						
   					if(bookTypeDao.deleteBookTypeByTypeids(typeids)){
   						DefaultTableModel tablemodel=new DefaultTableModel(BookTypeModiFrame.getBookTypeAll(new Book_Type_Dao().selectBooktype()), Constant.booktypelumnNames){
   							
   							public boolean isCellEditable(int arg0, int arg1) {
   								return false;
   							};
   						};//修改之后重新调用一次数据库渲染一下table中的数据
   						BookTypeModiFrame.getBookTypeModiFrame().table.setModel(tablemodel);
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
