package com.Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.IconUIResource;

import com.ActionListener.onloadButtonAction;
import com.ActionListener.resetButtonAction;
/**
 * 登陆窗体
 * @author LMingX0103380
 *
 */
public class OnloadFrame extends JFrame {
	public static JTextField usernamefield;
	public static JPasswordField passwordfield;
	public JButton onload;
	public JButton reset;
	private static OnloadFrame onloadFrame;
	private OnloadFrame(){
    	 BorderLayout borderlayout=new BorderLayout();
    	 this.setLayout(borderlayout);
    	 JPanel backGroundPanel=new JPanel();//初始化一个背景面板；   	 
    	 JLabel backgroudlabel=new JLabel(new ImageIcon("resource/image/background.jpg"));//设置背景图片
    	 backgroudlabel.setOpaque(true);
    	 backGroundPanel.add(backgroudlabel);
    	 backGroundPanel.setPreferredSize(new Dimension(getWidth(), 60));//设置背景面板大小  	     	 
    	 JPanel mainPanel=new JPanel(new GridLayout(2, 2,0,10));//初始化一个中间面板；
    	 mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 50, 0, 10)) ; //设置面板距离上下左右的边距 顺序是上右下左 顺时针方向	 
    	 JLabel usernamelabel=new JLabel("用户名");//初始化一个用户名label
    	 JLabel passwordlabel=new JLabel("密码");//初始化一个密码label
    	 usernamefield=new JTextField();
    	 passwordfield=new JPasswordField();
    	 passwordfield.addKeyListener(new KeyAdapter() {//给密码文本框添加一个按键点击事件，按回车就可以登陆
    		 public void keyPressed(final KeyEvent e){
    			 if(e.getKeyCode()==10){
    				 try {
						onloadButtonAction.login(usernamefield.getText(), new String(passwordfield.getPassword()));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    			 }
    		 }
		});
    	 mainPanel.add(usernamelabel);    	 
    	 mainPanel.add(usernamefield);
    	 mainPanel.add(passwordlabel);
    	 mainPanel.add(passwordfield);
    	 JPanel footPanel=new JPanel();//初始化一个底部面板；
    	 onload=new JButton("登陆");
    	 onload.addActionListener(new onloadButtonAction());//给登陆按钮添加点击事件
    	 reset=new JButton("重置");
    	 reset.addActionListener(new resetButtonAction());//给重置按钮添加点击事件
    	 footPanel.add(onload);
    	 footPanel.add(reset);
    	 this.add(backGroundPanel, borderlayout.NORTH);
    	 this.add(mainPanel, borderlayout.CENTER);
    	 this.add(footPanel, borderlayout.SOUTH);
    	 this.setIconImage(new ImageIcon("resource/image/icons.jpg").getImage());//设置窗体头标图片
    	 this.setTitle("图书馆管理系统登陆");
    	 this.setSize(325, 225);
    	 Toolkit tool= Toolkit.getDefaultToolkit();   	 
    	 this.setLocation(new Point((tool.getScreenSize().width-getWidth())/2,(tool.getScreenSize().height-getHeight())/2));//获取屏幕大小和窗口大小并取其中间间隔，使其窗体位于屏幕中间
    	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  	
    	 this.setVisible(true);
    	 this.setResizable(false);//设置窗体不可改变大小
     }
	/**
	 * 单例模式 获取对象
	 * @author LMingX0103380
	 * @return
	 */
     public static OnloadFrame getOnloadFrame(){
    	 if(onloadFrame==null){
    		 onloadFrame=new OnloadFrame();   		 
    	 }
    		 return onloadFrame;   	    	 
     }
}
