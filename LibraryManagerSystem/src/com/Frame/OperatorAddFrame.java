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
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.ActionListener.closeInterFrameEvent;
import com.ActionListener.operatoradd.operatorAddActionEvent;
import com.Const.Constant;

public class OperatorAddFrame extends JInternalFrame {
	 private static OperatorAddFrame operatorAddFrame;
	 public JTextField usernamefield;
	 public JComboBox  sexbox;
	 public JComboBox  agebox;
	 public JTextField identitycardfield;
	 public JFormattedTextField workdatefield;
	 public JTextField telfield;
	 public JComboBox  adminbox;
	 public JTextField passwordfield;
	 public OperatorAddFrame(){
		 BorderLayout borderlayout=new BorderLayout();
    	 this.setLayout(borderlayout);
    	 JPanel backGroundPanel=new JPanel();//初始化一个背景面板；   	 
    	 JLabel backgroudlabel=new JLabel(new ImageIcon("resource/image/bookaddbackground.jpg"));//设置背景图片
    	 backgroudlabel.setOpaque(true);
    	 backGroundPanel.add(backgroudlabel);
    	 
    	 backGroundPanel.setPreferredSize(new Dimension(getWidth(), 230));//设置背景面板大小  
    	 JPanel mainPanel=new JPanel(new GridLayout(4, 4,10,10));//初始化一个中间面板；
    	 mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 15, 50)) ; //设置面板距离上下左右的边距 顺序是上右下左 顺时针方向	 
    	 JLabel usernamelabel=new JLabel("用户名");
    	 JLabel sexlabel=new JLabel("性别");
    	 JLabel agelabel=new JLabel("年龄");
    	 JLabel identitycardlabel=new JLabel("证件号码");
    	 JLabel workdatelabel=new JLabel("添加时间");
    	 JLabel tellabel=new JLabel("电话号码");
    	 JLabel adminlabel=new JLabel("权限");
    	 JLabel passwordlabel=new JLabel("密码");
    	 
		 
    	 usernamefield=new JTextField();
    	 sexbox=new JComboBox(Constant.sexContent); 
    	 sexbox.setSelectedIndex(0);//默认选中第一个
    	 agebox=new JComboBox(getAge());
    	 //agebox.setBorder(new TitledBorder(null, "请选择年龄", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION));
    	 agebox.setSelectedIndex(0);
    	 identitycardfield=new JTextField();  
    	 workdatefield=new JFormattedTextField(new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").getInstance());
    	 workdatefield.setValue(new Date());
    	 telfield=new JTextField(); 
    	 adminbox=new JComboBox(Constant.adminContent);
    	 //adminbox.setBorder(BorderFactory.createTitledBorder("选择身份"));
    	 adminbox.setSelectedIndex(0);
    	 passwordfield=new JTextField();
    	 
    	 mainPanel.add(usernamelabel);
    	 mainPanel.add(usernamefield);
    	 mainPanel.add(sexlabel);
    	 mainPanel.add(sexbox);
    	 mainPanel.add(agelabel);
    	 mainPanel.add(agebox);
    	 mainPanel.add(identitycardlabel);
    	 mainPanel.add(identitycardfield);
    	 mainPanel.add(workdatelabel);
    	 mainPanel.add(workdatefield);
    	 mainPanel.add(tellabel);
    	 mainPanel.add(telfield);
    	 mainPanel.add(adminlabel);
    	 mainPanel.add(adminbox);
    	 mainPanel.add(passwordlabel);
    	 mainPanel.add(passwordfield);
    	 
    	 FlowLayout flow=new FlowLayout();
    	 flow.setHgap(30);
    	 flow.setVgap(30);
    	 flow.setAlignment(FlowLayout.RIGHT);
    	 JPanel footPanel=new JPanel(flow);//初始化一个底部面板；
    	 footPanel.setPreferredSize(new Dimension(getWidth(),80));
    	 JButton addButton=new JButton("添加");
    	 addButton.addActionListener(new operatorAddActionEvent());
    	 JButton closeButton=new JButton("关闭");
    	 
    	 closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				getOperatorAddFrame().doDefaultCloseAction();
				setOperatorAddFrameIsNull();
			}
		  });
    	 footPanel.add(addButton);
    	 footPanel.add(closeButton);   	 
    	 this.add(backGroundPanel, borderlayout.NORTH);
    	 this.add(mainPanel, borderlayout.CENTER);
    	 this.add(footPanel, borderlayout.SOUTH);    	 
    	 this.setTitle("用户添加"); 
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
		 */
	     public static OperatorAddFrame getOperatorAddFrame(){
	    	 if(operatorAddFrame==null){
	    		 operatorAddFrame=new OperatorAddFrame();   		 
	    	 }
	    		 return operatorAddFrame;   	    	 
	     }
	     public static void setOperatorAddFrameIsNull(){
	    	 if(operatorAddFrame!=null){
	    		 operatorAddFrame=null;
	    	 }    	 
	     }
	     /**
	      * 获取年龄范围
	      * @author LMingX0103380
	      * @return
	      */
	     public static Vector<String> getAge(){
	    	Vector<String> age=new Vector<String>();
	    	age.add("请选择年龄");
	    	 for (int i = 1; i <= 80; i++) {
	    		 age.add(""+i);
			}
	    	 return age;
	     }
}
