package Common;

import bean.Const;
import center.ChessFrame;
import net.NetworkInit;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;

public class MessageUtilsHelper {
    private static JSONObject result = new JSONObject(); // 返回的结果集
    private static JSONObject json = new JSONObject(); // 存入的结果集
    private static NetworkInit networkInit;
    private static ChessFrame panel;
    private static JFrame frame;
    /**
     * 初始化服务器数据
     * @param _json
     */
    public static void initialized(JSONObject _json, ChessFrame _panel,//
                                   NetworkInit _networkInit, JFrame _frame) {
        json = _json;
        panel = _panel;
        networkInit = _networkInit;
        frame = _frame;
        result.clear();
    }

    /**
     * 处理服务器传回来的唯一ID
     */
    public static void initId(){
        String userId = json.getString(Const.MSG);
        networkInit.userId.setText(userId);
    }

    /**
     * 服务器返回初始化的游戏数据
     * 客户端发起匹配玩家请求
     */
    public static void init() {
        // 处理发送的数据
        networkInit.status.setText("正在匹配玩家");
        final JSONObject requestMsg = new JSONObject();
        requestMsg.put(Const.ID, Const.ID_STATUS_PP);
        requestMsg.put(Const.MY, networkInit.userId.getText().trim());
        MessageUtils.sendJson(requestMsg);
    }

    /**
     * 匹配成功
     */
    public static void matchUserSuccess() {
        String ppStatus = json.getString(Const.MSG);
        if (Const.PP_SUCCESS.equals(ppStatus)) {
            networkInit.status.setText("匹配成功，对局即将开始");
            // networkInit.you.setText(json.getString(Const.YOU));
            networkInit.you.setText(json.getString(Const.USER_NAME));
            try {
                Thread.sleep(1 * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            panel.initNetworkData(json.getString(Const.FIRST).equals("1") ? true : false); // 初始化棋盘数据
            frame.setVisible(false);
            networkInit.ok.setEnabled(true);
            String my = networkInit.userName.getText().trim();
            String you = networkInit.you.getText().trim();
            panel.MQPanel.pk.setText(my + " PK " + you);
        }
    }

    /**
     * 处理服务器返回的棋子并存入棋盘
     */
    public static void getChess() {
        int x = json.getInt(Const.X);
        int y = json.getInt(Const.Y);
        panel.makeChess(x, y);
        panel.MQPanel.notifyTimer();
    }

    /**
     * 游戏结束
     */
    public static void gameOver() {
        String resultMsg = json.getString(Const.MSG);
        if ("退出".equals(resultMsg)) {
            // System.out.println("正常从服务器退出");
            // TODO 这里做数据的初始化工作
            panel.MQPanel.pk.setText("PVP状态");
            panel.MQPanel.stopTimer();
        }
    }

    /**
     * 信息处理
     */
    public static void filterMsg() {
        String msg = json.getString(Const.MSG);
        String userName = json.getString(Const.MY);
        panel.MQPanel.sendMsg(userName, null, msg, Color.BLUE, false);
    }

    /**
     * 处理悔棋请求
     */
    public static void toBack(){
        Object[] options ={ "同意", "拒绝" };
        int result = JOptionPane.showOptionDialog(null, "对方请求悔棋", "系统提示", //
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        // result: 同意:0; 拒绝:1;
        JSONObject requestMsg = new JSONObject();
        requestMsg.put(Const.ID, Const.ID_STATUS_BACK_RESULT);
        requestMsg.put(Const.MY, panel.userId);
        requestMsg.put(Const.MSG, options[result]);
        MessageUtils.sendJson(requestMsg);
        // 我方也要回退对方的棋子
        if(result == 0){
            panel.MQPanel.waitTimer();
            panel.backChess();
        }
    }

    /**
     * 处理悔棋结果
     */
    public static void backChess(){
        String result = json.getString(Const.MSG);
        if("同意".equals(result)){
            panel.MQPanel.notifyTimer();
            panel.backChess();
        }
        JOptionPane.showMessageDialog(null, "对方" + result + "悔棋");

    }

    /**
     * 对家认输
     */
    public static void fail() {
        panel.gameOver = true; // 设置游戏结束
        panel.MQPanel.stopTimer();
        JOptionPane.showMessageDialog(null, "对方认输");
    }

    /**
     * 游戏超时
     */
    public static void overTime() {
        panel.gameOver = true;
        panel.MQPanel.stopTimer();
        JOptionPane.showMessageDialog(null, "对方超时，我方获得胜利");
    }
}
