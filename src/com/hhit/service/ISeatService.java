package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Seat;

public interface ISeatService extends IDaoSupport<Seat>{
	public void deleteseats(List<Seat> seats);

	public Seat findbyip(String ip);

	public Seat findbystunum(String stunum);
}
