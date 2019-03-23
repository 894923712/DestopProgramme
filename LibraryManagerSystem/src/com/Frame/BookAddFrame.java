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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.ActionListener.closeInterFrameEvent;
import com.ActionListener.bookadd.bookAddActionEvent;
import com.Const.Constant;
import com.Dao.Dao;

public class BookAddFrame extends JInternalFrame {
	 private static BookAddFrame bookAddFrame;
	 public  JTextField booknumfield;
	 public JComboBox categorybox;
	 public JTextField booknamefield;
	 public JTextField authorfield;
	 public JComboBox  publishbox;
	 public JTextField translatorfield;
	 public JFormattedTextField pubdatefield;
	 public JTextField perpricefield;
	 private BookAddFrame(){
    	 BorderLayout borderlayout=new BorderLayout();
    	 this.setLayout(borderlayout);
    	 JPanel backGroundPanel=new JPanel();//初始化一个背景面板；   	 
    	 JLabel backgroudlabel=new JLabel(new ImageIcon("resource/image/bookaddbackground.jpg"));//设置背景图片
    	 backgroudlabel.setOpaque(true);
    	 backGroundPanel.add(backgroudlabel);
    	 
    	 backGroundPanel.setPreferredSize(new Dimension(getWidth(), 230));//设置背景面板大小  	   
    	 JPanel mainPanel=new JPanel(new GridLayout(4, 4,10,10));//初始化一个中间面板；
    	 mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 50, 10, 50)) ; //设置面板距离上下左右的边距 顺序是上右下左 顺时针方向	 
    	 JLabel booknumlabel=new JLabel("图书编号");
    	 JLabel categorylabel=new JLabel("类别");
    	 JLabel booknamelabel=new JLabel("书名");
    	 JLabel authorlabel=new JLabel("作者");
    	 JLabel publishlabel=new JLabel("出版社");
    	 JLabel translatorlabel=new JLabel("译者");
    	 JLabel pubdatelabel=new JLabel("出版日期");
    	 JLabel perpricelabel=new JLabel("单价");
    	 
    	 booknumfield=new JTextField();    	 
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
    	 
    	 mainPanel.add(booknumlabel);
    	 mainPanel.add(booknumfield);
    	 mainPanel.add(categorylabel);
    	 mainPanel.add(categorybox);
    	 mainPanel.add(booknamelabel);
    	 mainPanel.add(booknamefield);
    	 mainPanel.add(authorlabel);
    	 mainPanel.add(authorfield);
    	 mainPanel.add(publishlabel);
    	 mainPanel.add(publishbox);
    	 mainPanel.add(translatorlabel);
    	 mainPanel.add(translatorfield);
    	 mainPanel.add(pubdatelabel);
    	 mainPanel.add(pubdatefield);
    	 mainPanel.add(perpricelabel);
    	 mainPanel.add(perpricefield);
    	 
    	 FlowLayout flow=new FlowLayout();
    	 flow.setHgap(30);
    	 flow.setVgap(30);
    	 flow.setAlignment(FlowLayout.RIGHT);
    	 JPanel footPanel=new JPanel(flow);//初始化一个底部面板；
    	 footPanel.setPreferredSize(new Dimension(getWidth(),80));
    	 JButton addButton=new JButton("添加");
    	 addButton.addActionListener(new bookAddActionEvent());
    	 JButton closeButton=new JButton("关闭");
    	 
    	 closeButton.addActionListener(new closeInterFrameEvent());
    	 footPanel.add(addButton);
    	 footPanel.add(closeButton);   	 
    	 this.add(backGroundPanel, borderlayout.NORTH);
    	 this.add(mainPanel, borderlayout.CENTER);
    	 this.add(footPanel, borderlayout.SOUTH);    	 
    	 this.setTitle("图书信息添加"); 
    	 this.setFrameIcon(new ImageIcon("resource/image/icons.jpg"));//设置头标
    	 this.setSize(650, 550);   	 
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
	     public static BookAddFrame getBookAddFrame(){
	    	 if(bookAddFrame==null){
	    		 bookAddFrame=new BookAddFrame();   		 
	    	 }
	    		 return bookAddFrame;   	    	 
	     }
	     public static void setBookAddFrameIsNull(){
	    	 if(bookAddFrame!=null){
	    		 bookAddFrame=null;
	    	 }
	    	 
	     }
}
