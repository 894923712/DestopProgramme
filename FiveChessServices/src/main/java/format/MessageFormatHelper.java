package format;

import Servlet.SuperServlet;
import bean.Chess;
import Const.Const;
import bean.UserData;
import net.sf.json.JSONObject;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author LuoMing luom@3vjia.com
 * 消息格式化帮助类
 * @since 2018-12-04 20:06
 */
public class MessageFormatHelper {
    public static List<String> ppList = new CopyOnWriteArrayList(); // 匹配用户集
    private static Timer timer = null; // 匹配线程
    private static JSONObject result=new JSONObject();
    private static JSONObject json=new JSONObject();
    /**
     * 初始化服务器数据
     * @param _json
     */
    public static void init(JSONObject _json){
        json=_json;
        result.clear();
    }

    /**
     * 初始化客户端
     * @return 结果
     */
    public static void InitClient(){
        result.put(Const.ID,Const.ID_STATUS_INIT);
        String userId=json.getString(Const.MY);
        String userName=json.getString(Const.USER_NAME);
    }
    /**
     * 匹配用户
     * @return 匹配结果
     */
    public synchronized static void matchUser(){
        String userId = json.getString(Const.MY);
        ppList.add(userId);
        if(timer == null){
            timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    synchronized (ppList) {
                        for(String userId : ppList){
                            if(matchUserTimer(userId)){
                                ppList.remove(userId);
                                // System.out.println("匹配用户数量：" + ppList.size());
                            }
                        }
                    }
                }
            }, 0, 100);
        }
    }

    /**
     * 匹配线程
     */
    public static boolean matchUserTimer(String userId){
        result.put(Const.ID, Const.ID_STATUS_PP);
        UserData ud = SuperServlet.getClient().get(userId);
        // 若该用户状态不为0则不初始化状态
        if("0".equals(ud.getStatus())){
            ud.setStatus("1"); // 注意设置状态为准备状态
            ud.setChessBoard(null); // 清空棋盘
        }
        // 优先获取该用户有没有匹配对手
        if(ud.getEnemy() != null){
            ud.setOver(false);
            SuperServlet.updateClient(userId, ud);
            result.put(Const.MSG, Const.PP_SUCCESS);
            result.put(Const.YOU, ud.getEnemy());
            result.put(Const.USER_NAME, //
                    SuperServlet.getClient().get(ud.getEnemy()).getUserName());
            result.put(Const.FIRST, ud.getIsFirst());
            ud.getMsgFormat().send(result);
            return true;
        } else {
            boolean matchFlag = false; // 是否匹配成功的标记
            // 如若没有匹配则进行玩家搜索
            for(Map.Entry<String, UserData> set : SuperServlet.getClient().entrySet()){
                UserData clientData = set.getValue();
                // 1为准备状态 并且不能为自己
                if("1".equals(clientData.getStatus()) && !userId.equals(clientData.getUserId())){
                    clientData.setEnemy(ud.getUserId()); // 设置对手为当前用户
                    clientData.setStatus("2"); // 更改对手用户状态
                    clientData.setIsFirst(String.valueOf((int)(Math.random() * 2))); // 随机先手

                    ud.setEnemy(clientData.getUserId()); // 设置我的对手名称
                    ud.setStatus("2"); // 设置我的状态
                    ud.setIsFirst(clientData.getIsFirst().equals("1") ? "0" : "1"); // 取对手的反落子

                    // 更新数据
                    SuperServlet.updateClient(userId, ud);
                    SuperServlet.updateClient(clientData.getUserId(), clientData);
                    // 返回匹配数据
                    result.put(Const.MSG, Const.PP_SUCCESS);  // 记录匹配结果
                    result.put(Const.USER_NAME, clientData.getUserName()); // 对手的中文名称
                    result.put(Const.YOU, clientData.getUserId()); // 记录匹配的对手
                    result.put(Const.FIRST, ud.getIsFirst()); // 设置先手方
                    matchFlag = true; // 标记匹配
                    ud.getMsgFormat().send(result);
                    return true;
                }
            }
            // 如果遍历完了还没有匹配
            if(!matchFlag){
                // result.put(Const.MSG, "服务器没有正在准备的玩家");
                // ud.getMsgFormat().send(result);
            }
        }
        return false;
    }
    /**
     * 绘制棋盘棋子
     */
    public static void putChess() {
        // 设置我方棋盘
        String userId = json.getString(Const.MY);
        // 获取坐标
        int x = json.getInt(Const.X);
        int y = json.getInt(Const.Y);
        String color = json.getString(Const.COLOR);

        // 更新我方棋盘
        UserData my = SuperServlet.getClient().get(userId);
        my.getChessBoard().add(new Chess(x, y, color));
        // 更新对方棋盘
        UserData you = SuperServlet.getClient().get(my.getEnemy());
        you.getChessBoard().add(new Chess(x, y, color));

        // 更新服务器数据
        SuperServlet.updateClient(userId, my);
        SuperServlet.updateClient(you.getUserId(), you);

        // 将棋子同步给对手
        if(!you.isOver()){
            json.put(Const.ID, Const.ID_STATUS_GET);
            you.getMsgFormat().send(json);
        } else {
            System.out.println("无将棋子同步给对手, 对方已结束游戏");
        }
    }
    /**
     * 游戏结束
     */
    public static void gameOver() {
        result.put(Const.ID, Const.ID_STATUS_OVER);
        String userId = json.getString(Const.MY);
        ppList.remove(userId); // 如果在匹配中则移除匹配队列
        UserData my = SuperServlet.getClient().get(userId);
        my.setOver(true);
        my.setStatus("0"); // 设置状态
        // result.put(Const.MSG, "退出");
        // result.put(Const.MY, my.getUserId());
        // my.getMsgFormat().send(result);
        SuperServlet.updateClient(userId, my); // 更新服务器数据
    }
    /**
     * 悔棋请求
     */
    public static void toBack(){
        String userId = json.getString(Const.MY);
        UserData my = SuperServlet.getClient().get(userId);

        result.put(Const.ID, Const.ID_STATUS_BACK);
        UserData you = SuperServlet.getClient().get(my.getEnemy());
        you.getMsgFormat().send(result);
    }
    /**
     * 悔棋结果
     */
    public static void toBackResult(){
        String userId = json.getString(Const.MY);
        UserData my = SuperServlet.getClient().get(userId);

        result.put(Const.ID, Const.ID_STATUS_BACK_RESULT);
        result.put(Const.MSG, json.getString(Const.MSG));
        UserData you = SuperServlet.getClient().get(my.getEnemy());
        you.getMsgFormat().send(result);

        // 更新服务器棋盘以备观战
        if("同意".equals(json.getString(Const.MSG))){
            my.getChessBoard().remove(my.getChessBoard().size() - 1);
            you.getChessBoard().remove(you.getChessBoard().size() - 1);
            SuperServlet.updateClient(my.getUserId(), my);
            SuperServlet.updateClient(you.getUserId(), you);
        }
    }

    /**
     * 认输
     */
    public static void fail() {
        result.put(Const.ID, Const.ID_STATUS_FAIL);
        String userId = json.getString(Const.MY);
        UserData my = SuperServlet.getClient().get(userId);

        // 获取对手名称
        UserData you = SuperServlet.getClient().get(my.getEnemy());
        // 防止对方也同时退出，加个判断
        if(you != null){
            result.put(Const.MSG, my.getUserName());
            you.getMsgFormat().send(result);
        }
        // 更新游戏数据
        my.setOver(true);
        you.setOver(true);
        SuperServlet.updateClient(my.getUserId(), my);
        SuperServlet.updateClient(you.getUserId(), you);
    }
    /**
     * 聊天消息处理
     */
    public static void chatMsg() {
        result.put(Const.ID, Const.ID_STATUS_MSG);
        String userId = json.getString(Const.MY);
        UserData my = SuperServlet.getClient().get(userId);
        // 获取对手名称
        UserData you = SuperServlet.getClient().get(my.getEnemy());
        if(you != null){
            result.put(Const.MSG, json.getString(Const.MSG));
            result.put(Const.MY, my.getUserName());
            you.getMsgFormat().send(result);
        }
    }
    /**
     * 游戏超时
     */
    public static void overTime() {
        String userId = json.getString(Const.MY);
        UserData my = SuperServlet.getClient().get(userId);
        UserData you = SuperServlet.getClient().get(my.getEnemy());
        if(you != null){
            result.put(Const.ID, Const.ID_STATUS_OVERTIME);
            you.getMsgFormat().send(result);
        }
        // 更新游戏数据
        my.setOver(true);
        you.setOver(true);
        SuperServlet.updateClient(my.getUserId(), my);
        SuperServlet.updateClient(you.getUserId(), you);
    }
}
