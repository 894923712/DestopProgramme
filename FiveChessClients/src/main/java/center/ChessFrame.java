package center;

import Common.MessageUtils;
import Common.StringHelp;
import bean.Chess;
import bean.Const;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LuoMing luom@3vjia.com
 * 棋盘类
 * @since 2018-12-06 16:27
 */
public class ChessFrame extends JPanel {
    private int span = 35; // 棋盘格子宽度
    private int margin = 22;
    private final int DIAMETER = 35; // 直径
    private final int row = 15; // 棋盘行、列
    private final int col = 15;
    private int i = 0;
    private boolean isBlack = true;
    private boolean isPicture = true;// 是否用图片作为背景(图片是正常游戏背景，false为测试游戏背景)
    private ImageIcon img = new ImageIcon("src/images/board.jpg");
    private List<Chess> list = new LinkedList<Chess>();
    public boolean gameOver = true; // 默认结束游戏

    // 网络数据
    public boolean isNetworkPK = false;
    public boolean myChessColor = false; // 记录我方落子的颜色
    public boolean failFlag = false; // 认输标记
    public MessageQueuePanel MQPanel;
    public String userName = null; // 我的名称
    public String userId = null; // 我的ID

    public ChessFrame(MessageQueuePanel _panel){
        this.MQPanel = _panel;
        MQPanel.chessPanel = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (isNetworkPK) {
                    // 如果该我方落子
                    if (isBlack == myChessColor && !gameOver) {
                        int x = (e.getX() - margin + span / 2) / span; // 将鼠标按下的坐标转换成棋盘坐标
                        int y = (e.getY() - margin + span / 2) / span;
                        if(makeChess(x, y)){
                            // 将数据传给服务器
                            JSONObject json = new JSONObject();
                            json.put(Const.ID, Const.ID_STATUS_PUT);
                            json.put(Const.MY, userId);
                            json.put(Const.X, x);
                            json.put(Const.Y, y);
                            json.put(Const.COLOR, isBlack);
                            MessageUtils.sendJson(json);
                            MQPanel.waitTimer();
                        }
                    }
                }else {
                    // 不为网络对战，那就是人 人对战，自娱自乐
                    if (!gameOver) {
                        int x = (e.getX() - margin + span / 2) / span; // 将鼠标按下的坐标转换成棋盘坐标
                        int y = (e.getY() - margin + span / 2) / span;
                        makeChess(x, y);
                    }
                }
            }
        });
        // 鼠标划动变成小手的事件
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                int x = (e.getX() - margin + span / 2) / span; // 将鼠标按下的坐标转换成棋盘坐标
                int y = (e.getY() - margin + span / 2) / span;
                if (gameOver) {
                    return;
                }
                if (x < 0 || y < 0 || x >= row || y >= col) {
                    return;
                }
                if(isChess(list, x, y)){
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } else {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        }); // 监听鼠标移动事件
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // span = this.getHeight() / row; // 当窗口被拖动，动态刷新窗口
        Graphics2D g2 = (Graphics2D) g;
        if (isPicture) {
            g.drawImage(img.getImage(), 1, 0, null);
        } else {
            // 横线
            for (i = 0; i < row; ++i) {
                g2.drawLine(margin, i * span + margin, //
                        row * span - (margin + 4) / 2, i * span + margin);
                g2.drawString(String.valueOf(i), 4, i * span + margin + 3);
            }
            // 纵线
            for (i = 0; i < col; ++i) {
                g2.drawLine(i * span + margin, margin, //
                        i * span + margin, col * span - (margin + 4) / 2);
                g2.drawString(String.valueOf(i), i * span + margin, 15);
            }
        }
        RadialGradientPaint rgp = null;
        // 画棋子
        for (i = 0; i < list.size(); ++i) {
            Chess chess = list.get(i);
            int xPos = chess.getX() * span + margin; // 将真实坐标转换成网格坐标
            int yPos = chess.getY() * span + margin;
            g2.setColor(chess.getColors()); // 设置画笔颜色
            if (chess.getColors() == Color.BLACK) {
                rgp = new RadialGradientPaint(xPos - DIAMETER / 2 + 26, //
                        yPos - DIAMETER / 2 + 12, 20, new float[] { 0.0f, 1.0f }, //
                        new Color[] { Color.WHITE, Color.BLACK });
                g2.setPaint(rgp);
            } else {
                // x, y, 直径, 渐变度, 渐变色
                rgp = new RadialGradientPaint(xPos - DIAMETER / 2 + 25, //
                        yPos - DIAMETER / 2 - 30, 60, new float[] { 0f, 1f }, //
                        new Color[] { Color.BLACK, Color.WHITE });
                g2.setPaint(rgp);
            }
            // 去锯齿
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
            g2.fillOval(xPos - DIAMETER / 2, yPos - DIAMETER / 2, span - 1, span);
            // 画红色矩形
            g2.setColor(Color.RED);
            if (i == list.size() - 1)
                g2.draw3DRect(xPos - DIAMETER / 2 - 1, //
                        yPos - DIAMETER / 2 - 1, span, span + 1, true);
        }
    }
    /**
     * 落子
     * @param x
     * @param y
     * @return
     */
    public boolean makeChess(int x, int y) {
        // 游戏结束不能下
        if (gameOver) {
            JOptionPane.showMessageDialog(this, "游戏已经结束!");
            return false;
        }
        // 在棋盘外不能下
        if (x < 0 || y < 0 || x >= row || y >= col) {
            return false;
        }
        // 当前位置有棋不能下
        if (isChess(list, x, y)){
            return false;
        }

        // 添加棋子进棋盘
        list.add(new Chess(x, y, isBlack));
        this.repaint();
        // 判断胜利
        if (isWin(list, x, y, isBlack) || list.size() == (row * col) || failFlag) {
            gameOver = true;
            if(!StringHelp.IsEmptyOrNull(userId)){
                MessageUtils.gameOver(userId); // 游戏结束
            }
            // 平局判断
            if(list.size() == (row * col)){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(null, "双方平局");
                    }
                }).start();
            } else {
                final boolean _isBlack = isBlack;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(null,//
                                (_isBlack ? "黑棋" : "白棋") + "获得胜利");
                    }
                }).start();
            }
        }
        isBlack = !isBlack;
        return true;
    }
    // 判断胜利的方法
    private boolean isWin(List<Chess> list, int xPos, int yPos, boolean isBlack) {
        int chessCount = 1;
        final int max = 5; // 连子数
        int x = 0, y = 0;
        Color color = (isBlack ? Color.BLACK : Color.WHITE);
        // 当前位置向左
        for (x = xPos - 1; x >= 0; --x) {
            if (getChess(list, x, yPos, color) != null)
                chessCount++;
            else
                break;
        }
        // 当前位置向右
        for (x = xPos + 1; x <= row; ++x) {
            if (getChess(list, x, yPos, color) != null)
                chessCount++;
            else
                break;
        }
        if (chessCount >= max)
            return true;
        else
            chessCount = 1;

        // 当前位置向上
        for (y = yPos - 1; y >= 0; --y) {
            if (getChess(list, xPos, y, color) != null)
                chessCount++;
            else
                break;
        }
        // 当前位置向下
        for (y = yPos + 1; y <= col; ++y) {
            if (getChess(list, xPos, y, color) != null)
                chessCount++;
            else
                break;
        }
        if (chessCount >= max)
            return true;
        else
            chessCount = 1;

        // 左斜着向上
        for (x = xPos - 1, y = yPos - 1; x >= 0 && y >= 0; --x, --y) {
            if (getChess(list, x, y, color) != null)
                chessCount++;
            else
                break;
        }
        // 右斜着向下
        for (x = xPos + 1, y = yPos + 1; x <= row && y <= col; ++x, ++y) {
            if (getChess(list, x, y, color) != null)
                chessCount++;
            else
                break;
        }
        if (chessCount >= max)
            return true;
        else
            chessCount = 1;
        // 右斜着向上
        for (x = xPos + 1, y = yPos - 1; x <= row && y >= 0; ++x, --y) {
            if (getChess(list, x, y, color) != null)
                chessCount++;
            else
                break;
        }
        // 左斜着向下
        for (x = xPos - 1, y = yPos + 1; x >= 0 && y <= col; --x, ++y) {
            if (getChess(list, x, y, color) != null)
                chessCount++;
            else
                break;
        }
        if (chessCount >= max)
            return true;
        else
            chessCount = 1;
        return false;
    }
    // 得到所在位置的棋子
    private Chess getChess(List<Chess> list, int x, int y, Color color) {
        for (Chess c : list) {
            if (c.getX() == x //
                    && c.getY() == y //
                    && c.getColors() == color)
                return c;
        }
        return null;
    }
    // 判断当前位置是否已有棋子
    private boolean isChess(List<Chess> list, int x, int y) {
        for (Chess c : list) {
            if (c.getX() == x && c.getY() == y)
                return true;
        }
        return false;
    }
    /**
     * 初始化网络对战的相关数据
     * @param _isBlack
     */
    public void initNetworkData(boolean _isBlack) {
        this.isBlack = _isBlack; // 记录先手方
        this.myChessColor = _isBlack; // 静态变量记录先手
        this.isNetworkPK = true; // 标记为网络对战
        MQPanel.sendMsg(Const.SYSTEM_MSG, null, "对局开始-我方" + (_isBlack ? "执黑先手" : "执白后手"), Color.RED, false);
        this.refurbish();
        // 如果是先手则启用倒计时
        MQPanel.firstInitialized(); // 初始化基础参数(在最之前)
        MQPanel.startTimer(); // 启动倒计时任务
        if(_isBlack){
            MQPanel.notifyTimer();
        }
    }
    /**
     * 重新开始
     */
    public void refurbish() {
        // 这里要进行网络对战判断
        if (isNetworkPK && !gameOver) {
            JOptionPane.showMessageDialog(null, "请先结束网络对战");
        } else if (!gameOver) {
            if (JOptionPane.showConfirmDialog(this, "游戏正在进行，确定重新开始？", "温馨提示",
                    JOptionPane.YES_NO_OPTION) == 1) {
                return;
            }
        }
        list.clear();
        gameOver = false;
        isBlack = true;
        failFlag = false;
        Collections.synchronizedList(list); // 同步锁
        this.repaint();
    }

    /**
     * 悔棋校验
     */
    public void toBack() {
        if (gameOver) {
            JOptionPane.showMessageDialog(null, "游戏已结束或未开始!");
            return;
        }
        if(list.size() <= 0){
            JOptionPane.showMessageDialog(null, "棋盘为空");
            return;
        }
        // 如果是网络对战
        if(isNetworkPK){
            // 并且不为我方落子才进行悔棋
            if (isBlack != myChessColor && !gameOver) {
                // 发送悔棋信息
                JSONObject requestMsg = new JSONObject();
                requestMsg.put(Const.ID, Const.ID_STATUS_BACK);
                requestMsg.put(Const.MY, userId);
                MessageUtils.sendJson(requestMsg);
                MQPanel.sendMsg(Const.SYSTEM_MSG, null, "已向对方发送悔棋请求", Color.RED, false);
            } else {
                JOptionPane.showMessageDialog(null, "当前为您落子，无法悔棋");
            }
        } else {
            backChess();
        }
    }
    /**
     * 回退棋子
     */
    public void backChess(){
        int n = list.size();
        if (n > 0) {
            list.remove(n - 1);
            isBlack = !isBlack;
        }
        this.repaint();
    }


}
