package Servlet;

import bean.UserData;
import Const.Const;
import Common.LogUtils;
import format.MessageFormat;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


/**
 * @author LuoMing luom@3vjia.com
 * @since 2018-12-04 21:30
 */
public class SuperServlet  extends Thread{
    private ServerSocket serverSocket;
    private Socket socket;
    private boolean start=false;
    private static volatile Map<String, UserData> client=new HashMap();
    public static void main(String[] argv){
        SuperServlet servlet=new SuperServlet();
        servlet.startService();
    }

    public SuperServlet(){
        try {
            serverSocket=new ServerSocket(Const.PORT);
        } catch (IOException e) {
            LogUtils.write("服务器启动异常"+e.getMessage());
        }
    }

    /**
     * 统一更新客户端接口
     * @param userId
     * @param newData
     */
    public synchronized static void updateClient(String userId,UserData newData){
        //保证数据安全可以先移除客户端在录入
        client.put(userId,newData);
    }

    /**
     * 统一获取客户端接口
     * @return
     */
    public synchronized static Map<String ,UserData> getClient(){
        return  client;
    }
    //启动方法
    public void startService(){
        //打开接收标志
        this.start=true;
        this.start();
        System.out.println("服务已启动！监听端口:"+Const.PORT);

    }

    //关闭的方法
    public  void  stopService(){
        this.start=false;
        //停止所有的客户端
        for(Map.Entry<String,UserData> itemclient:client.entrySet()){
            itemclient.getValue().getMsgFormat().close();
            itemclient.getValue().getThread().stop();
            System.out.println("踢出客户端:"+itemclient.getKey());
        }
        this.stop();
        System.out.println("服务已关闭");
    }

    @Override
    public void run() {
        while (start){
            try {
                //监听客户端的请求
                socket=serverSocket.accept();
                synchronized (socket){
                    String userId=Const.getId();
                    MessageFormat cs=new MessageFormat(socket);
                    Thread th=new Thread(cs);
                    UserData userData=new UserData();
                    userData.setUserId(userId);
                    userData.setMsgFormat(cs);
                    userData.setSocket(socket);
                    userData.setThread(th);
                    client.put(userId,userData);
                    //启动这个线程
                    th.start();
                    JSONObject responsemsg=new JSONObject();
                    responsemsg.put(Const.ID,Const.ID_STATUS_HANDSNAKE);
                    responsemsg.put(Const.MSG,userId);
                    cs.send(responsemsg);
                    System.out.println("一个客户端连接。。。在线数量："+client.size());
                }
            } catch (IOException e) {

                LogUtils.write("服务已启动或端口被占用!"+e.getMessage());
                e.printStackTrace();
                System.exit(0);
            }
        }
        super.run();
    }
}
