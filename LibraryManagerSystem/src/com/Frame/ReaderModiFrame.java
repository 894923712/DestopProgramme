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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ActionListener.bookModi.bookModiActionEvent;
import com.ActionListener.bookModi.bookModiTableListener;
import com.Const.Constant;
import com.Dao.Reader_Info_Dao;
import com.bean.Reader_info;

public class ReaderModiFrame extends JInternalFrame {
	private static ReaderModiFrame readerModiFrame;
	public JTextField namefield;
	public ButtonGroup sexgroup;
	public JRadioButton maleradio;
	public JRadioButton femaleradio;
	public JComboBox agebox;
	public JTextField jobfield;
	public JComboBox identityCardTYpebox; // 证件类型
	public JTextField identityCardfield;// 证件号码
	public JTextField maxborrownumfield;
	public JFormattedTextField Vipdatefield;// 会员证件有效期
	public JTextField telfield;
	public JTextField keeymoneyfield;
	public JFormattedTextField bzdatefield;// 办证日期
	//public JTextField readeridfield;// 读者编号
    public JTable table;
	public ReaderModiFrame() {
		BorderLayout borderlayout = new BorderLayout();
		this.setLayout(borderlayout);
		JPanel backGroundPanel = new JPanel();// 初始化一个背景面板；
		JLabel backgroudlabel = new JLabel(new ImageIcon(
				"resource/image/operatorModibackground.jpg"));// 设置背景图片
		backgroudlabel.setOpaque(true);
		backGroundPanel.add(backgroudlabel);
		backGroundPanel.setPreferredSize(new Dimension(getWidth(), 100));// 设置背景面板大小

		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		JScrollPane scrollPanel = new JScrollPane();

		try {
			Object[][] result = getReaderInfoAll(new Reader_Info_Dao().selectReaderInfo());
			DefaultTableModel tablemodel = new DefaultTableModel(result,Constant.readerInfocolumnNames) {
				@Override
				public boolean isCellEditable(int arg0, int arg1) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			table = new JTable(tablemodel);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setRowHeight(30);
			table.addMouseListener(new bookModiTableListener());
			table.getColumnModel().getColumn(3).setPreferredWidth(180);
			table.getColumnModel().getColumn(4).setPreferredWidth(180);
			table.getColumnModel().getColumn(6).setPreferredWidth(120);
			table.getColumnModel().getColumn(10).setPreferredWidth(180);
			scrollPanel.setViewportView(table);
			mainPanel.add(scrollPanel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel modiPanel = new JPanel(new GridLayout(4, 6, 10, 10));
		modiPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // 设置面板距离上下左右的边距
																			// 顺序是上右下左
																			// 顺时针方向
		modiPanel.setPreferredSize(new Dimension(getWidth(), 180));// 设置背景面板大小

		JLabel namelabel = new JLabel("姓名");
		JLabel sexlabel = new JLabel("性别");
		JLabel agelabel = new JLabel("年龄");
		JLabel joblabel = new JLabel("职业");
		JLabel identityTypelabel = new JLabel("有效证件");
		JLabel identityCardlabel = new JLabel("证件号码");
		JLabel maxborrownumlabel = new JLabel("最大借书量");
		JLabel vipdatelabel = new JLabel("会员证有效日期");
		JLabel tellabel = new JLabel("电话号码");
		JLabel keeymoneylabel = new JLabel("押金");
		JLabel bztimelabel = new JLabel("办证日期");
		//JLabel readeridlabel = new JLabel("读者编号");

		namefield = new JTextField();
		maleradio = new JRadioButton("男");
		femaleradio = new JRadioButton("女");
		sexgroup = new ButtonGroup();
		sexgroup.add(maleradio);
		maleradio.setSelected(true);// 默认选中男
		sexgroup.add(femaleradio);
		JPanel sexpanel = new JPanel(new FlowLayout());
		sexpanel.add(maleradio);
		sexpanel.add(femaleradio);

		agebox = new JComboBox(getAge());
		agebox.setSelectedIndex(0);
		jobfield = new JTextField();
		identityCardTYpebox = new JComboBox(Constant.identityType);
		identityCardTYpebox.setSelectedIndex(0);
		identityCardfield = new JTextField();
		maxborrownumfield = new JTextField();
		Vipdatefield = new JFormattedTextField(new SimpleDateFormat("YYYY-MM-dd").getInstance());
		Vipdatefield.setValue(new Date());// 会员证件有效期暂时设置为当天有效；
		telfield = new JTextField();
		keeymoneyfield = new JTextField();
		bzdatefield = new JFormattedTextField(new SimpleDateFormat("YYYY-MM-dd").getInstance());
		bzdatefield.setValue(new Date());
		//readeridfield = new JTextField();

		modiPanel.add(namelabel);
		modiPanel.add(namefield);
		modiPanel.add(sexlabel);
		modiPanel.add(sexpanel);
		modiPanel.add(agelabel);
		modiPanel.add(agebox);
		modiPanel.add(joblabel);
		modiPanel.add(jobfield);
		modiPanel.add(identityTypelabel);
		modiPanel.add(identityCardTYpebox);
		modiPanel.add(identityCardlabel);
		modiPanel.add(identityCardfield);
		modiPanel.add(maxborrownumlabel);
		modiPanel.add(maxborrownumfield);
		modiPanel.add(vipdatelabel);
		modiPanel.add(Vipdatefield);
		modiPanel.add(tellabel);
		modiPanel.add(telfield);
		modiPanel.add(keeymoneylabel);
		modiPanel.add(keeymoneyfield);
		modiPanel.add(bztimelabel);
		modiPanel.add(bzdatefield);
		//modiPanel.add(readeridlabel);
		//modiPanel.add(readeridfield);
		
		mainPanel.add(modiPanel, BorderLayout.SOUTH);
		FlowLayout flow = new FlowLayout();
		flow.setHgap(30);
		flow.setVgap(30);
		flow.setAlignment(FlowLayout.RIGHT);
		JPanel footPanel = new JPanel(flow);// 初始化一个底部面板；
		footPanel.setPreferredSize(new Dimension(getWidth(), 80));
		JButton addButton = new JButton("修改");
		addButton.addActionListener(new bookModiActionEvent());
		JButton closeButton = new JButton("关闭");
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ReaderModiFrame.getReaderModiFrame().doDefaultCloseAction();
				ReaderModiFrame.setReaderModiFrameIsNull();
			}
		});
		footPanel.add(addButton);
		footPanel.add(closeButton);
		this.add(backGroundPanel, borderlayout.NORTH);
		this.add(mainPanel, borderlayout.CENTER);
		this.add(footPanel, borderlayout.SOUTH);
		this.setTitle("读者信息修改");
		this.setFrameIcon(new ImageIcon("resource/image/icons.jpg"));// 设置头标
		this.setSize(800, 600);
		this.setLocation(new Point(
				(MainFrame.DesktopPane.getWidth() - getWidth()) / 2,
				(MainFrame.DesktopPane.getHeight() - getHeight()) / 2));// 获取屏幕大小和窗口大小并取其中间间隔，使其窗体位于屏幕中间
		this.setClosable(true);
		this.setIconifiable(true);// 设置窗体可最小化
		this.setResizable(false);// 设置窗体不可改变大小
		this.setVisible(true);

	}

