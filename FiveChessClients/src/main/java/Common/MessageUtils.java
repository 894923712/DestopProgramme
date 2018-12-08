package Common;

import bean.Const;
import center.ChessFrame;
import net.NetworkInit;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageUtils {
    private static Socket socket = null;
    private static DataInputStream dis;
    private static DataOutputStream dos;

    private static NetworkInit networkInit;
    private static ChessFrame panel;
    private static JFrame frame;
    public static boolean startFlag = false;
    /**
     * 连接服务器
     */
    public static boolean connection() {
        try {
            if (socket == null) {
                socket = new Socket(Const.IP, Const.PORT);
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
            }
            return true;
        } catch (Exception er) {
            // JOptionPane.showMessageDialog(null, "连接服务器失败");
            // System.exit(0);
            return false;
        }
    }
    /**
     * 数据传送中心 外部传入
     */
    public static void sendJson(final JSONObject json) {
        // 以线程的方式发送数据(暂时不用)
        try {
            dos.writeUTF(json.toString());
            // System.out.println("client: out:" + json.toString());
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }
    /**
     * 信息拦截中心
     */
    public static void filterMsgCenter() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (startFlag) {
                        String responseMsg = dis.readUTF();
                        if (responseMsg != null) {
                            responseMsg = responseMsg.replaceAll("\"", "\\\"");
                        }
                        // System.out.println("client  in:" + responseMsg);
                        JSONObject json = JSONObject.fromObject(responseMsg);
                        MessageUtilsHelper.initialized(json, panel, networkInit, frame);
                        String id = String.valueOf(json.get(Const.ID));
                        // 初次与服务器交互
                        if (Const.ID_STATUS_HANDSNAKE.equals(id)) {
                            MessageUtilsHelper.initId();
                            // 初始化->匹配玩家
                        } else if (Const.ID_STATUS_INIT.equals(id)) {
                            MessageUtilsHelper.init();
                            // 匹配玩家->成功
                        } else if (Const.ID_STATUS_PP.equals(id)) {
                            MessageUtilsHelper.matchUserSuccess();
                            // 获取棋子
                        } else if (Const.ID_STATUS_GET.equals(id)) {
                            MessageUtilsHelper.getChess();
                            // 对局结束
                        } else if (Const.ID_STATUS_OVER.equals(id)) {
                            MessageUtilsHelper.gameOver();
                            // 消息传送拦截
                        } else if (Const.ID_STATUS_MSG.equals(id)) {
                            MessageUtilsHelper.filterMsg();
                            // 处理悔棋请求
                        } else if (Const.ID_STATUS_BACK.equals(id)) {
                            MessageUtilsHelper.toBack();
                            // 处理悔棋结果
                        } else if (Const.ID_STATUS_BACK_RESULT.equals(id)) {
                            MessageUtilsHelper.backChess();
                            // 认输
                        } else if (Const.ID_STATUS_FAIL.equals(id)) {
                            MessageUtilsHelper.fail();
                            // 游戏超时
                        } else if (Const.ID_STATUS_OVERTIME.equals(id)) {
                            MessageUtilsHelper.overTime();
                        } else {
                            System.out.println("Client: 未匹配到分支, responseMsg = " + responseMsg);
                        }
                    }
                } catch (Exception er) {
                    // 与服务器断开连接进行重连操作
                    socket = null;
//					int restart = 0;
//					int max = 3;
//					while (restart < max) {
//						String restartMsg = "与服务器断开连接，正在重新连接(" + (restart + 1) + "/" + max + ")";
//						System.out.println(restartMsg);
//						restart++;
//						try {
//							Thread.sleep(2 * 1000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
                    startFlag = false;
                    networkInit.serverFlag = false;
                    close();
//					JOptionPane.showMessageDialog(null, "重新连接失败, 游戏结束");
                    er.printStackTrace();
                }
            }
        }).start();
    }
    // 退出程序调用关闭连接
    public static void close() {
        try {
            if (dos != null) {
                dos.close();
                dos = null;
            }
            if (dis != null) {
                dis.close();
                dis = null;
            }
            if (socket != null) {
                socket.close();
                socket = null;
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
    }
    /**
     * 传递初始化后处理各handler的参数
     * @param _networkInit
     * @param _panel
     * @param _frame
     */
    public static void setInitData(NetworkInit _networkInit, ChessFrame _panel, JFrame _frame) {
        networkInit = _networkInit;
        panel = _panel;
        frame = _frame;
        // 启动信息拦截中心
        startFlag = true;
        filterMsgCenter();
    }
    /**
     * 游戏结束
     * @param userId
     */
    public static void gameOver(String userId) {
        // 向服务器传送比赛结束信息
        final JSONObject requestMsg = new JSONObject();
        requestMsg.put(Const.ID, Const.ID_STATUS_OVER);
        requestMsg.put(Const.MY, userId);
        MessageUtils.sendJson(requestMsg);
    }

}
