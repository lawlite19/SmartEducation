package com.hhit.entity;

/**
 * TClassSelectCourse entity. @author MyEclipse Persistence Tools
 */

public class ClassSelectCourse implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Course Course;
	private ATerm ATerm;
	private Class_ class_;
	private String selectNum;
	private String teacherNum;
	private Double selfAccess;
	private Double teachAccess;
	private String teacherName;
	// Constructors

	/** default constructor */
	public ClassSelectCourse() {
	}

	/** full constructor */
	public ClassSelectCourse(Course Course, ATerm ATerm, Class_ class_,
			String selectNum, String teacherNum, Double selfAccess,
			Double teachAccess,String teacherName) {
		this.Course = Course;
		this.ATerm = ATerm;
		this.class_ = class_;
		this.selectNum = selectNum;
		this.teacherNum = teacherNum;
		this.selfAccess = selfAccess;
		this.teachAccess = teachAccess;
		this.teacherName = teacherName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Course getCourse() {
		return this.Course;
	}

	public void setCourse(Course Course) {
		this.Course = Course;
	}

	public ATerm getATerm() {
		return this.ATerm;
	}

	public void setATerm(ATerm ATerm) {
		this.ATerm = ATerm;
	}
	public String getSelectNum() {
		return this.selectNum;
	}

	public void setSelectNum(String selectNum) {
		this.selectNum = selectNum;
	}

	public String getTeacherNum() {
		return this.teacherNum;
	}

	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}

	public Double getSelfAccess() {
		return this.selfAccess;
	}

	public void setSelfAccess(Double selfAccess) {
		this.selfAccess = selfAccess;
	}

	public Double getTeachAccess() {
		return this.teachAccess;
	}

	public void setTeachAccess(Double teachAccess) {
		this.teachAccess = teachAccess;
	}

	public Class_ getClass_() {
		return class_;
	}

	public void setClass_(Class_ class_) {
		this.class_ = class_;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

}