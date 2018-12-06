package format;

import Common.LogUtils;
import Const.Const;
import bean.UserData;
import Servlet.SuperServlet;
import net.sf.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Map;


/**
 * @author LuoMing luom@3vjia.com
 * 处理消息类
 * @since 2018-12-04 19:00
 */
public class MessageFormat implements Runnable{
    public volatile  boolean clientStart=true;
    private DataInputStream dis;
    private DataOutputStream dos;
    public MessageFormat(Socket socket){
        try {
            dis=new DataInputStream(socket.getInputStream());
            dos=new DataOutputStream(socket.getOutputStream());
        }catch (Exception ex){
            LogUtils.write("MessageFormat类初始化异常"+ex.getMessage());
        }
    }
    @Override
    public void run() {
        while(clientStart){
            try {
                String ClientMsg=dis.readUTF();
                System.out.println("service  do in :"+ClientMsg);
                addInfo(ClientMsg);
            }catch (Exception ex){
                //如果无法获取客户端的消息
                for(Map.Entry<String, UserData> set : SuperServlet.getClient().entrySet()){
                    String userId = set.getKey();
                    UserData ud = set.getValue();
                    // 匹配退出的客户端进程
                    if(this == ud.getMsgFormat()){
                        // 给对手发送退出信息
                        JSONObject responseMsg = new JSONObject();
                        responseMsg.put(Const.ID, Const.ID_STATUS_FAIL);
                        UserData clientData = SuperServlet.getClient().get(ud.getEnemy());
                        if(clientData != null && !clientData.isOver()){
                            clientData.getMsgFormat().send(responseMsg);
                        }
                        MessageFormatHelper.ppList.remove(ud.getUserId());
                        System.out.println("退出的客户端为："+ ud.getUserName() + " --> " + ud.getUserId());
                        SuperServlet.getClient().remove(userId); // 服务器移除这个用户
                        System.out.println("在线数量：" + SuperServlet.getClient().size());
                        // ud.getThread().interrupt(); // 等待线程关闭
                        close(); // 关闭相应的流
                        clientStart = false; // 预先停止 while循环
                        ud.getThread().stop(); // 等待线程关闭
                    }

                }
                //LogUtils.write("无法获取客户端的消息"+ex.getMessage());
            }
        }
    }

    /**
     * 发送消息给客户端
     * @param msg
     */
    public void send(final JSONObject msg){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    if(msg!=null && dos!=null){
                        System.out.println("service do out:"+msg);
                        dos.writeUTF(msg.toString());
                        dos.flush();
                    }
                }catch (Exception ex){
                    LogUtils.write("发送消息给客户端异常"+ex.getMessage());
                }

            }
        }).start();

    }
    /**
     * 给指定的客户端发送信息
     * @param userId 客户端
     * @param msg 信息
     */
    public void send(String userId,JSONObject msg){
        UserData ud = SuperServlet.getClient().get(userId);
        if(ud != null){
            ud.getMsgFormat().send(msg);
        } else {
            System.out.println("服务器无法获取发送对象, userId:" + userId);
        }
    }

    /**
     * 关闭读写流
     */
    public void close(){
        try {
            if(dis!=null){
                dis.close();
                dis=null;
            }
            if(dos!=null){
                dos.close();
                dos=null;
            }
        }catch (Exception ex){
            LogUtils.write("关闭读写流"+ex.getMessage());
        }

    }

    /**
     * 消息集散中心
     * @param msg
     */
    public void addInfo(String msg){
        JSONObject json=JSONObject.fromObject(msg.replaceAll("\"","\\\""));
        MessageFormatHelper.init(json);
        String id = json.get(Const.ID).toString();
        switch (id) {
            // 初始化
            case Const.ID_STATUS_INIT:
                MessageFormatHelper.InitClient();
                break;
            // 匹配玩家
            case Const.ID_STATUS_PP:
                MessageFormatHelper.matchUser();
                break;
            // 落子
            case Const.ID_STATUS_PUT:
                MessageFormatHelper.putChess();
                break;
            // 获取棋子
//		case Const.ID_STATUS_GET:
//			MessageFormatHelper.getChess();
//			break;
            // 对局结束
            case Const.ID_STATUS_OVER:
                MessageFormatHelper.gameOver();
                break;
            // 悔棋请求
            case Const.ID_STATUS_BACK:
                MessageFormatHelper.toBack();
                break;
            // 悔棋请求的结果
            case Const.ID_STATUS_BACK_RESULT:
                MessageFormatHelper.toBackResult();
                break;
            // 认输
            case Const.ID_STATUS_FAIL:
                MessageFormatHelper.fail();
                break;
            // 聊天消息
            case Const.ID_STATUS_MSG:
                MessageFormatHelper.chatMsg();
                break;
            // 游戏超时
            case Const.ID_STATUS_OVERTIME:
                MessageFormatHelper.overTime();
                break;
            default:
                System.out.println("server: 未匹配到分支; id:" + id);
                break;
        }
    }

}
