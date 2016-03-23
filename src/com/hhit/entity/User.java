package com.hhit.entity;


public class User {
	private Long id;
	private String name;
	
	public User() {
		super();
	}
	public User(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
