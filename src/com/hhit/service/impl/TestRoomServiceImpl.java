package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl2;
import com.hhit.entity.TestRoom;
import com.hhit.service.ITestRoomService;

@Service
@Transactional
public class TestRoomServiceImpl extends DaoSupportImpl2<TestRoom> implements ITestRoomService{

	@Override
	public TestRoom findRoom(int testroomid) {
		return (TestRoom)getSession().createQuery("FROM TestRoom WHERE TestRoomID="+testroomid)
				.uniqueResult();
	}

	@Override
	public List<TestRoom> findRooms(String location) {
		return (List<TestRoom>)getSession().createQuery("FROM TestRoom WHERE RoomPlace "+location)
				.list();
	}

}
