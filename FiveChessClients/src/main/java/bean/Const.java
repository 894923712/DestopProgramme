package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LuoMing luom@3vjia.com
 * 常量类
 * @since 2018-12-06 14:22
 */
public class Const {
    //请求的服务器连接
    public static final String IP="localhost";
    public static final int PORT=7001;
    // 基础数据(key)
    public static final String ID = "id";
    public static final String MSG = "msg";

    // 基础数据(value)
    public static final String ID_STATUS_ERROR = "idError"; // 错误信息
    public static final String ID_STATUS_INIT = "初始化客户端"; // 向服务器请求初始化
    public static final String ID_STATUS_PP = "匹配玩家";
    public static final String PP_SUCCESS = "匹配成功";
    public static final String ID_STATUS_PUT = "传送落子位置";
    public static final String ID_STATUS_GET = "获取落子位置";
    public static final String ID_STATUS_OVER = "对局结束";
    public static final String ID_STATUS_MSG = "聊天消息";
    public static final String ID_STATUS_BACK = "请求悔棋";
    public static final String ID_STATUS_FAIL = "认输";
    public static final String ID_STATUS_HANDSNAKE = "初次握手";
    public static final String ID_STATUS_BACK_RESULT = "请求悔棋结果";
    public static final String ID_STATUS_OVERTIME = "游戏超时";
    public static final String SIZE = "棋盘长度";
    public static final String EXISTS = "该用户名已存在系统中";
    public static final String USER_NAME = "userName";
    public static final String INIT_SUCCESS = "初始化成功";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String STATUS = "status"; // 当前棋子的状态
    public static final String COLOR = "落子颜色";
    public static final String SYSTEM_MSG = "系统消息";


    // key - value
    public static final String MY = "my"; // 玩家
    public static final String YOU = "you"; // 对家
    public static final String FIRST = "先手方"; // 1：先手; 0：后手

    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    public static String getSysDate(){
        return sdf.format(new Date());
    }
}
