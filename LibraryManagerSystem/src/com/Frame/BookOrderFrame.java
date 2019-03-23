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
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ActionListener.bookOrder.bookOrderActionEvent;
import com.ActionListener.bookOrder.bookOrderTableListener;
import com.Const.Constant;
import com.Dao.Book_Order_Dao;
import com.bean.Book_Order;
/**
 * 新书订购界面
 * @author LMingX0103380
 *
 */
public class BookOrderFrame extends JInternalFrame {
	private static BookOrderFrame bookOrderFrame;
	 public JTextField bookISBNfield;
	 public JFormattedTextField orderDatefield;
	 public JTextField orderNumfield;
	 public JTextField zkfield;
	 public JTable table;
    public BookOrderFrame(){
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
			Object[][] result=getBookOrderInfoAll(new Book_Order_Dao().selectBookBorrow());
			DefaultTableModel tablemodel = new DefaultTableModel(result,Constant.bookOrdercolumnNames) {
				@Override
				public boolean isCellEditable(int arg0, int arg1) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			table = new JTable(tablemodel);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setRowHeight(30);
			table.addMouseListener(new bookOrderTableListener());
			table.getColumnModel().getColumn(0).setPreferredWidth(180);
			table.getColumnModel().getColumn(1).setPreferredWidth(180);
			table.getColumnModel().getColumn(3).setPreferredWidth(120);
			table.getColumnModel().getColumn(5).setPreferredWidth(120);
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
   	 JLabel orderDatelabel=new JLabel("采购日期");
   	 JLabel orderNumlabel=new JLabel("采购数量");
   	 JLabel zklabel=new JLabel("书籍折扣");
   	 
   	 bookISBNfield=new JTextField();  
   	 orderDatefield=new JFormattedTextField(new SimpleDateFormat("YYYY-MM-dd").getInstance());    	 
	 orderDatefield.setValue(new Date());
	 orderNumfield=new JTextField();
	 zkfield=new JTextField();
    	
   	 
   	 modiPanel.add(bookISBNlabel);
   	 modiPanel.add(bookISBNfield);
   	 modiPanel.add(orderDatelabel);
   	 modiPanel.add(orderDatefield);
   	 modiPanel.add(orderNumlabel);
   	 modiPanel.add(orderNumfield);
   	 modiPanel.add(zklabel);
   	 modiPanel.add(zkfield);
   	 
   	 mainPanel.add(modiPanel, BorderLayout.SOUTH);
   	 FlowLayout flow=new FlowLayout();
   	 flow.setHgap(30);
   	 flow.setVgap(30);
   	 flow.setAlignment(FlowLayout.CENTER);
   	 JPanel footPanel=new JPanel(flow);//初始化一个底部面板；
   	 footPanel.setPreferredSize(new Dimension(getWidth(),80));
   	 JButton addButton=new JButton("采购");
   	 addButton.addActionListener(new bookOrderActionEvent());
   	 JButton closeButton=new JButton("关闭");
   	 
   	 closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				getBookOrderFrame().doDefaultCloseAction();
				setBookOrderFrameIsNull();
			}
		  });
   	 footPanel.add(addButton);
   	 footPanel.add(closeButton);   	 
   	 this.add(backGroundPanel, borderlayout.NORTH);
   	 this.add(mainPanel, borderlayout.CENTER);
   	 this.add(footPanel, borderlayout.SOUTH);    	 
   	 this.setTitle("图书采购"); 
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
	     public static BookOrderFrame getBookOrderFrame(){
	    	 if(bookOrderFrame==null){
	    		 bookOrderFrame=new BookOrderFrame();   		 
	    	 }
	    		 return bookOrderFrame;   	    	 
	     }
	     public static void setBookOrderFrameIsNull(){
	    	 if(bookOrderFrame!=null){
	    		 bookOrderFrame=null;
	    	 }
	    	 
	     }
	     /**
	      * 获取当前读者的借阅信息
	      */
	     public static Object[][] getBookOrderInfoAll(List<Book_Order> list ){
	    	 Object[][] result = new Object[list.size()][Constant.bookOrdercolumnNames.length];
	 		for (int i = 0; i < list.size(); i++) {
	 			Book_Order book_Order = list.get(i);
	 			result[i][0] = book_Order.getISBN();// 获取图书编号
	 			result[i][1] = book_Order.getOrder_date();// 获取订购日期
	 			result[i][2] = book_Order.getNumber();// 获取订购数量
	 			result[i][4] = book_Order.getOperator();
	 			if(book_Order.getCheckandaccept()==1){
	 				result[i][3] =Constant.isCheckOrderBookContent[0] ;// 获取是否验收
	 			}else{
	 				result[i][3] =Constant.isCheckOrderBookContent[1];// 获取是否验收
	 			}	 			
	 			// 获取借出日期
	 			result[i][5] = book_Order.getZk();// 获取书籍折扣 	
	 			result[i][6] = book_Order.getOperator();// 获取验收员 	
	 			result[i][7] = book_Order.getCheck_date();// 获取验收日期
	 		}
	 		return result;	    	 
	     }
}
