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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ActionListener.closeInterFrameEvent;
import com.ActionListener.bookType.bookTypeActionEvent;

public class BookTypeAddFrame extends JInternalFrame {
	private static BookTypeAddFrame bookTypeAddFrame;
	public  JTextField typenamefield;
	public  JTextField daysfield;
	public  JTextField fkfield;
	public BookTypeAddFrame(){
		 BorderLayout borderlayout=new BorderLayout();
    	 this.setLayout(borderlayout);
    	 JPanel backGroundPanel=new JPanel();//初始化一个背景面板；   	 
    	 JLabel backgroudlabel=new JLabel(new ImageIcon("resource/image/bookaddbackground.jpg"));//设置背景图片
    	 backgroudlabel.setOpaque(true);
    	 backGroundPanel.add(backgroudlabel);
    	 
    	 backGroundPanel.setPreferredSize(new Dimension(getWidth(), 180));//设置背景面板大小  
    	 
    	 JPanel mainPanel=new JPanel(new GridLayout(3, 2,10,10));//初始化一个中间面板；
    	 mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 50, 10, 50)) ; //设置面板距离上下左右的边距 顺序是上右下左 顺时针方向	 
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
    	 mainPanel.add(typenamelabel);
    	 mainPanel.add(typenamefield);
    	 mainPanel.add(dayslabel);
    	 mainPanel.add(daysfield);
    	 mainPanel.add(fklabel);
    	 mainPanel.add(fkfield);
    	 
    	 
    	 FlowLayout flow=new FlowLayout();
    	 flow.setHgap(60);
    	 flow.setVgap(30);
    	 flow.setAlignment(FlowLayout.CENTER);
    	 JPanel footPanel=new JPanel(flow);//初始化一个底部面板；
    	 footPanel.setPreferredSize(new Dimension(getWidth(),80));
    	 JButton addButton=new JButton("添加");
    	 JButton closeButton=new JButton("关闭");
    	 addButton.addActionListener(new bookTypeActionEvent());//添加按钮点击事件
    	 closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				BookTypeAddFrame.getBookTypeAddFrame().doDefaultCloseAction();
				BookTypeAddFrame.setBookTypeAddFrameIsNull();
			}
		});
    	 footPanel.add(addButton);
    	 footPanel.add(closeButton);   	 
    	 this.add(backGroundPanel, borderlayout.NORTH);
    	 this.add(mainPanel, borderlayout.CENTER);
    	 this.add(footPanel, borderlayout.SOUTH);    	 
    	 this.setTitle("图书类别添加"); 
    	 this.setFrameIcon(new ImageIcon("resource/image/icons.jpg"));//设置头标
    	 this.setSize(500, 450);   	 
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
     public static BookTypeAddFrame getBookTypeAddFrame(){
    	 if(bookTypeAddFrame==null){
    		 bookTypeAddFrame=new BookTypeAddFrame();   		 
    	 }
    		 return bookTypeAddFrame;   	    	 
     }
     public static void setBookTypeAddFrameIsNull(){
    	 if(bookTypeAddFrame!=null){
    		 bookTypeAddFrame=null;
    	 }
    	 
     }
}
