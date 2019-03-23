package com.Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import com.ActionListener.bookTypeModi.bookTypeModiTableListener;
import com.Const.Constant;
import com.Dao.Book_Info_Dao;
import com.Dao.Book_Type_Dao;
import com.Dao.Dao;
import com.bean.Book_Info;
import com.bean.Book_Type;

public class BookTypeModiFrame extends JInternalFrame {
	 private static BookTypeModiFrame bookTypeModiFrame;
	 public  JTextField typenamefield;
	 public  JTextField daysfield;
	 public  JTextField fkfield;
	 public JTable table;
	 public String typeid=null;
	 public BookTypeModiFrame(){
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
			 Object[][] result = getBookTypeAll(new Book_Type_Dao().selectBooktype());
			 DefaultTableModel tablemodel=new DefaultTableModel(result, Constant.booktypelumnNames){
				 @Override
				public boolean isCellEditable(int arg0, int arg1) {
					// TODO Auto-generated method stub
					return false;
				}				 
			 };
			 table=new JTable(tablemodel);
	    	 table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    	 table.setRowHeight(30);
	    	 table.addMouseListener(new bookTypeModiTableListener());
	    	 scrollPanel.setViewportView(table);
	    	 mainPanel.add(scrollPanel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
    	 JPanel modiPanel=new JPanel(new GridLayout(1, 6,10,10)); 	
    	 modiPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)) ; //设置面板距离上下左右的边距 顺序是上右下左 顺时针方向	 
    	 modiPanel.setPreferredSize(new Dimension(getWidth(), 40));//设置背景面板大小
    	 
    	 JLabel typenamelabel=new JLabel("图书类别名称");
    	 JLabel dayslabel=new JLabel("可借天数");
    	 JLabel fklabel=new JLabel("罚款(迟还一天的罚款数)");
    	 
    	 typenamefield=new JTextField();
    	 typenamefield.addFocusListener(new FocusAdapter() {
    		@Override
    		public void focusGained(FocusEvent arg0) {
    			// TODO Auto-generated method stub
    			 typenamefield.setText("");
    		}
		});
    	 daysfield=new JTextField();
    	 daysfield.addFocusListener(new FocusAdapter() {
    		 @Override
    		public void focusGained(FocusEvent arg0) {
    			// TODO Auto-generated method stub
    			 daysfield.setText("");
    		}
		});
    	 fkfield=new JTextField("单位为角");
    	 fkfield.addFocusListener(new FocusAdapter() {
    		 @Override
    		public void focusGained(FocusEvent arg0) {
    			// TODO Auto-generated method stub
    			 fkfield.setText("");
    		}
		});
     	  
     	 modiPanel.add(typenamelabel);
     	 modiPanel.add(typenamefield);
     	 modiPanel.add(dayslabel);
     	 modiPanel.add(daysfield);
     	 modiPanel.add(fklabel);
    	 modiPanel.add(fkfield);
     	  
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
    	 this.setTitle("图书类别修改"); 
    	 this.setFrameIcon(new ImageIcon("resource/image/icons.jpg"));//设置头标
    	 this.setSize(650, 500);   	 
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
	     public static BookTypeModiFrame getBookTypeModiFrame(){
	    	 if(bookTypeModiFrame==null){
	    		 bookTypeModiFrame=new BookTypeModiFrame();   		 
	    	 }
	    		 return bookTypeModiFrame;   	    	 
	     }
	     public static void setBookTypeModiFrameIsNull(){
	    	 if(bookTypeModiFrame!=null){
	    		 bookTypeModiFrame=null;
	    	 }
	    	 
	     }
	     public static Object[][] getBookTypeAll(List<Book_Type> list){
	    	 Object[][] result=new Object[list.size()][Constant.booktypelumnNames.length];
	    	 for (int i = 0; i < list.size(); i++) {
	    		 Book_Type book_Type=list.get(i);
	    		result[i][0]=book_Type.getTypeId();//设置类型编号
				result[i][1]=book_Type.getTypename();//设置类型名称
				result[i][2]=book_Type.getDays();//获得可借天数
				result[i][3]=book_Type.getFk();//获得罚款			
			}
	    	 return result;	    	 
	     }
}
