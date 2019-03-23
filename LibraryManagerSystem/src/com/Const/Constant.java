package com.Const;

public class Constant {
    public final static String DRIVER="com.mysql.jdbc.Driver";
    public final static String URL="jdbc:mysql://192.168.42.130:3306/library?characterEncoding=utf8&useSSL=false";
    public final static String USERNAME="root";
    public final static String PASSWORD="123456";   
    public final static String[] adminContent=new String[]{"请选择身份","管理员","普通用户"};
    public final static String[] sexContent=new String[]{"请选择性别","男","女"};
    public final static String[] publisher=new String[]{"北京出版社","天津出版社","上海出版社","中国人民出版社","人民邮电出版社"};
    public final static String[] bookinfocolumnNames=new String[]{"图书编号","图书类别","图书名称","作者","译者","出版社","出版日期","价格"};
    public final static String[] operatorcolumnNames=new String[]{"编号","用户名","性别","年龄","证件号","修改日期","电话号码","是否为管理员","当前密码"};
    public final static String[] booktypelumnNames=new String[]{"类别编号","类别名称","可借日期","罚款(迟还一天罚款数)"};
    public final static String[] identityType=new String[]{"请选择证件类型","工作证","身份证","老年证","学生证"};
    public final static String[] readerInfocolumnNames=new String[]{"姓名","性别","年龄","证件号码","会员证件有效日期","最大借书量","电话号码","押金","证件类型","职业","办证日期"};
    public final static String[] returnboxContent=new String[]{"请选择借出归还","借出","归还"};
    public final static String[] borrowDetailcolumnNames=new String[]{"图书编号","图书名称","读者名称","是否归还","借出日期","归还日期","操作员名称"};  
    public final static String[] returnDetailcolumnNames=new String[]{"图书编号","图书名称","读者名称","是否归还","归还日期","本书罚款","总罚款","借出日期","操作员名称"};
    public final static String[] searchDetailcolumnNames=new String[]{"图书编号","图书名称","图书类别","读者名称","读者编号","是否归还","借出日期","归还日期","操作员名称"};
    public final static String[] bookOrdercolumnNames=new String[]{"图书编号","采购日期","采购数量","操作员","是否验收","书籍折扣","验收员","验收日期"};
    public final static String[] isCheckOrderBookContent=new String[]{"已验收","未验收"};
}
