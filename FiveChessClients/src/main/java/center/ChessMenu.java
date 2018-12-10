package center;

import Common.MessageUtils;
import author.Author;
import bean.Const;
import net.NetworkInit;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author LuoMing luom@3vjia.com
 * 棋盘菜单栏
 * @since 2018-12-06 15:10
 */
public class ChessMenu extends JFrame implements ActionListener {
    private ChessFrame panel;
    private MessageQueuePanel mqPanel;
    public JMenuBar bar;
    public JMenu  select,edit,about;
    public JMenuItem start,exit,computer,back,author,fail;
    private NetworkInit network = null;
    private JFrame frame = null;
    private final int width = 772; // 宽度
    private final int height = 592;
    private final int boardWidth = 535;
    public ChessMenu(){
        super("五子棋");
        this.setLayout(null);
        mqPanel = new MessageQueuePanel();
        mqPanel.setBounds(width - (width - boardWidth), 0, width - boardWidth, height);
        panel = new ChessFrame(mqPanel);
        panel.setBounds(0, 0, width - (width - boardWidth), height);
        bar = new JMenuBar();
        select = new JMenu("选项");
        start = new JMenuItem("单人对局");
        exit = new JMenuItem("退出");
        select.add(start);
        // select.addSeparator(); 在菜单中间加一条横线
        select.add(exit);
        edit = new JMenu("联机对战");
        computer = new JMenuItem("登陆服务器");
        back = new JMenuItem("悔棋");
        fail = new JMenuItem("认输");
        edit.add(fail);
        edit.add(back);
        edit.add(computer);
        about = new JMenu("关于");
        author = new JMenuItem("作者");
        about.add(author);
        bar.add(select);
        bar.add(edit);
        bar.add(about);
        this.add(mqPanel);
        this.add(panel);
        this.setJMenuBar(bar);
        this.setSize(width, height);
        this.setResizable(false);
        // this.setMinimumSize(new Dimension(540, 580)); // 允许最小尺寸
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.addActionListener(this);
        exit.addActionListener(this);
        fail.addActionListener(this);
        back.addActionListener(this);
        computer.addActionListener(this);
        author.addActionListener(this);
   }
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem=(JMenuItem)e.getSource();
        if(menuItem==exit){
            if(JOptionPane.showConfirmDialog(this,"您真的要退出吗？","温馨提示",JOptionPane.YES_NO_OPTION)==0){
                 System.exit(0);
            }
        }else if(menuItem==back){
            panel.toBack();
        }else if(menuItem==start){
            panel.refurbish();
        }else if(menuItem==computer){
            if(panel.isNetworkPK && !panel.gameOver){
                JOptionPane.showMessageDialog(frame, "您正在进行网络对战");
                return;
            }
            if(network == null) {
                frame = new JFrame("联机对战");
                network = new NetworkInit(panel, frame);
                frame.add(network);
                frame.setSize(300, 300);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
//        		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else {
                frame.setVisible(true);
                network.initData();
            }
        }else if(menuItem==author){
            new Author();
        }else if(menuItem == fail){
            if(panel.isNetworkPK && !panel.gameOver){
                if(JOptionPane.showConfirmDialog(frame, "确定认输？", "系统提示", JOptionPane.YES_NO_OPTION) == 0){
                    JSONObject responseMsg = new JSONObject();
                    responseMsg.put(Const.ID, Const.ID_STATUS_FAIL);
                    responseMsg.put(Const.MY, panel.userId);
                    MessageUtils.sendJson(responseMsg);
                    panel.gameOver = true;
                    panel.MQPanel.stopTimer();
                    // myChessColor -> true：我方棋子颜色。如果是认输则为对方颜色获得胜利
                    JOptionPane.showMessageDialog(frame, panel.myChessColor ? "白棋获得胜利" : "黑棋获得胜利");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "您不是在进行网络对战");
            }
        }
    }
}
