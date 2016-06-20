package com.hhit.entity;

public class Seat {
	private String Id;
	private String Ip;
	private String StuNum;
	private int Flag;
	public Seat() {
		super();
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getIp() {
		return Ip;
	}
	public void setIp(String ip) {
		Ip = ip;
	}
	public String getStuNum() {
		return StuNum;
	}
	public void setStuNum(String stuNum) {
		StuNum = stuNum;
	}
	public int getFlag() {
		return Flag;
	}
	public void setFlag(int flag) {
		Flag = flag;
	}
	@Override
	public String toString() {
		return "Seat [Id=" + Id + ", Ip=" + Ip + ", StuNum=" + StuNum
				+ ", Flag=" + Flag + "]";
	}
	
}