	/**
	 * 单例模式 获取对象
	 * 
	 * @author LMingX0103380
	 * @return
	 */
	public static ReaderModiFrame getReaderModiFrame() {
		if (readerModiFrame == null) {
			readerModiFrame = new ReaderModiFrame();
		}
		return readerModiFrame;
	}

	public static void setReaderModiFrameIsNull() {
		if (readerModiFrame != null) {
			readerModiFrame = null;
		}

	}

	/**
	 * 获取年龄范围
	 * 
	 * @author LMingX0103380
	 * @return
	 */
	public static Vector<String> getAge() {
		Vector<String> age = new Vector<String>();
		age.add("请选择年龄");
		for (int i = 1; i <= 80; i++) {
			age.add("" + i);
		}
		return age;
	}

	public static Object[][] getReaderInfoAll(List<Reader_info> list) {
		Object[][] result = new Object[list.size()][Constant.readerInfocolumnNames.length];
		for (int i = 0; i < list.size(); i++) {
			Reader_info reader_info = list.get(i);
			//result[i][0] = reader_info.getISBN();// 设置读者编号
			result[i][0] = reader_info.getName();// 设置读者姓名
			result[i][1] = reader_info.getSex();// 设置i性别
			result[i][2] = reader_info.getAge();// 设置年龄
			result[i][3] = reader_info.getIdentityCard();// 设置证件号码
			result[i][4] = reader_info.getDate();// 设置会员证件有效日期
			result[i][5] = reader_info.getMaxnum();// 设置最大借书量
			result[i][6] = reader_info.getTel();// 设置电话号码
			result[i][7] = reader_info.getKeepmoney();// 设置押金
			result[i][8] = reader_info.getZj();// 设置证件类型
			result[i][9] = reader_info.getJob();// 设置职业
			result[i][10] = reader_info.getBzTime();// 设置办证时间

		}
		return result;
	}

}
