package com.ActionListener.operatorModi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.Const.Constant;
import com.Dao.Operator_Dao;
import com.Frame.OperatorModiFrame;

public class operatorTableListener implements MouseListener {
    
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		OperatorModiFrame operator=OperatorModiFrame.getOperatorModiFrame();
        int rownum= operator.table.getSelectedRow();
        operator.id=operator.table.getValueAt(rownum, 0).toString().trim();
        operator.usernamefield.setText(operator.table.getValueAt(rownum, 1).toString().trim());
        operator.sexbox.setSelectedItem(operator.table.getValueAt(rownum, 2));
        operator.agebox.setSelectedItem(operator.table.getValueAt(rownum, 3).toString().trim());
        operator.identitycardfield.setText(operator.table.getValueAt(rownum, 4).toString().trim());
        operator.telfield.setText(operator.table.getValueAt(rownum, 6).toString().trim());
        operator.adminbox.setSelectedItem(operator.table.getValueAt(rownum, 7));
        operator.passwordfield.setText(operator.table.getValueAt(rownum, 8).toString().trim());
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

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

		// TODO Auto-generated method stub

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
				JTable table=OperatorModiFrame.getOperatorModiFrame().table;
				int[] rows = table.getSelectedRows();
				int[] ids=new int[rows.length];
				for (int i = 0; i < rows.length; i++) {
					ids[i]=Integer.parseInt(table.getValueAt(rows[i], 0).toString().trim());
				}
				Operator_Dao opdao=new Operator_Dao();
				try {						
					if(opdao.deleteOperationByIds(ids)){
						DefaultTableModel tablemodel=new DefaultTableModel(OperatorModiFrame.getOperatorAll(new Operator_Dao().selectOperation()), Constant.operatorcolumnNames){
							 
							 @Override
							public boolean isCellEditable(int arg0, int arg1) {
								// TODO Auto-generated method stub
								return false;
							}
						 };//修改之后重新调用一次数据库渲染一下table中的数据
						 OperatorModiFrame operator=OperatorModiFrame.getOperatorModiFrame();
						 operator.table.setModel(tablemodel);
						 operator.table.getColumnModel().getColumn(4).setPreferredWidth(180);
	     				 operator.table.getColumnModel().getColumn(5).setPreferredWidth(180);
	     				 operator.table.getColumnModel().getColumn(6).setPreferredWidth(120);
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
