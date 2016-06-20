package com.hhit.entity;

public class TestingControl {
	private int Rnumber;
	private String TestName;
	private String TestPaperID;
	private String TestList;
	private String TesterID;
	private String TesterName;
	private String TestRoomName;
	private String ClassName;
	private String LoginFlag;
	private String TestRoomID;
	private String teaname;
	private String teaid;
	
	public TestingControl() {
		super();
	}
	
	public int getRnumber() {
		return Rnumber;
	}
	public void setRnumber(int rnumber) {
		Rnumber = rnumber;
	}
	public String getTestName() {
		return TestName;
	}
	public void setTestName(String testName) {
		TestName = testName;
	}
	public String getTestList() {
		return TestList;
	}
	public void setTestList(String testList) {
		TestList = testList;
	}
	public String getTesterID() {
		return TesterID;
	}
	public void setTesterID(String testerID) {
		TesterID = testerID;
	}
	public String getTesterName() {
		return TesterName;
	}
	public void setTesterName(String testerName) {
		TesterName = testerName;
	}
	public String getTestRoomName() {
		return TestRoomName;
	}
	public void setTestRoomName(String testRoomName) {
		TestRoomName = testRoomName;
	}
	public String getClassName() {
		return ClassName;
	}
	public void setClassName(String className) {
		ClassName = className;
	}
	public String getLoginFlag() {
		return LoginFlag;
	}
	public void setLoginFlag(String loginFlag) {
		LoginFlag = loginFlag;
	}
	public String getTestRoomID() {
		return TestRoomID;
	}
	public void setTestRoomID(String testRoomID) {
		TestRoomID = testRoomID;
	}
	public String getTeaname() {
		return teaname;
	}
	public void setTeaname(String teaname) {
		this.teaname = teaname;
	}

	public String getTeaid() {
		return teaid;
	}

	public void setTeaid(String teaid) {
		this.teaid = teaid;
	}

	public String getTestPaperID() {
		return TestPaperID;
	}

	public void setTestPaperID(String testPaperID) {
		TestPaperID = testPaperID;
	}

	@Override
	public String toString() {
		return "TestingControl [Rnumber=" + Rnumber + ", TestName=" + TestName
				+ ", TestPaperID=" + TestPaperID + ", TestList=" + TestList
				+ ", TesterID=" + TesterID + ", TesterName=" + TesterName
				+ ", TestRoomName=" + TestRoomName + ", ClassName=" + ClassName
				+ ", LoginFlag=" + LoginFlag + ", TestRoomID=" + TestRoomID
				+ ", teaname=" + teaname + ", teaid=" + teaid + "]";
	}

}
