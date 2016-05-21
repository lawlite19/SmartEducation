package com.hhit.entity;
import java.util.Date;

//
//  @ Project : Untitled
//  @ File Name : UserOnlineTime.java
//  @ Date : 2016/5/20
//  @ Author : 
//
//




public class UserOnlineTime {
	private Integer id;
	private Date date;
	private Integer durationMinute;
	
	private User user;
	
	/**
	 * 默认构造函数
	 */
	public UserOnlineTime() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造函数
	 */
	public UserOnlineTime(Date date, Integer durationMinute, User user) {
		super();
		this.date = date;
		this.durationMinute = durationMinute;
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getDurationMinute() {
		return durationMinute;
	}
	public void setDurationMinute(Integer durationMinute) {
		this.durationMinute = durationMinute;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
