package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Seat;
import com.hhit.service.ISeatService;

@Transactional
@Service
public class SeatServiceImpl extends DaoSupportImpl<Seat> implements ISeatService{

	@Override
	public void deleteseats(List<Seat> seats) {
		for (Seat seat : seats) {
			getSession().delete(seat);
		}
	}

	@Override
	public Seat findbyip(String ip) {
		return (Seat) getSession().createQuery("FROM Seat WHERE Ip=?")
				.setParameter(0, ip)
				.uniqueResult();
	}

	@Override
	public Seat findbystunum(String stunum) {
		return (Seat) getSession().createQuery("FROM Seat WHERE StuNum=?")
				.setParameter(0, stunum)
				.list().get(0);
	}
}
