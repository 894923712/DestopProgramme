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
import java.util.Vector;

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

import com.ActionListener.operatorModi.operatorModiActionEvent;
import com.ActionListener.operatorModi.operatorTableListener;
import com.Const.Constant;
import com.Dao.Operator_Dao;
import com.bean.Operator;

public class OperatorModiFrame extends JInternalFrame {
	 private static OperatorModiFrame operatorModiFrame;
	 public JTextField usernamefield;
	 public JComboBox  sexbox;
	 public JComboBox  agebox;
	 public JTextField identitycardfield;
	 public JFormattedTextField workdatefield;
	 public JTextField telfield;
	 public JComboBox  adminbox;
	 public JTextField passwordfield;
	 public JTable table;
	 public String id=null;
	 //public DefaultTableModel tablemodel;
	 public OperatorModiFrame(){
		 BorderLayout borderlayout=new BorderLayout();
    	 this.setLayout(borderlayout);
    	 JPanel backGroundPanel=new JPanel();//初始化一个背景面板；   	 
    	 JLabel backgroudlabel=new JLabel(new ImageIcon("resource/image/operatorModibackground.jpg"));//设置背景图片
    	 backgroudlabel.setOpaque(true);
    	 backGroundPanel.add(backgroudlabel);   	 
    	 backGroundPanel.setPreferredSize(new Dimension(getWidth(), 100));//设置背景面板大小
    	 
    	 JPanel mainPanel=new JPanel(new BorderLayout(10,10));//初始化一个中间面板；
    	 JScrollPane scrollPanel=new JScrollPane();
    	 //scrollPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
		try {
			 Object[][] result = getOperatorAll(new Operator_Dao().selectOperation());
			 DefaultTableModel tablemodel=new DefaultTableModel(result, Constant.operatorcolumnNames){
				 
				 @Override
				public boolean isCellEditable(int arg0, int arg1) {
					// TODO Auto-generated method stub
					return false;
				}
			 };
			 table=new JTable(tablemodel);
	    	 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    	 table.setRowHeight(30);
	    	 table.addMouseListener(new operatorTableListener());
	    	 table.getColumnModel().getColumn(4).setPreferredWidth(180);
	    	 table.getColumnModel().getColumn(5).setPreferredWidth(180);
	    	 table.getColumnModel().getColumn(6).setPreferredWidth(120);
	    	 scrollPanel.setViewportView(table);
	    	 mainPanel.add(scrollPanel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //结果要从数据库中获取，目前先设为空   	 
    	 
    	 
    	 JPanel modiPanel=new JPanel(new GridLayout(3, 6,10,10));
    	 modiPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)) ; //设置面板距离上下左右的边距 顺序是上右下左 顺时针方向	 
    	 modiPanel.setPreferredSize(new Dimension(getWidth(), 120));//设置背景面板大小
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
    	 
    	 modiPanel.add(usernamelabel);
    	 modiPanel.add(usernamefield);
    	 modiPanel.add(sexlabel);
    	 modiPanel.add(sexbox);
    	 modiPanel.add(agelabel);
    	 modiPanel.add(agebox);
    	 modiPanel.add(identitycardlabel);
    	 modiPanel.add(identitycardfield);
    	 modiPanel.add(workdatelabel);
    	 modiPanel.add(workdatefield);
    	 modiPanel.add(tellabel);
    	 modiPanel.add(telfield);
    	 modiPanel.add(adminlabel);
    	 modiPanel.add(adminbox);
    	 modiPanel.add(passwordlabel);
    	 modiPanel.add(passwordfield);
    	 
    	 
    	 mainPanel.add(modiPanel,BorderLayout.SOUTH);
    	 
    	 FlowLayout flow=new FlowLayout();
    	 flow.setHgap(30);
    	 flow.setVgap(30);
    	 flow.setAlignment(FlowLayout.RIGHT);
    	 JPanel footPanel=new JPanel(flow);//初始化一个底部面板；
    	 footPanel.setPreferredSize(new Dimension(getWidth(),80));
    	 JButton addButton=new JButton("修改");
    	 addButton.addActionListener(new operatorModiActionEvent());
    	 JButton closeButton=new JButton("关闭");
    	 
    	 closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				getOperatorModiFrame().doDefaultCloseAction();
				setOperatorModiFrameIsNull();
			}
		  });
    	 footPanel.add(addButton);
    	 footPanel.add(closeButton);   	 
    	 this.add(backGroundPanel, borderlayout.NORTH);
    	 this.add(mainPanel, borderlayout.CENTER);
    	 this.add(footPanel, borderlayout.SOUTH);    	 
    	 this.setTitle("用户修改"); 
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
	     public static OperatorModiFrame getOperatorModiFrame(){
	    	 if(operatorModiFrame==null){
	    		 operatorModiFrame=new OperatorModiFrame();   		 
	    	 }
	    		 return operatorModiFrame;   	    	 
	     }
	     public static void setOperatorModiFrameIsNull(){
	    	 if(operatorModiFrame!=null){
	    		 operatorModiFrame=null;
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
	     
	     public static Object[][] getOperatorAll(List<Operator> list){
	    	 Object[][] result=new Object[list.size()][Constant.operatorcolumnNames.length];
	    	 for (int i = 0; i < list.size(); i++) {
				Operator operator=list.get(i);
				result[i][0]=operator.getId();//设置id
				result[i][1]=operator.getName();//设置用户名
				result[i][2]=operator.getSex();//设置i性别
				result[i][3]=operator.getAge();//设置年龄
				result[i][4]=operator.getIdentitycard();//设置证件号码
				result[i][5]=operator.getWorkDate();//设置修改日期
				result[i][6]=operator.getTel();//设置电话号码
				if(operator.getAdmin()==1){				
					result[i][7]="管理员";
				}else{
					result[i][7]="普通用户";
				}				
				result[i][8]=operator.getPassword();//设置当前密码
			}
	    	 return result;
	     }
}
