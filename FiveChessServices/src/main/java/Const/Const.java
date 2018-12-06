package Const;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author LuoMing luom@3vjia.com
 * 常量类
 * @since 2018-12-04 20:12
 */
public class Const {
    //监听端口号
    public static final int PORT=7001;

    public static final String ID="id";

    public static final String MSG="msg";
    public static final String ID_STATUS_ERROR="idError";
    //向服务器请求初始化
    public static final String ID_STATUS_INIT="初始化客户端";

    public static final String ID_STATUS_PP="匹配玩家";
    public static final String PP_SUCCESS="匹配成功";
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

    // 属于页面的专属数据
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static String getId() {
        return getSysDate() + "-" + UUID.randomUUID().toString();
    }

    public static String getSysDate(){
        return sdf.format(new Date());
    }
}
