package bean;


import format.MessageFormat;

import java.net.Socket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoMing luom@3vjia.com
 * 用户数据
 * @since 2018-12-04 18:57
 */
public class UserData {
    private MessageFormat msgFormat; // 处理消息的对象
    private Socket socket; // 客户端套接字对象
    private Thread thread; // 处理消息的线程
    private String userId; // 登陆的用户(唯一性)
    private String userName; // 客户的名字(自己取的名称)
    private String status; // 状态(0：登陆; 1：准备; 2：对局开始; 3：观战)
    private String enemy; // 对家
    private String isFirst; // 是否为先手
    private List<Chess> chessBoard; // 棋盘
    private boolean isOver; // 对局是否结束

    // 玩家特色属性
    private double scoer; // 积分
    private int total; // 累计对局
    private int victory; // 胜利次数
    private int level; // 等级

    public UserData() {

    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean isOver) {
        this.isOver = isOver;
    }

    public MessageFormat getMsgFormat() {
        return msgFormat;
    }

    public void setMsgFormat(MessageFormat msgFormat) {
        this.msgFormat = msgFormat;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public String getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(String isFirst) {
        this.isFirst = isFirst;
    }

    public List<Chess> getChessBoard() {
        if (chessBoard == null) {
            chessBoard = new ArrayList();
        }
        return chessBoard;
    }

    public void setChessBoard(List<Chess> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public double getScoer() {
        return scoer;
    }

    public void setScoer(double scoer) {
        this.scoer = scoer;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getVictory() {
        return victory;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
