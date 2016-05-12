package com.hhit.entity;

import java.sql.Timestamp;
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : TestPaper.java
//  @ Date : 2016/5/12
//  @ Author : 
//
//
import java.util.Set;




public class TestPaper {
	private Integer id;
	private String testType;
	private Integer questionCount;
	private Integer submitCount;
	private Timestamp endTime;
	
	private String teaNum;
	
	
	private Class_ class_;
	private Course course;
	private Chapter chapter;
	private Set<TestQuestion> testQuestions;

	
	
	/**
	 * 默认构造函数
	 */
	public TestPaper() {
	}
	/***
	 * 
	 * @return
	 */
	public TestPaper(String testType, Integer questionCount,
			Integer submitCount, Timestamp endTime, String teaNum,
			Class_ class_, Course course, Chapter chapter) {
		this.testType = testType;
		this.questionCount = questionCount;
		this.submitCount = submitCount;
		this.endTime = endTime;
		this.teaNum = teaNum;
		this.class_ = class_;
		this.course = course;
		this.chapter = chapter;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public Integer getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(Integer questionCount) {
		this.questionCount = questionCount;
	}
	public Integer getSubmitCount() {
		return submitCount;
	}
	public void setSubmitCount(Integer submitCount) {
		this.submitCount = submitCount;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getTeaNum() {
		return teaNum;
	}
	public void setTeaNum(String teaNum) {
		this.teaNum = teaNum;
	}
	public Class_ getClass_() {
		return class_;
	}
	public void setClass_(Class_ class_) {
		this.class_ = class_;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public Set<TestQuestion> getTestQuestions() {
		return testQuestions;
	}
	public void setTestQuestions(Set<TestQuestion> testQuestions) {
		this.testQuestions = testQuestions;
	}
	
	
}
