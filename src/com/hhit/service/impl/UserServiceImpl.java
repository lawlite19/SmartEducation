package com.hhit.service.impl;




import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.dao.IUserDao;
import com.hhit.entity.User;
import com.hhit.service.IUserService;

//注入
@Service
//开事务
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements IUserService {

	@Resource
	private IUserDao userDao;
	@Override
	public boolean isValidateUser(String userNum, String password, String userType) {
		@SuppressWarnings("unchecked")
		List<User> users=getSession().createQuery(//
				"FROM User WHERE userNum=? AND password=? AND userType=? AND isUsable=?")//
				.setParameter(0, userNum)//
				.setParameter(1, password)//
				.setParameter(2, userType)//
				.setParameter(3, 1)//
				.list();
		if(users.size()>0)
			return true;
		return false;

	}
	
}
