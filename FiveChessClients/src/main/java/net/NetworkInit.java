package net;

import bean.Const;
import center.ChessFrame;
import Common.MessageUtils;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NetworkInit extends JPanel implements ActionListener {
    private JFrame frame;
    private ChessFrame panel;

    public NetworkInit(ChessFrame panel, JFrame frame) {
        this.frame = frame;
        this.panel = panel;
        this.init();
    }

    private JLabel statusLabel;
    private JLabel MyLabel;
    private JLabel YouLabel;

    public JButton ok;
    public JButton cancel;
    public JTextField status;
    public JTextField userId;
    public JTextField userName;
    public JTextField you;
    public boolean serverFlag = false; // 是否能连接服务器

    public void init() {
        this.setLayout(null);
        ok = new JButton("连接");
        cancel = new JButton("离开");
        status = new JTextField("游戏状态");
        userId = new JTextField();
        userName = new JTextField();
        you = new JTextField();
        statusLabel = new JLabel("状态：");
        MyLabel = new JLabel("玩家：");
        YouLabel = new JLabel("对家：");
        statusLabel.setBounds(5, 20, 50, 35);
        status.setBounds(50, 20, 220, 35);
        status.setEditable(false);

        MyLabel.setBounds(5, 70, 50, 35);
        userName.setBounds(50, 70, 220, 35);

        YouLabel.setBounds(5, 120, 50, 35);
        you.setBounds(50, 120, 220, 35);
        you.setEditable(false);

        ok.setBounds(50, 180, 80, 35);
        cancel.setBounds(170, 180, 80, 35);
        this.add(statusLabel);
        this.add(MyLabel);
        this.add(YouLabel);
        this.add(ok);
        this.add(cancel);
        this.add(status);
        this.add(userId); // 注意userId只添加到容器中但不设置位置
        this.add(userName);
        this.add(you);
        ok.addActionListener(this);
        cancel.addActionListener(this);
        status.addActionListener(this);
    }

    public void initData() {
        if (serverFlag) {
            status.setText("连接成功"); // 这个状态是重新连接给客户端的提示
            ok.setEnabled(true);
            you.setText("");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            // status.setText("");
            // my.setText("");
            // you.setText("");
            frame.setVisible(false);
            if (serverFlag) {
                MessageUtils.gameOver(userId.getText());
            }
        } else if (e.getSource() == ok) {
            if (userName.getText().length() <= 0) {
                JOptionPane.showMessageDialog(frame, "请输入玩家名称");
            } else {
                ok.setEnabled(false);
                // 没连接服务器则进行初始化
                if (!serverFlag) {
                    status.setText("正在连接服务器");
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            if (MessageUtils.connection()) {
                                serverFlag = true;
                                status.setText("连接成功");
                                ok.setText("准备");
                                MessageUtils.setInitData(NetworkInit.this, panel, frame); // 传输数据
                            } else {
                                serverFlag = false;
                                ok.setText("连接");
                                status.setText("连接失败");
                            }
                            ok.setEnabled(true);
                        }
                    }).start();
                } else {
                    // 优先校验服务器返回的校验ID
                    String my = userId.getText().trim();
                    if (my == null || my.length() <= 0) {
                        status.setText("客户端数据异常, 请再次尝试准备");
                        ok.setEnabled(true);
                    } else {
                        // 初始化匹配数据
                        JSONObject requestMsg = new JSONObject();
                        requestMsg.put(Const.ID, Const.ID_STATUS_INIT);
                        requestMsg.put(Const.MY, userId.getText().trim());
                        requestMsg.put(Const.USER_NAME, userName.getText().trim());
                        MessageUtils.sendJson(requestMsg);
                        status.setText("正在初始化服务器数据");
                        ok.setEnabled(false);
                        panel.userId = userId.getText().trim();
                        panel.userName = userName.getText().trim();
                    }
                }
            }
        }
    }
}
