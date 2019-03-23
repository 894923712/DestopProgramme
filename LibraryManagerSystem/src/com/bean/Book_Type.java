package com.bean;

public class Book_Type {
      public int typeId;  //类别编号
      public String typename; //类别名称
      public int days;     //可借的天数
      public double fk;   //罚款
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public double getFk() {
		return fk;
	}
	public void setFk(double fk) {
		this.fk = fk;
	}
      
      
}
