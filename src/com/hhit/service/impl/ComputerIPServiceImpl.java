package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.ComputerIP;
import com.hhit.service.IComputerIPService;

@Service
@Transactional
public class ComputerIPServiceImpl extends DaoSupportImpl<ComputerIP> implements IComputerIPService{

	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerIP> findbytestroomid(int testroomid) {
		return (List<ComputerIP>)getSession().createQuery("FROM ComputerIP WHERE TestRoomId=? ORDER BY ComputerId ASC")
				.setParameter(0, testroomid)
				.list();
	}

	@Override
	public ComputerIP findbyid(String id) {
		return (ComputerIP)getSession().createQuery("FROM ComputerIP WHERE ID=?")
				.setParameter(0, id)
				.uniqueResult();
	}

	@Override
	public ComputerIP findbyip(String ip) {
		return (ComputerIP)getSession().createQuery("FROM ComputerIP WHERE IP=?")
				.setParameter(0, ip)
				.uniqueResult();
	}

	@Override
	public void deletebyid(String id) {
		ComputerIP object=new ComputerIP();
		object.setID(id);
		getSession().delete(object);
	}

	@Override
	public ComputerIP findbycomputerid(int computerid,int testroomid) {
		return (ComputerIP)getSession().createQuery("FROM ComputerIP WHERE ComputerId=? AND TestRoomId=?")
				.setParameter(0, computerid)
				.setParameter(1, testroomid)
				.uniqueResult();
	}

}
