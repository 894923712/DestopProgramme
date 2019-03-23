package com.bean;

import java.util.Date;

public class Reader_info {
	public String ISBN;// 图书编号
	public String name;
	public String sex;
	public int age;
	public String identityCard;// 证件号码
	public Date date;// 会员证件有效日期
	public int maxnum;// 最大借书量
	public String tel;// 电话号码
	public double keepmoney;// 押金
	public String zj;// 证件类型
	public String job;
	public Date bzTime;// 办证日期

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMaxnum() {
		return maxnum;
	}

	public void setMaxnum(int maxnum) {
		this.maxnum = maxnum;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public double getKeepmoney() {
		return keepmoney;
	}

	public void setKeepmoney(double keepmoney) {
		this.keepmoney = keepmoney;
	}

	public String getZj() {
		return zj;
	}

	public void setZj(String zj) {
		this.zj = zj;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getBzTime() {
		return bzTime;
	}

	public void setBzTime(Date bzTime) {
		this.bzTime = bzTime;
	}

}
