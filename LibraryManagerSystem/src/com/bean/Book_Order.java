package com.bean;

import java.util.Date;

public class Book_Order {
	public String ISBN;
	public Date order_date;
	public int number;
	public String operator;
	public int checkandaccept;// 是否验收 1 表示验收 0 表示没有验收
	public double zk;// 书籍折扣
    public Date check_date;
    public String checkOperator ;
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getCheckandaccept() {
		return checkandaccept;
	}

	public void setCheckandaccept(int checkandaccept) {
		this.checkandaccept = checkandaccept;
	}

	public double getZk() {
		return zk;
	}

	public void setZk(double zk) {
		this.zk = zk;
	}

	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}

	public String getCheckOperator() {
		return checkOperator;
	}

	public void setCheckOperator(String checkOperator) {
		this.checkOperator = checkOperator;
	}

}
