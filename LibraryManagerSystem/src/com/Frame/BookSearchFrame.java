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
import com.ActionListener.bookSearch.bookSearchActionEvent;
import com.ActionListener.bookSearch.bookSearchTableListener;
import com.Const.Constant;
import com.Dao.Book_SearchDao;
import com.Dao.Dao;
import com.bean.Book_Borrow;
public class BookSearchFrame extends JInternalFrame {
	 private static BookSearchFrame bookSearchFrame;
	 public  JTextField bookISBNfield;
	 public  JTextField bookNamefield;
	 public  JComboBox  bookTypebox;
	 public  JTextField readNamefield;
	 public  JTextField readISBNfield;
	 public  JComboBox returnbox;
	 public  JFormattedTextField borrowDatefield;
	 public  JFormattedTextField returnDatefield;
	 public  JTextField operatorNamefield;
	 public JTable table;
	 public BookSearchFrame(){
		 BorderLayout borderlayout=new BorderLayout();
    	 this.setLayout(borderlayout);	   
    	 JPanel tablePanel=new JPanel(new BorderLayout(10, 10));  
    	 tablePanel.setPreferredSize(new Dimension(getWidth(), 400));
 		 JScrollPane scrollPanel = new JScrollPane();
 		try {
 			Object[][] result = getSearchBookInfoAll(new Book_SearchDao().selectSearchBookInfoAll());
 			DefaultTableModel tablemodel = new DefaultTableModel(result,Constant.searchDetailcolumnNames) {
 				@Override
 				public boolean isCellEditable(int arg0, int arg1) {
 					// TODO Auto-generated method stub
 					return false;
 				}
 			};
 			table = new JTable(tablemodel);
 			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			table.setRowHeight(30);
 			table.addMouseListener(new bookSearchTableListener());
 			table.getColumnModel().getColumn(0).setPreferredWidth(180);
 			table.getColumnModel().getColumn(1).setPreferredWidth(180);
 			table.getColumnModel().getColumn(4).setPreferredWidth(180);
 			table.getColumnModel().getColumn(6).setPreferredWidth(180);
 			table.getColumnModel().getColumn(7).setPreferredWidth(120);
 			scrollPanel.setViewportView(table);
 			tablePanel.add(scrollPanel);
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		JPanel filterPanel = new JPanel(new GridLayout(3, 6, 10, 10));
 		filterPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // 设置面板距离上下左右的边距																			// 顺序是上右下左 																			// 顺时针方向
 		filterPanel.setPreferredSize(new Dimension(getWidth(), 100));// 设置背景面板大小
    	 
    	 JLabel bookISBNlabel=new JLabel("图书编号");
    	 JLabel bookNamelabel=new JLabel("图书名称");
    	 JLabel bookTypelabel=new JLabel("图书类别");
    	 JLabel readNamelabel=new JLabel("读者名称");
    	 JLabel readISBNlabel=new JLabel("读者编号");
    	 JLabel returnlabel=new JLabel("借出或者归还");
    	 JLabel borrowDatelabel=new JLabel("借出时间");
    	 JLabel returnDatelabel=new JLabel("归还时间");
    	 JLabel operatorNamelabel=new JLabel("操作员姓名");
    	 
    	 bookISBNfield=new JTextField();    	 		
    	 bookNamefield=new JTextField();
    	 try {
			bookTypebox=new JComboBox(Dao.getBookTypeName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 readNamefield=new JTextField();
    	 readISBNfield=new JTextField();
    	 returnbox=new JComboBox(Constant.returnboxContent);
    	 returnbox.setSelectedIndex(0);
    	 borrowDatefield=new JFormattedTextField(new SimpleDateFormat("YYYY-MM-dd").getInstance());    	 
    	 borrowDatefield.setValue(new Date());
    	 returnDatefield=new JFormattedTextField(new SimpleDateFormat("YYYY-MM-dd").getInstance());
    	 returnDatefield.setValue(new Date());
    	 operatorNamefield=new JTextField();
    	 
    	 filterPanel.add(bookISBNlabel);
    	 filterPanel.add(bookISBNfield);
    	 filterPanel.add(bookNamelabel);
    	 filterPanel.add(bookNamefield);
    	 filterPanel.add(bookTypelabel);
    	 filterPanel.add(bookTypebox);
    	 filterPanel.add(readNamelabel);
    	 filterPanel.add(readNamefield);
    	 filterPanel.add(readISBNlabel);
    	 filterPanel.add(readISBNfield);
    	 filterPanel.add(returnlabel);
    	 filterPanel.add(returnbox);
    	 filterPanel.add(borrowDatelabel);
    	 filterPanel.add(borrowDatefield);
    	 filterPanel.add(returnDatelabel);
    	 filterPanel.add(returnDatefield);
    	 filterPanel.add(operatorNamelabel);
    	 filterPanel.add(operatorNamefield);
    	 
    	 FlowLayout flow=new FlowLayout();
    	 flow.setHgap(30);
    	 flow.setVgap(30);
    	 flow.setAlignment(FlowLayout.CENTER);
    	 JPanel footPanel=new JPanel(flow);//初始化一个底部面板；
    	 footPanel.setPreferredSize(new Dimension(getWidth(),80));
    	 JButton searchButton=new JButton("搜索");
    	 searchButton.addActionListener(new bookSearchActionEvent());
    	 JButton closeButton=new JButton("关闭");
    	 
    	 closeButton.addActionListener(new ActionListener() {
 			
 			@Override
 			public void actionPerformed(ActionEvent arg0) {
 				// TODO Auto-generated method stub
 				getBookSearchFrame().doDefaultCloseAction();
 				setBookSearchFrameIsNull();
 			}
 		  });
    	 footPanel.add(searchButton);
    	 footPanel.add(closeButton);   	 
    	 this.add(tablePanel, borderlayout.NORTH);
    	 this.add(filterPanel, borderlayout.CENTER);
    	 this.add(footPanel, borderlayout.SOUTH);    	 
    	 this.setTitle("图书搜索"); 
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
		     public static BookSearchFrame getBookSearchFrame(){
		    	 if(bookSearchFrame==null){
		    		 bookSearchFrame=new BookSearchFrame();   		 
		    	 }
		    		 return bookSearchFrame;   	    	 
		     }
		     public static void setBookSearchFrameIsNull(){
		    	 if(bookSearchFrame!=null){
		    		 bookSearchFrame=null;
		    	 }
		    	 
		     }	   
		     /**
		      * 获取当前所有的读者借阅和归还信息
		      */
		     public static Object[][] getSearchBookInfoAll(List<Book_Borrow> list ){
		    	 Object[][] result = new Object[list.size()][Constant.searchDetailcolumnNames.length];
		 		for (int i = 0; i < list.size(); i++) {
		 			Book_Borrow book_Borrow = list.get(i);
		 			result[i][0] = book_Borrow.getBookISBN();// 获取图书编号
		 			result[i][1] = book_Borrow.getBookName();// 获取图书名称
		 			result[i][2] = book_Borrow.getBookTypeName();// 获取读者姓名
		 			result[i][3] = book_Borrow.getReaderName();// 获取读者姓名
		 			result[i][4] = book_Borrow.getReadISBN();// 获取读者编号
		 			if(book_Borrow.getIsback()==1){
		 				result[i][5] ="归还" ;// 获取是否归还
		 			}else{
		 				result[i][5] ="借出";// 获取是否归还
		 			}	 				 			
		 			result[i][6] = book_Borrow.getBorrowDate();// 获取借出日期
		 			result[i][7] = book_Borrow.getBackDate();// 获取归还日期
		 			result[i][8] = book_Borrow.getOperatorname();// 获取操作员姓名	 	
		 		}
		 		return result;
		    	 
		     }
}
