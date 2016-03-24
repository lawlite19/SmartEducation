package com.hhit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.dao.IUserDao;
import com.hhit.entity.User;
import com.hhit.service.IUserService;

//注入
@Service
//开事务
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Resource
	private IUserDao userDao;
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
