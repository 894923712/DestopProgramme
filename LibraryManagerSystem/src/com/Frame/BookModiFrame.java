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

import com.ActionListener.bookModi.bookModiActionEvent;
import com.ActionListener.bookModi.bookModiTableListener;
import com.ActionListener.operatorModi.operatorTableListener;
import com.Const.Constant;
import com.Dao.Book_Info_Dao;
import com.Dao.Dao;
import com.Dao.Operator_Dao;
import com.bean.Book_Info;
import com.bean.Operator;

public class BookModiFrame extends JInternalFrame {
	 private static BookModiFrame bookModiFrame;
	 public JTextField booknumfield;
	 public JComboBox categorybox;
	 public JTextField booknamefield;
	 public JTextField authorfield;
	 public JComboBox  publishbox;
	 public JTextField translatorfield;
	 public JFormattedTextField pubdatefield;
	 public JTextField perpricefield;
	 public JTable table;
	 private BookModiFrame(){
		 BorderLayout borderlayout=new BorderLayout();
    	 this.setLayout(borderlayout);
    	 JPanel backGroundPanel=new JPanel();//初始化一个背景面板；   	 
    	 JLabel backgroudlabel=new JLabel(new ImageIcon("resource/image/operatorModibackground.jpg"));//设置背景图片
    	 backgroudlabel.setOpaque(true);
    	 backGroundPanel.add(backgroudlabel);   	 
    	 backGroundPanel.setPreferredSize(new Dimension(getWidth(), 100));//设置背景面板大小
    	 
    	 JPanel mainPanel=new JPanel(new BorderLayout(10, 10));
    	 JScrollPane scrollPanel=new JScrollPane();
    	 
    	 try {
			 Object[][] result = getBookInfoAll(new Book_Info_Dao().selectBookInfo());
			 DefaultTableModel tablemodel=new DefaultTableModel(result, Constant.bookinfocolumnNames){
				 @Override
				public boolean isCellEditable(int arg0, int arg1) {
					// TODO Auto-generated method stub
					return false;
				}				 
			 };
			 table=new JTable(tablemodel);
	    	 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    	 table.setRowHeight(30);
	    	 table.addMouseListener(new bookModiTableListener());
	    	 table.getColumnModel().getColumn(0).setPreferredWidth(180);
	    	 table.getColumnModel().getColumn(2).setPreferredWidth(140);
	    	 table.getColumnModel().getColumn(3).setPreferredWidth(100);
	    	 table.getColumnModel().getColumn(4).setPreferredWidth(140);
	    	 table.getColumnModel().getColumn(5).setPreferredWidth(180);
	    	 table.getColumnModel().getColumn(6).setPreferredWidth(180);
	    	 scrollPanel.setViewportView(table);
	    	 mainPanel.add(scrollPanel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
    	 JPanel modiPanel=new JPanel(new GridLayout(3, 6,10,10)); 	
    	 modiPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)) ; //设置面板距离上下左右的边距 顺序是上右下左 顺时针方向	 
    	 modiPanel.setPreferredSize(new Dimension(getWidth(), 120));//设置背景面板大小
    	 
    	 JLabel booknumlabel=new JLabel("图书编号");
    	 JLabel categorylabel=new JLabel("类别");
    	 JLabel booknamelabel=new JLabel("书名");
    	 JLabel authorlabel=new JLabel("作者");
    	 JLabel publishlabel=new JLabel("出版社");
    	 JLabel translatorlabel=new JLabel("译者");
    	 JLabel pubdatelabel=new JLabel("出版日期");
    	 JLabel perpricelabel=new JLabel("单价");
    	 
    	 booknumfield=new JTextField();
    	 booknumfield.setEditable(false);
 		try {
 			categorybox = new JComboBox(Dao.getBookTypeName());
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}//数据库中获取类别
     	  booknamefield=new JTextField();
     	  authorfield=new JTextField();
     	  publishbox=new JComboBox(Constant.publisher);
     	  publishbox.setSelectedIndex(0);
     	  translatorfield=new JTextField();    	 
     	  pubdatefield=new JFormattedTextField(new SimpleDateFormat("YYYY-MM-dd").getInstance());
     	  pubdatefield.setValue(new Date());
     	  perpricefield=new JTextField();
     	  
     	 modiPanel.add(booknumlabel);
     	 modiPanel.add(booknumfield);
     	 modiPanel.add(categorylabel);
     	 modiPanel.add(categorybox);
     	 modiPanel.add(booknamelabel);
    	 modiPanel.add(booknamefield);
    	 modiPanel.add(authorlabel);
    	 modiPanel.add(authorfield);
    	 modiPanel.add(publishlabel);
    	 modiPanel.add(publishbox);
    	 modiPanel.add(translatorlabel);
    	 modiPanel.add(translatorfield);
    	 modiPanel.add(pubdatelabel);
    	 modiPanel.add(pubdatefield);
    	 modiPanel.add(perpricelabel);
    	 modiPanel.add(perpricefield);
     	  
    	 mainPanel.add(modiPanel,BorderLayout.SOUTH);  	 
    	 FlowLayout flow=new FlowLayout();
    	 flow.setHgap(30);
    	 flow.setVgap(30);
    	 flow.setAlignment(FlowLayout.RIGHT);
    	 JPanel footPanel=new JPanel(flow);//初始化一个底部面板；
    	 footPanel.setPreferredSize(new Dimension(getWidth(),80));
    	 JButton addButton=new JButton("修改");
    	 addButton.addActionListener(new bookModiActionEvent());
    	 JButton closeButton=new JButton("关闭");
    	 closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				BookModiFrame.getBookModiFrame().doDefaultCloseAction();
				BookModiFrame.setBookModiFrameIsNull();
			}
		});
    	 footPanel.add(addButton);
    	 footPanel.add(closeButton);
    	 this.add(backGroundPanel, borderlayout.NORTH);
    	 this.add(mainPanel, borderlayout.CENTER);
    	 this.add(footPanel, borderlayout.SOUTH);   
    	 this.setTitle("图书信息修改"); 
    	 this.setFrameIcon(new ImageIcon("resource/image/icons.jpg"));//设置头标
    	 this.setSize(800, 600);   	 
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
		 */
	     public static BookModiFrame getBookModiFrame(){
	    	 if(bookModiFrame==null){
	    		 bookModiFrame=new BookModiFrame();   		 
	    	 }
	    		 return bookModiFrame;   	    	 
	     }
	     public static void setBookModiFrameIsNull(){
	    	 if(bookModiFrame!=null){
	    		 bookModiFrame=null;
	    	 }
	    	 
	     }
	     public static Object[][] getBookInfoAll(List<Book_Info> list){
	    	 Object[][] result=new Object[list.size()][Constant.bookinfocolumnNames.length];
	    	 for (int i = 0; i < list.size(); i++) {
	    		 Book_Info book_Info=list.get(i);
				result[i][0]=book_Info.getISBN();//设置id
				result[i][1]=book_Info.getTypeName();//
				result[i][2]=book_Info.getBookName();//设置i性别
				result[i][3]=book_Info.getWriter();//设置年龄
				result[i][4]=book_Info.getTranslator();//设置证件号码
				result[i][5]=book_Info.getPublisher();//设置修改日期
				result[i][6]=book_Info.getPubllishDate();//设置电话号码
				result[i][7]=book_Info.getPrice();//设置当前密码
			}
	    	 return result;	    	 
	     }
}
