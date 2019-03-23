package com.Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ActionListener.bookReturn.bookReturnActionEvent;
import com.ActionListener.bookReturn.bookReturnTableListener;
import com.Const.Constant;
import com.Dao.Book_Borrow_Dao;
import com.Dao.Dao;
import com.Dao.Reader_Info_Dao;
import com.bean.Book_Borrow;
import com.bean.Reader_info;

public class BookReturnFrame extends JInternalFrame {
	 private static BookReturnFrame bookReturnFrame;
	 public  JTextField bookISBNfield;
	 public JTextField readISBNfield;
	 public JComboBox returnbox;
	 public JFormattedTextField borrowDatefield;
	 public JTable table;
     public BookReturnFrame(){
    	 BorderLayout borderlayout=new BorderLayout();
    	 this.setLayout(borderlayout);
    	 JPanel backGroundPanel=new JPanel();//初始化一个背景面板；   	 
    	 JLabel backgroudlabel=new JLabel(new ImageIcon("resource/image/readerAdd.jpg"));//设置背景图片
    	 backgroudlabel.setOpaque(true);
    	 backGroundPanel.add(backgroudlabel); 	 
    	 backGroundPanel.setPreferredSize(new Dimension(getWidth(), 150));//设置背景面板大小  	   
    	 JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
 		 JScrollPane scrollPanel = new JScrollPane();

 		try {
 			Object[][] result = new Object[1][Constant.returnDetailcolumnNames.length];
 			DefaultTableModel tablemodel = new DefaultTableModel(result,Constant.returnDetailcolumnNames) {
 				@Override
 				public boolean isCellEditable(int arg0, int arg1) {
 					// TODO Auto-generated method stub
 					return false;
 				}
 			};
 			table = new JTable(tablemodel);
 			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			table.setRowHeight(30);
 			table.addMouseListener(new bookReturnTableListener());
 			table.getColumnModel().getColumn(0).setPreferredWidth(180);
 			table.getColumnModel().getColumn(4).setPreferredWidth(180);
 			table.getColumnModel().getColumn(7).setPreferredWidth(180);
 			table.getColumnModel().getColumn(8).setPreferredWidth(120);
 			scrollPanel.setViewportView(table);
 			mainPanel.add(scrollPanel);
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		JPanel modiPanel = new JPanel(new GridLayout(2, 4, 10, 10));
 		modiPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // 设置面板距离上下左右的边距																			// 顺序是上右下左 																			// 顺时针方向
 		modiPanel.setPreferredSize(new Dimension(getWidth(), 100));// 设置背景面板大小
    	 
    	 JLabel bookISBNlabel=new JLabel("图书编号");
    	 JLabel readISBNlabel=new JLabel("读者编号");
    	 JLabel returnlabel=new JLabel("借出或者归还");
    	 JLabel borrowDatelabel=new JLabel("归还时间");	 
    	 bookISBNfield=new JTextField();    	 		
    	 readISBNfield=new JTextField();
    	 returnbox=new JComboBox(Constant.returnboxContent);
    	 returnbox.setSelectedIndex(2);
    	 borrowDatefield=new JFormattedTextField(new SimpleDateFormat("YYYY-MM-dd").getInstance());    	 
    	 borrowDatefield.setValue(new Date());
    	 
    	 modiPanel.add(bookISBNlabel);
    	 modiPanel.add(bookISBNfield);
    	 modiPanel.add(readISBNlabel);
    	 modiPanel.add(readISBNfield);
    	 modiPanel.add(returnlabel);
    	 modiPanel.add(returnbox);
    	 modiPanel.add(borrowDatelabel);
    	 modiPanel.add(borrowDatefield);
    	 
    	 mainPanel.add(modiPanel, BorderLayout.SOUTH);
    	 FlowLayout flow=new FlowLayout();
    	 flow.setHgap(30);
    	 flow.setVgap(30);
    	 flow.setAlignment(FlowLayout.CENTER);
    	 JPanel footPanel=new JPanel(flow);//初始化一个底部面板；
    	 footPanel.setPreferredSize(new Dimension(getWidth(),80));
    	 JButton addButton=new JButton("確定");
    	 addButton.addActionListener(new bookReturnActionEvent());
    	 JButton closeButton=new JButton("关闭");
    	 
    	 closeButton.addActionListener(new ActionListener() {
 			
 			@Override
 			public void actionPerformed(ActionEvent arg0) {
 				// TODO Auto-generated method stub
 				getBookReturnFrame().doDefaultCloseAction();
 				setBookReturnFrameIsNull();
 			}
 		  });
    	 footPanel.add(addButton);
    	 footPanel.add(closeButton);   	 
    	 this.add(backGroundPanel, borderlayout.NORTH);
    	 this.add(mainPanel, borderlayout.CENTER);
    	 this.add(footPanel, borderlayout.SOUTH);    	 
    	 this.setTitle("图书归还"); 
    	 this.setFrameIcon(new ImageIcon("resource/image/icons.jpg"));//设置头标
    	 this.setSize(750, 650);   	 
    	 this.setLocation(new Point((MainFrame.DesktopPane.getWidth()-getWidth())/2,(MainFrame.DesktopPane.getHeight()-getHeight())/2));//获取屏幕大小和窗口大小并取其中间间隔，使其窗体位于屏幕中间
    	 this.setClosable(true);
    	 this.setIconifiable(true);//设置窗体可最小化
    	 this.setResizable(false);//设置窗体不可改变大小
    	 this.setVisible(true);
    	 
    	 
     }
     
     /**
		 * 单例模式 获取对象
		 * @author LMingX0103380
		 * @return
	     * @throws Exception 
		 */
	     public static BookReturnFrame getBookReturnFrame(){
	    	 if(bookReturnFrame==null){
	    		 bookReturnFrame=new BookReturnFrame();   		 
	    	 }
	    		 return bookReturnFrame;   	    	 
	     }
	     public static void setBookReturnFrameIsNull(){
	    	 if(bookReturnFrame!=null){
	    		 bookReturnFrame=null;
	    	 }
	    	 
	     }
	    // "图书编号","图书名称","读者名称","是否归还","归还日期","本书罚款","总罚款","借出日期","操作员名称"
	     /**
	      * 获取当前读者的借阅信息
	      */
	     public static Object[][] getBookReturnInfoAll(List<Book_Borrow> list ){
	    	 Object[][] result = new Object[list.size()][Constant.returnDetailcolumnNames.length];
	 		for (int i = 0; i < list.size(); i++) {
	 			Book_Borrow book_Borrow = list.get(i);
	 			result[i][0] = book_Borrow.getBookISBN();// 获取图书编号
	 			result[i][1] = book_Borrow.getBookName();// 获取图书名称
	 			result[i][2] = book_Borrow.getReaderName();// 获取读者姓名
	 			if(book_Borrow.getIsback()==1){
	 				result[i][3] ="归还" ;// 获取是否归还
	 			}else{
	 				result[i][3] ="借出";// 获取是否归还
	 			}	 				 			
	 			result[i][4] = book_Borrow.getBackDate();// 获取归还日期
	 			result[i][5] = book_Borrow.getFk();// 获取本书罚款数
	 			result[i][6] = book_Borrow.getAmountfk();// 获取总罚款数
	 			result[i][7] = book_Borrow.getBorrowDate();// 获取借出日期
	 			result[i][8] = book_Borrow.getOperatorname();// 获取操作员姓名	 			
	 		}
	 		return result;
	    	 
	     }
}
