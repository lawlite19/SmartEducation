package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.User;
import com.hhit.entity.UserOnlineTime;
import com.hhit.service.IUserOnlineTimeService;
@Service
@Transactional
public class UserOnlineTimeServiceImpl extends DaoSupportImpl<UserOnlineTime> implements IUserOnlineTimeService{

	@Override
	public UserOnlineTime findByUser(User userFind) {
		return (UserOnlineTime) getSession().createQuery("FROM UserOnlineTime WHERE user=? ORDER BY id DESC")//
				.setParameter(0, userFind)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
			
	}

}
