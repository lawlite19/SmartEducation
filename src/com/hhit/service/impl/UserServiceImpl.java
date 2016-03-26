package com.hhit.service.impl;




import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.BaseDaoImpl;
import com.hhit.dao.IUserDao;
import com.hhit.entity.User;
import com.hhit.service.IUserService;

//注入
@Service
//开事务
@Transactional
public class UserServiceImpl extends BaseDaoImpl<User> implements IUserService {

	@Resource
	private IUserDao userDao;
	@Override
	public boolean isValidateUser(String userNum, String password, String userType) {
		User user= userDao.findByNumPwdAndType(userNum,password,userType);
		if(user!=null)
			return true;
		return false;
	}
	
}
