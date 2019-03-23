package com.Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import com.ActionListener.exitEvent;
import com.ActionListener.bookCheck.bookCheck;
import com.ActionListener.bookModi.bookModi;
import com.ActionListener.bookOrder.bookOrder;
import com.ActionListener.bookReturn.bookReturn;
import com.ActionListener.bookSearch.bookSearch;
import com.ActionListener.bookType.bookType;
import com.ActionListener.bookTypeModi.bookTypeModi;
import com.ActionListener.bookadd.bookAdd;
import com.ActionListener.bookborrow.bookBorrow;
import com.ActionListener.operatorModi.operatorModi;
import com.ActionListener.operatoradd.operatorAdd;
import com.ActionListener.readerAdd.readerAdd;
import com.ActionListener.readerModi.readerModi;
import com.Dao.Operator_Dao;
import com.Vaildate.AccountCheck;

public class MainFrame extends JFrame{
	/**
	 * 初始化一个桌面面板
	 */	
    public  static final JDesktopPane DesktopPane=new JDesktopPane();
    public JLabel backgroudlabel;
	public MainFrame(String username,String password){
		JMenuBar menubar=CreatMenuBar(username,password);
		JToolBar toolbar=CreatToolBar(username,password);
		
		this.setLayout(new BorderLayout());//设置布局为边界布局
		this.setJMenuBar(menubar);//设置菜单bar
		this.add(toolbar,BorderLayout.NORTH);//添加工具栏在最顶端
		Toolkit tool=Toolkit.getDefaultToolkit();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("resource/image/icons.jpg").getImage());//设置窗体头标图片
		this.setSize(1000, 900);
		this.setLocation((tool.getScreenSize().width-getWidth())/2, (tool.getScreenSize().height-getHeight())/2);
		this.setTitle("图书馆管理系统");		   	 
    	backgroudlabel=new JLabel();
    	backgroudlabel.setBounds(0, 0, 0, 0);
    	backgroudlabel.setIcon(null);    	
    	DesktopPane.addComponentListener(new ComponentAdapter() {
    		public void componentResized(ComponentEvent e){
    			Dimension size=e.getComponent().getSize();
    			backgroudlabel.setSize(size);//设置标签的大小
    			backgroudlabel.setText("<html><img width="+size.width+" height="+size.height+" src='"+this.getClass().getResource("/com/Frame/library.jpg")+"'></html>");
    		};
		});
    	DesktopPane.add(backgroudlabel,new Integer(Integer.MIN_VALUE));    	
		this.getContentPane().add(DesktopPane);		
		this.setVisible(true);
		this.setResizable(false);//设置窗体不可改变大小
	}
	/**
	 * 定义一个创建菜单栏的方法
	 * @author LMingX0103380
	 * @return
	 */
	public JMenuBar CreatMenuBar(String username,String password){
		String isAdminOrCommonOperator=null;
		try {
			isAdminOrCommonOperator = AccountCheck.IsAdminOrCommonOperator(username, password);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JMenuBar menuBar=new JMenuBar();
		//定义几个一级菜单栏
		JMenu menuFirst=new JMenu("基础数据维护");
		JMenu menuSecond=new JMenu("新书订购管理");
		JMenu menuThird=new JMenu("借阅管理");
		JMenu menuFourth=new JMenu("系统维护");
		//将一级菜单栏添加到菜单bar
		menuBar.add(menuFirst);
		menuBar.add(menuSecond);
		menuBar.add(menuThird);
		menuBar.add(menuFourth);
		//定义几个二级菜单栏
		JMenu menuFirstReadInfo=new JMenu("读者信息管理");
		JMenu menuFirstBookCategory=new JMenu("图书类别管理");
		JMenu menuFirstBookInfo=new JMenu("图书信息管理");
		JMenuItem menuFirstExit=new JMenuItem("退出系统");
		//定义退出系统点击事件
		menuFirstExit.addActionListener(new exitEvent());
		//-------------------------------------------------
		JMenuItem menuSecondBuy=new JMenuItem("新书订购");
		menuSecondBuy.addActionListener(new bookOrder());
		JMenuItem menuSecondCheck=new JMenuItem("验收新书");
		menuSecondCheck.addActionListener(new bookCheck());	
		//-------------------------------------------------
		JMenuItem menuThirdBorrowed=new JMenuItem("图书借阅");
		menuThirdBorrowed.addActionListener(new bookBorrow());
		JMenuItem menuThirdReturn=new JMenuItem("图书归还");
		menuThirdReturn.addActionListener(new bookReturn());
		JMenuItem menuThirdSearch=new JMenuItem("图书搜索");	
		menuThirdSearch.addActionListener(new bookSearch());
		//-------------------------------------------------
		JMenuItem menuFourthAlter=new JMenuItem("更改口令");
		menuFourthAlter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String username=OnloadFrame.usernamefield.getText();
				String oldpassword=JOptionPane.showInputDialog(null, "请输入您当前口令","更改口令",JOptionPane.PLAIN_MESSAGE);
				if(oldpassword!=null && oldpassword.equals(new String(OnloadFrame.passwordfield.getPassword()))){
					String newpassword=JOptionPane.showInputDialog(null, "请输入您新口令","更改口令",JOptionPane.INFORMATION_MESSAGE);
					//修改数据库
					try {
						if(newpassword!=null && new Operator_Dao().updateOperationPassWord(username, newpassword)){
							JOptionPane.showMessageDialog(null, "恭喜您修改口令成功","更改口令",JOptionPane.INFORMATION_MESSAGE);
						}
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}else{
					JOptionPane.showMessageDialog(null, "您当前输入的口令有误","更改口令",JOptionPane.INFORMATION_MESSAGE);
				}									
			}
		});
		JMenu menuFourthUserManager=new JMenu("用户管理");
		
		//将二级菜单栏添加到对应的一级菜单栏
		menuFirst.add(menuFirstReadInfo);
		menuFirst.add(menuFirstBookCategory);
		menuFirst.add(menuFirstBookInfo);
		menuFirst.add(menuFirstExit);
		//-------------------------------------------------
		menuSecond.add(menuSecondBuy);
		menuSecond.add(menuSecondCheck);
		
		//-------------------------------------------------
		menuThird.add(menuThirdBorrowed);
		menuThird.add(menuThirdReturn);
		menuThird.add(menuThirdSearch);
		//-------------------------------------------------
		menuFourth.add(menuFourthAlter);
		menuFourth.add(menuFourthUserManager);
		
		//定义几个三级菜单栏
		JMenuItem menuFirstReadInfoAdd=new JMenuItem("读者信息添加");
		menuFirstReadInfoAdd.addActionListener(new readerAdd());
		//-------------------------------------------------
		JMenuItem menuFirstBookCategoriesAdd=new JMenuItem("图书类型添加");
		menuFirstBookCategoriesAdd.addActionListener(new bookType());
		
		//-------------------------------------------------
		JMenuItem menuFirstBookInfoAdd=new JMenuItem("图书信息添加");
		menuFirstBookInfoAdd.addActionListener(new bookAdd());
		
		//-------------------------------------------------
		JMenuItem menuFourthUserAdd=new JMenuItem("用户添加");
		menuFourthUserAdd.addActionListener(new operatorAdd());
		
		
		//添加三级菜单
		menuFirstReadInfo.add(menuFirstReadInfoAdd);
		
		//-------------------------------------------------
		menuFirstBookCategory.add(menuFirstBookCategoriesAdd);
		
		//-------------------------------------------------
		menuFirstBookInfo.add(menuFirstBookInfoAdd);
		
		//-------------------------------------------------
		menuFourthUserManager.add(menuFourthUserAdd);
		if(!AccountCheck.IsEmptyOrNull(isAdminOrCommonOperator) && isAdminOrCommonOperator.equals("管理员")){
			JMenuItem menuFirstReadInfoAlter=new JMenuItem("读者信息修改");
			menuFirstReadInfo.add(menuFirstReadInfoAlter);
			menuFirstReadInfoAlter.addActionListener(new readerModi());
			JMenuItem menuFirstBookCategoriesAlter=new JMenuItem("图书类型修改");
			menuFirstBookCategoriesAlter.addActionListener(new bookTypeModi());
			menuFirstBookCategory.add(menuFirstBookCategoriesAlter);
			JMenuItem menuFirstBookInfoAlter=new JMenuItem("图书信息修改");
			menuFirstBookInfoAlter.addActionListener(new bookModi());
			menuFirstBookInfo.add(menuFirstBookInfoAlter);
			JMenuItem menuFourthUserAlter=new JMenuItem("用户修改");
			menuFourthUserAlter.addActionListener(new operatorModi());
			menuFourthUserManager.add(menuFourthUserAlter);
		}
		return menuBar;
	}
	
	/**
	 * 定义一个创建工具栏的方法
	 * @author LMingX0103380
	 * @return
	 */
	public JToolBar CreatToolBar(String username,String password){
		String isAdminOrCommonOperator=null;
		try {
			isAdminOrCommonOperator = AccountCheck.IsAdminOrCommonOperator(username, password);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JToolBar toolbar=new JToolBar();
		toolbar.setFloatable(false);//设置工具栏不可以被浮动
		toolbar.setBorder(new BevelBorder(BevelBorder.RAISED));
		JButton bookAddButton=new JButton(new ImageIcon("resource/image/add.png"));
		bookAddButton.setHideActionText(true);
		bookAddButton.setToolTipText("图书信息添加"); //设置按钮提示文本
		bookAddButton.addActionListener(new bookAdd());//给图书信息添加点击事件	
		
		JButton bookTypeAddButton=new JButton(new ImageIcon("resource/image/add.png"));
		bookTypeAddButton.setHideActionText(true);
		bookTypeAddButton.setToolTipText("图书类别添加"); //设置按钮提示文本
		bookTypeAddButton.addActionListener(new bookType());
		
		JButton bookBorrowButton=new JButton(new ImageIcon("resource/image/add.png"));
		bookBorrowButton.setHideActionText(true);
		bookBorrowButton.setToolTipText("图书借阅"); //设置按钮提示文本
		bookBorrowButton.addActionListener(new bookBorrow());
		
		JButton bookOrderButton=new JButton(new ImageIcon("resource/image/bookorder.png"));
		bookOrderButton.setHideActionText(true);
		bookOrderButton.setToolTipText("新书订购"); //设置按钮提示文本
		bookOrderButton.addActionListener(new bookOrder());
		
		
		JButton bookCheckButton=new JButton(new ImageIcon("resource/image/add.png"));
		bookCheckButton.setHideActionText(true);
		bookCheckButton.setToolTipText("验收新书"); //设置按钮提示文本
		bookCheckButton.addActionListener(new bookCheck());
		
		JButton readAddButton=new JButton(new ImageIcon("resource/image/add.png"));
		readAddButton.setHideActionText(true);
		readAddButton.setToolTipText("读者信息添加"); //设置按钮提示文本
		readAddButton.addActionListener(new readerAdd());
		
		
		JButton exitButton=new JButton(new ImageIcon("resource/image/exit.jpg"));
		exitButton.setHideActionText(true);
		exitButton.setToolTipText("退出"); //设置按钮提示文本
		exitButton.addActionListener(new exitEvent());
		toolbar.add(bookAddButton);
		
		toolbar.add(bookTypeAddButton);
		toolbar.add(bookBorrowButton);
		toolbar.add(bookOrderButton);
		toolbar.add(bookCheckButton);
		toolbar.add(readAddButton);
		if(!AccountCheck.IsEmptyOrNull(isAdminOrCommonOperator) && isAdminOrCommonOperator.equals("管理员")){
			JButton bookModiAndDelButton=new JButton(new ImageIcon("resource/image/delete.png"));
			bookModiAndDelButton.setHideActionText(true);
			bookModiAndDelButton.setToolTipText("图书信息修改和删除"); //设置按钮提示文本
			bookModiAndDelButton.addActionListener(new bookModi());
			toolbar.add(bookModiAndDelButton);
			
			JButton readModiAndDelButton=new JButton(new ImageIcon("resource/image/readdelete.png"));
			readModiAndDelButton.setHideActionText(true);
			readModiAndDelButton.setToolTipText("读者信息修改和删除"); //设置按钮提示文本
			readModiAndDelButton.addActionListener(new readerModi());
			toolbar.add(readModiAndDelButton);
		}
		toolbar.add(exitButton);	
		return toolbar;
		
	}
	
	/**
	 * 添加子窗体方法
	 * @param frame
	 */
	public static void addIFrame(JInternalFrame frame){
		DesktopPane.add(frame);	
	}
}
