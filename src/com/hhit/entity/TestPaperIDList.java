package com.hhit.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class TestPaperIDList {
	private String TestPaperID;
	private int PaperCombineID;
	private String TestList;
	private String TestID;
	private int CourseID;
	private Timestamp BeginTime;
	private Timestamp EndTime;
	private String TestFlag;
	private int TestPaperIDRnumber;
	private Boolean adminflag;
	
	public TestPaperIDList(){
	}

	public String getTestPaperID() {
		return TestPaperID;
	}

	public void setTestPaperID(String testPaperID) {
		TestPaperID = testPaperID;
	}

	public int getPaperCombineID() {
		return PaperCombineID;
	}

	public void setPaperCombineID(int paperCombineID) {
		PaperCombineID = paperCombineID;
	}

	public String getTestList() {
		return TestList;
	}

	public void setTestList(String testList) {
		TestList = testList;
	}

	public String getTestID() {
		return TestID;
	}

	public void setTestID(String testID) {
		TestID = testID;
	}

	public int getCourseID() {
		return CourseID;
	}

	public void setCourseID(int courseID) {
		CourseID = courseID;
	}

	public Timestamp getBeginTime() {
		return BeginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		BeginTime = beginTime;
	}

	public Timestamp getEndTime() {
		return EndTime;
	}

	public void setEndTime(Timestamp endTime) {
		EndTime = endTime;
	}

	public String getTestFlag() {
		return TestFlag;
	}

	public void setTestFlag(String testFlag) {
		TestFlag = testFlag;
	}

	public int getTestPaperIDRnumber() {
		return TestPaperIDRnumber;
	}

	public void setTestPaperIDRnumber(int testPaperIDRnumber) {
		TestPaperIDRnumber = testPaperIDRnumber;
	}

	public Boolean getAdminflag() {
		return adminflag;
	}

	public void setAdminflag(Boolean adminflag) {
		this.adminflag = adminflag;
	}
	
}
