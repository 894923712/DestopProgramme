package com.Const;

/**
 * 这个类是用来提供sql语句的
 * 
 * @author LMingX0103380
 * 
 */
public class SQLConst {
	/**
	 * 图书信息添加
	 */
	public static final String INSERT_BOOK_INFO = "insert into book_info(ISBN ,typeId ,bookname,writer,translator ,publisher ,publishdate ,price )"
			+ "values(?,?,?,?,?,?,?,?)";
	/**
	 * 图书信息修改
	 */
	public static final String UPDATE_BOOK_INFO = "update book_info set typeId=? ,bookname=? ,writer=? ,translator=?,publisher=?,publishdate=?,price=? where ISBN=?";
	/**
	 * 批量删除图书信息
	 */
	public static final String DELETE_BOOK_INFO = "delete from book_info where ISBN = ?";
	/**
	 * 查询所有图书信息
	 */
	public static final String SELECT_BOOK_INFOALL = "select ISBN,typename ,bookname ,writer,translator,publisher ,publishdate ,price "
			+ "from book_info a inner join book_type b on a.typeid=b.typeid";
	/**
	 * 查询图书信息是否已存在
	 */
	public static final String SELECT_BOOK_INFO_IS_EXIST = "select * from book_info where ISBN=?";
	/**
	 * 查询图书类别编号
	 */
	public static final String SELECT_BOOKTYPEID = "select typeid from book_type where typename =?";

	/**
	 * 查询图书类别是否存在
	 */
	public static final String SELECT_BOOKTYPE_IS_EXIST = "select * from book_type where typename =? and days=?";
	/**
	 * 查询所有图书类别信息
	 */
	public static final String SELECT_BOOK_TYPE_ALL = "select * from book_type";

	/**
	 * 批量删除图书类型
	 */
	public static final String DELETEBOOKTYPEBYTYPEIDS = "delete from book_type where typeid=?";

	/**
	 * 更新图书类型
	 */
	public static final String UPDATE_BOOK_TYPE_BY_TYPEID = "update book_type set typename=?,days=?,fk=? where typeid=?";
	/**
	 * 查询管理员用户名和密码
	 */
	public static final String SELECT_OPERATOR = "select name,password from operator where name=? and password=?";
	/**
	 * 获取图书类别名称
	 */
	public static final String SELECT_BOOKTYPE_NAME = "select distinct typename from book_type";
	/**
	 * 添加图书类别
	 */
	public static final String INSERT_BOOKTYPE = "insert into book_type(typename ,days,fk)values(?,?,?)";
	/**
	 * 修改指定的用户名和密码
	 */
	public static final String UPDATEOPERATORPASSWORD = "update operator set password=? where name=?";
	/**
	 * 添加用户
	 */
	public static final String INSERTOPERATOR = "insert into operator(name,sex,age,identitycard,workdate,tel,admin,password) values(?,?,?,?,?,?,?,?)";
	/**
	 * 修改用户
	 */
	public static final String UPDATEOPERATOR = "update operator set name=? , sex=? , age=? ,identitycard=? , workdate=? , tel=? , admin=? , password=? where id=?";
	/**
	 * 查询所有的用户信息
	 */
	public static final String SELECTOPERATORALL = "select * from operator";
	/**
	 * 根据ID查询指定的用户信息
	 */
	public static final String SELECTOPERATORBYID = "select * from operator where id=?";
	/**
	 * 批量删除用户信息
	 */
	public static final String DELETEOPERATORALL = "delete from operator where id = ?";
	/**
	 * 查询用户是否存在
	 */
	public static final String SELECT_OPERATOR_IS_EXIST = "select * from operator where name = ? and sex=? and age=? and identitycard=?";
	/**
	 * 查询用户是否为管理员
	 */
	public static final String SELECT_OPERATOR_IS_ADMIN_OR_COMMEN = "select admin from operator where name = ? and password=?";
	/**
	 * 根据当前登录的用户名和密码查询当前登陆用户的ID
	 */
	public static final String SELECT_OPERATORID_BY_NAME_AND_PASSWORD = "select id from operator where name = ? and password=?";

	/**
	 * 插入读者信息
	 */
	public static final String INSERT_READER_INFO = "insert into  reader_info (readerid,name,sex,age,job,zj,identityCard,maxnum ,date ,tel ,keepmoney, bztime ) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	/**
	 * 查询所有的读者信息
	 */
	public static final String SELECT_READER_INFO_ALL = "select readerid,name,sex,age,job,zj,identityCard,maxnum ,date ,tel ,keepmoney, bztime  from reader_info";
	/**
	 * 批量删除读者信息
	 */
	public static final String DELETE_READER_INFO_BATCH = "delete  from reader_info where readerid=?";
	/**
	 * 修改读者信息
	 */
	public static final String UPDATE_READER_INFO = "update reader_info set name=? where readerid=?";
	/**
	 * 判断读者是否已存在
	 */
	public static final String IS_EXIST_READER = "select * from reader_info where readerid=?";
	/**
	 * 获取图书借出信息
	 */
	public static final String SELECT_BOOK_BORROW_ALL="select b.ISBN,b.bookname,r.name,a.isback,a.borrowDate,a.backDate,o.name as operatorname " +
			"from book_borrow a inner join book_info b on a.bookISBN=b.ISBN inner join reader_info r on a.readISBN =r.readerid inner join operator o on a.operatorId =o.id where a.isback=0 and a.readISBN=?";
	
