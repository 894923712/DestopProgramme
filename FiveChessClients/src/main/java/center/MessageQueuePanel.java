package center;

import Common.MessageUtils;
import bean.Const;
import net.sf.json.JSONObject;
import task.ClockTask;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author LuoMing luom@3vjia.com
 * 消息队列
 * @since 2018-12-06 16:36
 */
public class MessageQueuePanel extends JPanel implements KeyListener {
    public JTextField pk,countdownText,totalTimeText;
    public JScrollPane consoleScroll,messageScroll;
    public JLabel countdownLabel,totalTimeLabel;
    public JButton send;
    public JTextArea message;
    private ImageIcon bgImg = new ImageIcon("src/images/t1.jpg");
    //显示消息的控制台
    public MyJTextPane console;
    public ChessFrame chessPanel; // 棋盘容器
    // 两线程和两 Runnable 类
    public ClockTask timerCountdown;
    public ClockTask timertotalTime;

    public long countdownTime = 0; // 步长
    public long totalTimes = 0; // 时长

    // 动态更改的步长和时长
    public long countdownDynamic = 0;
    public long totalTimesDynamic = 0;

    // 监控倒计时
    public Thread threadMonitor = null;
    public boolean countdownFlag = false; // 读秒标记
    public MessageQueuePanel(){
        this.setLayout(null);
        pk=new JTextField("PVP状态");
        //读秒
        countdownText=new JTextField();
        //单时间
        totalTimeText=new JTextField();
        console=new MyJTextPane();
        consoleScroll=new JScrollPane(console);
        message=new JTextArea();
        messageScroll=new JScrollPane(message);
        send=new JButton("发送");
        countdownLabel=new JLabel("步长：");
        totalTimeLabel=new JLabel("单时长");
        pk.setBounds(0, 10, 230, 35);
        pk.setFont(new Font("楷体", Font.BOLD, 16));
        pk.setEditable(false);
        pk.setHorizontalAlignment(JTextField.CENTER);
        pk.setForeground(Color.GREEN);
        pk.setFocusable(false);
        countdownLabel.setBounds(17, 50, 85, 35);
        countdownLabel.setFont(new Font("楷体", Font.BOLD, 16));

        countdownText.setBounds(60, 50, 170, 35);
        countdownText.setFont(new Font("楷体", Font.BOLD, 16));
        countdownText.setHorizontalAlignment(JTextField.CENTER);
        countdownText.setEditable(false);
        countdownText.setFocusable(false);

        totalTimeLabel.setBounds(0, 85, 85, 35);
        totalTimeLabel.setFont(new Font("楷体", Font.BOLD, 16));

        totalTimeText.setBounds(60, 85, 170, 35);
        totalTimeText.setFont(new Font("楷体", Font.BOLD, 16));
        totalTimeText.setHorizontalAlignment(JTextField.CENTER);
        totalTimeText.setEditable(false);
        totalTimeText.setFocusable(false);
        totalTimeText.setOpaque(false);

        console.setEditable(false);
        console.setFont(new Font("楷体", Font.BOLD, 16));
        console.setFocusable(false);
        console.setOpaque(false);
        consoleScroll.setBounds(0, 120, 230, 380);
        consoleScroll.setOpaque(false);

        message.setFont(new Font("楷体", Font.BOLD, 14));
        message.setFocusable(true);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        messageScroll.setBounds(0, 500, 230, 35);

        this.add(pk);
        this.add(countdownText);
        this.add(totalTimeText);
        this.add(consoleScroll);
        this.add(messageScroll);
        this.add(send);
        this.add(countdownLabel);
        this.add(totalTimeLabel);
        message.addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImg.getImage(), 0, 0, getSize().width, getSize().height, this);// 图片会自动缩放
    }

    /**
     * 统一发送信息方法
     *
     * @param userName
     *            消息传送的中文名
     * @param userId
     *            消息发送人
     * @param msg
     *            消息内容
     * @param color
     *            字体颜色{系统消息：红色; 我的消息：绿色; 对方消息：蓝色}
     * @param isChatMsg
     *            {false: 不需要发送给服务器; true: 需要发送给服务器}
     */
    public void sendMsg(String userName, String userId, String msg, Color color, boolean isChatMsg) {

        try {
            StringBuffer formatMsg = new StringBuffer();
            formatMsg.append("[" + userName + "] " + Const.getSysDate());
            formatMsg.append("\r\n");
            console.setText(formatMsg.toString(), null, null, Color.BLACK);
            formatMsg = new StringBuffer();
            formatMsg.append(msg.trim());
            formatMsg.append("\r\n");
            console.setText(" " + formatMsg.toString(), null, null, color);
            // 当滚动条不在最底部的时候 发送信息可以直接将滚动条拉到最底部(方便看信息)
            console.selectAll();
            // 不是系统消息才传给服务器
            if (isChatMsg) {
                JSONObject requestMsg = new JSONObject();
                requestMsg.put(Const.ID, Const.ID_STATUS_MSG);
                requestMsg.put(Const.MY, userId);
                requestMsg.put(Const.MSG, msg);
                MessageUtils.sendJson(requestMsg);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }


    }
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            String sendMsg = message.getText().trim();
            if (sendMsg.length() > 0) {
                if (chessPanel != null //
                        && chessPanel.isNetworkPK //
                        && !chessPanel.gameOver) {

                    sendMsg(chessPanel.userName, chessPanel.userId, //
                            message.getText().trim(), Color.GREEN, true);
                    message.setText("");
                } else {
                    String msg = message.getText();
                    message.setText(msg.substring(0, msg.length() - 1));
                    JOptionPane.showMessageDialog(null, "不是网络对战无法发送信息");
                }
            } else {
                message.setText("");
                JOptionPane.showMessageDialog(null, "不能发送空信息");
            }
        }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
    /**
     * 开始任务
     */
    public void startTimer() {
        // monitor不参与暂时、启动行列
        threadMonitor = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    synchronized (this) {
                        if (timerCountdown.currTime() <= 0) {
                            // 游戏超时结束
                            JSONObject requestMsg = new JSONObject();
                            requestMsg.put(Const.ID, Const.ID_STATUS_OVERTIME);
                            requestMsg.put(Const.MY, chessPanel.userId);
                            MessageUtils.sendJson(requestMsg);
                            chessPanel.gameOver = true;
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    JOptionPane.showMessageDialog(null, "我方超时判输");
                                }
                            }).start();
                            stopTimer();
                        }
                        if (!countdownFlag && timertotalTime.currTime() <= 0) {
                            if (timerCountdown.currTime() / 1000 > 60) {
                                countdownDynamic = 1 * 1 * 60 * 1000;
                                timerCountdown.setArgs(countdownDynamic, countdownText);
                            }
                            countdownFlag = true;
                        }
                    }
                }
            }
        });
        // 启动两个倒计时任务
        timerCountdown.start();
        timertotalTime.start();
        // 启动计算管理的任务
        threadMonitor.start();
    }
    /**
     * 等待
     */
    public void waitTimer() {
        // 线程暂停
        timerCountdown.setSuspend(true);
        timertotalTime.setSuspend(true);
        // 若局时已耗尽则步长只有一分钟且不重新累计(1 * 1 * 60 * 1000)
        if (countdownFlag) {
            String[] str = countdownText.getText().split(":");
            int m = Integer.parseInt(str[1]);
            int s = Integer.parseInt(str[2]);
            countdownDynamic = m * 60 * 1000 + s * 1000;
        } else {
            // 正常情况享受三分钟倒计时
            countdownDynamic = countdownTime;
        }
        String[] str = totalTimeText.getText().split(":");
        int m = Integer.parseInt(str[1]);
        int s = Integer.parseInt(str[2]);
        long time = m * 60 * 1000 + s * 1000;
        totalTimesDynamic = time;
        countdownText.setText(timerCountdown.format(countdownDynamic));
        totalTimeText.setText(timertotalTime.format(totalTimesDynamic));
    }

    /**
     * 取消等待
     */
    public void notifyTimer() {
        // 重新设置倒计时数据
        timerCountdown.setArgs(countdownDynamic, countdownText);
        timertotalTime.setArgs(totalTimesDynamic, totalTimeText);
        timerCountdown.setSuspend(false);
        timertotalTime.setSuspend(false);
    }
    /**
     * 停止
     */
    @SuppressWarnings("deprecation")
    public void stopTimer() {
        if (timerCountdown != null) {
            timerCountdown.stop();
            timerCountdown = null;
        }
        if (timertotalTime != null) {
            timertotalTime.stop();
            timertotalTime = null;
        }
        if (threadMonitor != null) {
            threadMonitor.stop();
            threadMonitor = null;
        }

    }

    /**
     * 初始化相关数据
     */
    public void firstInitialized() {
        stopTimer();
        if(timerCountdown == null) {
            timerCountdown = new ClockTask();
        }
        if(timertotalTime == null) {
            timertotalTime = new ClockTask();
        }
        // 游戏时长初始化
        countdownTime = 1 * 3 * 60 * 1000; // 步长
        totalTimes = 1 * 20 * 60 * 1000; // 时长

        // 动态更改的步长和时长
        countdownDynamic = countdownTime;
        totalTimesDynamic = totalTimes;

        // 保存相关数据进定时任务
        timerCountdown.setArgs(countdownDynamic, countdownText);
        timertotalTime.setArgs(totalTimesDynamic, totalTimeText);

        // 文本框内容初始化
        countdownText.setText(timerCountdown.format(countdownDynamic));
        totalTimeText.setText(timertotalTime.format(totalTimes));
    }

}
