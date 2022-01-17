package com.poscoict.guestbook.vo;

import java.sql.Date;

public class GuestbookVO {
	private int no;
	private String name;
	private String passwd;
	private String message;
	private Date reg_date;
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String password) {
		this.message = password;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date date) {
		this.reg_date = date;
	}
	@Override
	public String toString() {
		return "GuestbookDAO [no=" + no + ", name=" + name + ", message=" + message + ", reg_date=" + reg_date + "]";
	}
	
}
