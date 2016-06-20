package com.hhit.entity;

public class ComputerIP {
	private String ID;
	private int TestRoomId;
	private int ComputerId;
	private String IP;
	
	public ComputerIP() {
		super();
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getTestRoomId() {
		return TestRoomId;
	}

	public void setTestRoomId(int testRoomId) {
		TestRoomId = testRoomId;
	}



	public int getComputerId() {
		return ComputerId;
	}

	public void setComputerId(int computerId) {
		ComputerId = computerId;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	@Override
	public String toString() {
		return "ComputerIP [ID=" + ID + ", TestRoomId=" + TestRoomId
				+ ", ComputerId=" + ComputerId + ", IP=" + IP + "]";
	}
	
	
}
