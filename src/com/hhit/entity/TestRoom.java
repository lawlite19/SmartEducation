package com.hhit.entity;

public class TestRoom {
	private int TestRoomID;
	private String TestRoomName;
	private String FirstIP;
	private String EndtIP;
	private String FtpIP;
	private String FtpName;
	private String FtpPwd;
	private String TeacherPwd;
	private String SetDir;
	public TestRoom() {
		super();
	}
	public int getTestRoomID() {
		return TestRoomID;
	}
	public void setTestRoomID(int testRoomID) {
		TestRoomID = testRoomID;
	}
	public String getTestRoomName() {
		return TestRoomName;
	}
	public void setTestRoomName(String testRoomName) {
		TestRoomName = testRoomName;
	}
	public String getFirstIP() {
		return FirstIP;
	}
	public void setFirstIP(String firstIP) {
		FirstIP = firstIP;
	}
	public String getEndtIP() {
		return EndtIP;
	}
	public void setEndtIP(String endtIP) {
		EndtIP = endtIP;
	}
	public String getFtpIP() {
		return FtpIP;
	}
	public void setFtpIP(String ftpIP) {
		FtpIP = ftpIP;
	}
	public String getFtpName() {
		return FtpName;
	}
	public void setFtpName(String ftpName) {
		FtpName = ftpName;
	}
	public String getFtpPwd() {
		return FtpPwd;
	}
	public void setFtpPwd(String ftpPwd) {
		FtpPwd = ftpPwd;
	}
	public String getTeacherPwd() {
		return TeacherPwd;
	}
	public void setTeacherPwd(String teacherPwd) {
		TeacherPwd = teacherPwd;
	}
	public String getSetDir() {
		return SetDir;
	}
	public void setSetDir(String setDir) {
		SetDir = setDir;
	}
	@Override
	public String toString() {
		return "ClassRoom [TestRoomID=" + TestRoomID + ", TestRoomName="
				+ TestRoomName + ", FirstIP=" + FirstIP + ", EndtIP=" + EndtIP
				+ ", FtpIP=" + FtpIP + ", FtpName=" + FtpName + ", FtpPwd="
				+ FtpPwd + ", TeacherPwd=" + TeacherPwd + ", SetDir=" + SetDir
				+ "]";
	}
	
}
