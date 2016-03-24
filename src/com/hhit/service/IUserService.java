package com.hhit.service;

import java.util.List;

import com.hhit.entity.User;

public interface IUserService {

	//查询所有用户
	List<User> findAll();
	
}
