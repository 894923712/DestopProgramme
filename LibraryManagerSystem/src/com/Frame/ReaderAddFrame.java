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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import com.ActionListener.closeInterFrameEvent;
import com.ActionListener.bookadd.bookAddActionEvent;
import com.ActionListener.readerAdd.readerAddActionEvent;
import com.Const.Constant;
import com.Dao.Dao;

public class ReaderAddFrame extends JInternalFrame {
	 private static ReaderAddFrame readerAddFrame;
	 public JTextField namefield;
	 public ButtonGroup sexgroup;
	 public JRadioButton maleradio;
	 public JRadioButton femaleradio;
	 public JComboBox  agebox;
	 public JTextField jobfield;
	 public JComboBox  identityCardTYpebox; //证件类型
	 public JTextField identityCardfield;//证件号码
	 public JTextField maxborrownumfield;
	 public JFormattedTextField Vipdatefield;//会员证件有效期
	 public JTextField telfield;
	 public JTextField keeymoneyfield;
	 public JFormattedTextField bzdatefield;//办证日期
	 //public JTextField readeridfield;//读者编号
	 public ReaderAddFrame() {
		// TODO Auto-generated constructor stub
		 BorderLayout borderlayout=new BorderLayout();
    	 this.setLayout(borderlayout);
    	 JPanel backGroundPanel=new JPanel();//初始化一个背景面板；   	 
    	 JLabel backgroudlabel=new JLabel(new ImageIcon("resource/image/readerAdd.jpg"));//设置背景图片
    	 backgroudlabel.setOpaque(true);
    	 backGroundPanel.add(backgroudlabel);
    	 
    	 backGroundPanel.setPreferredSize(new Dimension(getWidth(), 230));//设置背景面板大小  	   
    	 JPanel mainPanel=new JPanel(new GridLayout(6, 4,10,10));//初始化一个中间面板；
    	 mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 50, 10, 50)) ; //设置面板距离上下左右的边距 顺序是上右下左 顺时针方向	 
    	 JLabel namelabel=new JLabel("姓名");
    	 JLabel sexlabel=new JLabel("性别");
    	 JLabel agelabel=new JLabel("年龄");
    	 JLabel joblabel=new JLabel("职业");
    	 JLabel identityTypelabel=new JLabel("有效证件");
    	 JLabel identityCardlabel=new JLabel("证件号码");
    	 JLabel maxborrownumlabel=new JLabel("最大借书量");
    	 JLabel vipdatelabel=new JLabel("会员证有效日期");
    	 JLabel tellabel=new JLabel("电话号码");
    	 JLabel keeymoneylabel=new JLabel("押金");
    	 JLabel bztimelabel=new JLabel("办证日期");
    	// JLabel readeridlabel=new JLabel("读者编号");
    	 namefield=new JTextField();    	 
		 
    	 maleradio=new JRadioButton("男");
		 femaleradio=new JRadioButton("女")	;
		 sexgroup=new ButtonGroup();
		 sexgroup.add(maleradio);
		 maleradio.setSelected(true);//默认选中男
		 sexgroup.add(femaleradio);
		 JPanel sexpanel=new JPanel(new FlowLayout());
		 sexpanel.add(maleradio);
		 sexpanel.add(femaleradio);
		 
		 agebox=new JComboBox(getAge());
		 agebox.setSelectedIndex(0);
		 jobfield=new JTextField();
		 identityCardTYpebox=new JComboBox(Constant.identityType);
		 identityCardTYpebox.setSelectedIndex(0);
    	 identityCardfield=new JTextField();   
    	 maxborrownumfield=new JTextField();
    	 Vipdatefield=new JFormattedTextField(new SimpleDateFormat("YYYY-MM-dd").getInstance());
    	 Vipdatefield.setValue(new Date());//会员证件有效期暂时设置为当天有效；
    	 telfield=new JTextField();
    	 keeymoneyfield=new JTextField();
    	 bzdatefield=new JFormattedTextField(new SimpleDateFormat("YYYY-MM-dd").getInstance());
    	 bzdatefield.setValue(new Date());
    	// readeridfield=new JTextField();
    	 
    	 mainPanel.add(namelabel);
    	 mainPanel.add(namefield);
    	 mainPanel.add(sexlabel);
    	 mainPanel.add(sexpanel);
    	 mainPanel.add(agelabel);
    	 mainPanel.add(agebox);
    	 mainPanel.add(joblabel);
    	 mainPanel.add(jobfield);
    	 mainPanel.add(identityTypelabel);
    	 mainPanel.add(identityCardTYpebox);
    	 mainPanel.add(identityCardlabel);
    	 mainPanel.add(identityCardfield);
    	 mainPanel.add(maxborrownumlabel);
    	 mainPanel.add(maxborrownumfield);
    	 mainPanel.add(vipdatelabel);
    	 mainPanel.add(Vipdatefield);
    	 mainPanel.add(tellabel);
    	 mainPanel.add(telfield);
    	 mainPanel.add(keeymoneylabel);
    	 mainPanel.add(keeymoneyfield);
    	 mainPanel.add(bztimelabel);
    	 mainPanel.add(bzdatefield);
    	// mainPanel.add(readeridlabel);
    	// mainPanel.add(readeridfield);
    	 
    	 FlowLayout flow=new FlowLayout();
    	 flow.setHgap(30);
    	 flow.setVgap(30);
    	 flow.setAlignment(FlowLayout.CENTER);
    	 JPanel footPanel=new JPanel(flow);//初始化一个底部面板；
    	 footPanel.setPreferredSize(new Dimension(getWidth(),80));
    	 JButton addButton=new JButton("添加");
    	 addButton.addActionListener(new readerAddActionEvent());
    	 JButton closeButton=new JButton("关闭");
    	 
    	 closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ReaderAddFrame.getReaderAddFrame().doDefaultCloseAction();
				ReaderAddFrame.setReaderAddFrameIsNull();
			}
		});
    	 footPanel.add(addButton);
    	 footPanel.add(closeButton);   	 
    	 this.add(backGroundPanel, borderlayout.NORTH);
    	 this.add(mainPanel, borderlayout.CENTER);
    	 this.add(footPanel, borderlayout.SOUTH);    	 
    	 this.setTitle("图书信息添加"); 
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
	     public static ReaderAddFrame getReaderAddFrame(){
	    	 if(readerAddFrame==null){
	    		 readerAddFrame=new ReaderAddFrame();   		 
	    	 }
	    		 return readerAddFrame;   	    	 
	     }
	     public static void setReaderAddFrameIsNull(){
	    	 if(readerAddFrame!=null){
	    		 readerAddFrame=null;
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
