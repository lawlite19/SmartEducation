package com.hhit.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hhit.base.DaoSupportImpl;
import com.hhit.dao.IUserDao;
import com.hhit.entity.User;

@Deprecated
//注入容器
@Repository
public class UserDaoImpl extends DaoSupportImpl<User> implements IUserDao {

	@Override
	public User findByNumPwdAndType(String userNum, String password,
			String userType) {
		@SuppressWarnings("unchecked")
		List<User> users=getSession().createQuery(//
				"FROM User WHERE userNum=? AND password=? AND userType=? AND isUsable=?")//
				.setParameter(0, userNum)//
				.setParameter(1, password)//
				.setParameter(2, userType)//
				.setParameter(3, 1)//
				.list();
		if(users.size()>0)
			return users.get(0);
		return null;
	}
}