	/**
	 * 插入图书借出信息
	 */
	public static final String INSERT_BOOK_BORROW="insert into book_borrow (bookISBN ,operatorId ,readISBN ,isback ,borrowDate) values(?,?,?,?,?)";
	
	/**
	 * 根据借出图书的编号和读者编号，批量删除借出图书的数据
	 */
	public static final String DELETE_BOOK_BORROW="delete from book_borrow where bookISBN =? and readISBN =?";
	
	/**
	 * 获取图书归还信息
	 */
	public static final String SELECT_BOOK_RETURN_ALL="select a1.bookISBN,a1.bookname,a1.name,a1.isback,a1.backDate,a1.fk," +
			"(select sum(fk) from " +
			"(select a.bookISBN,b.bookname ,r.name,a.isback,a.backDate," +
			"(select case when greatest(datediff(a.backDate,a.borrowDate),t.days)=t.days then 0" +
			" else  ((datediff(a.backDate,a.borrowDate) -t.days)*fk)/10 end as fk " +
			"from book_type t where t.typeid =b.typeId ) as fk," +
			"a.borrowDate,(select name from operator where id=a.operatorId ) as operatorname " +
			"from book_borrow a inner join book_info b on a.bookISBN=b.ISBN inner join reader_info " +
			"r on r.readerid =a.readISBN where a.isback=1 and a.readISBN=?) a2" +
			" where a1.backDate>=a2.backDate) as amountfk,a1.borrowDate,a1.operatorname " +
			"from (select a.bookISBN,b.bookname ,r.name,a.isback,a.backDate," +
			"(select case when greatest(datediff(a.backDate,a.borrowDate),t.days)=t.days then 0" +
			" else  ((datediff(a.backDate,a.borrowDate) -t.days)*fk)/10 end as fk " +
			"from book_type t where t.typeid =b.typeId ) as fk," +
			"a.borrowDate,(select name from operator where id=a.operatorId ) as operatorname " +
			"from book_borrow a inner join book_info b on a.bookISBN=b.ISBN inner join reader_info " +
			"r on r.readerid =a.readISBN where a.isback=1 and a.readISBN=?) a1 order by a1.backDate;";
	/**
	 * 修改借出信息，将图书归还
	 */
	public static final String UPDATE_BOOK_BORROW_TO_RETURN="update book_borrow set isback=?,backDate=? where bookISBN =? and readISBN =?";
	
	/**
	 * 判断当前读者借出的书本数是否超过最大借阅量
	 */
	public static final String IS_MORE_THAN_MAX_NUM="select case when " +
			"count(*)>=(select maxnum from reader_info where readerid=?) then 1 " +
			"else 0 end as ismorethanmaxnum  from book_borrow where readISBN =? and isback=0;";
	
	/**
	 * 判断当前读者的会员是否有效
	 */
	public static final String IS_EFFECTIVE_VIP="select case when  datediff(now(),date)<0 then 1" +
			" when  datediff(now(),date)=0  and timediff(now(),date)<=0 then 1 " +
			"else 0 end as iseffectiveVip " +
			"from reader_info where readerid=?";
	/**
	 * 获取所有的借出和归还信息
	 */
	public static final String SEARCH_BORROW_RETURN_INFO_ALL="select b.ISBN,b.bookname,t.typename,r.name,r.readerid,a.isback,a.borrowDate,a.backDate,o.name as operatorname " +
			"from book_borrow a inner join book_info b on a.bookISBN=b.ISBN inner join " +
			"reader_info r on a.readISBN =r.readerid inner join " +
			"operator o on a.operatorId =o.id inner join book_type t on b.typeId=t.typeid where 1=1";
	/**
	 * 获取当前未验收的新订书籍
	 */
	public static final String SELECT_ORDER_BOOK="select * from book_order where checkandaccept=0";
	
	/**
	 * 插入当前未验收的新订书籍
	 */
	public static final String INSERT_ORDER_BOOK="insert into book_order(ISBN ,order_date ,number,orderOperator,checkandaccept,zk) values(?,?,?,?,?,?)";
	
	/**
	 * 批量删除当前未验收的新订书籍
	 */
	public static final String DELETE_ORDER_BOOK="delete from book_order where ISBN =?";
	
	/**
	 * 修改新订书籍验收状态为已验收
	 */
	public static final String UPDATE_ORDER_BOOK="update  book_order  set checkandaccept=? ,checkOperator =? ,check_date=? where ISBN =?";
	
	
}
