package com.hhit.entity;

import java.sql.Timestamp;
import java.util.Set;




public class Teacher implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String teaNum;
	private String teaName;
	private String title;
	private String sex;
	private Timestamp birthday;
	private String finalEdu;
	private Timestamp inTime;
	
	
	private Department department;
	private Set<Role> roles;
	
	private Set<Course> courses;
	
	/**
	 * 默认构造函数
	 */
	public Teacher(String tNum,String tName){
		teaNum=tNum;
		teaName=tName;
	}
	/**
	 * 构造函数
	 */
	public Teacher(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTeaNum() {
		return teaNum;
	}
	public void setTeaNum(String teaNum) {
		this.teaNum = teaNum;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public String getFinalEdu() {
		return finalEdu;
	}
	public void setFinalEdu(String finalEdu) {
		this.finalEdu = finalEdu;
	}
	public Timestamp getInTime() {
		return inTime;
	}
	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
}
