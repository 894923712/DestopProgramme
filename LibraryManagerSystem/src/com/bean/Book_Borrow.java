package com.bean;

import java.util.Date;

public class Book_Borrow {
	public int id;
	public String bookISBN;
	public String bookName;
	public String readerName;
	public int operatorId;
	public String operatorname;
	public String readISBN;
	public int isback;// 是否归还 1 表示归还 0 表示没有归还
	public Date borrowDate;// 借出日期
	public Date backDate; // 归还日期
	public double fk;//本书的罚款
	public double amountfk;//总罚款
	public String bookTypeName;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public String getOperatorname() {
		return operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public String getReadISBN() {
		return readISBN;
	}

	public void setReadISBN(String readISBN) {
		this.readISBN = readISBN;
	}

	public int getIsback() {
		return isback;
	}

	public void setIsback(int isback) {
		this.isback = isback;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getBackDate() {
		return backDate;
	}

	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

	public double getFk() {
		return fk;
	}

	public void setFk(double fk) {
		this.fk = fk;
	}

	public double getAmountfk() {
		return amountfk;
	}

	public void setAmountfk(double amountfk) {
		this.amountfk = amountfk;
	}

	public String getBookTypeName() {
		return bookTypeName;
	}

	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}
	
}
