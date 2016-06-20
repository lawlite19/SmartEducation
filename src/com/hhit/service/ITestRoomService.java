package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.TestRoom;

public interface ITestRoomService extends IDaoSupport<TestRoom>{
	public TestRoom findRoom(int testroomid);
	public List<TestRoom> findRooms(String location);
}
