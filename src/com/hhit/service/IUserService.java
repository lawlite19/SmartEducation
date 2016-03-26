package com.hhit.service;


import com.hhit.base.IBaseDao;
import com.hhit.entity.User;

public interface IUserService extends IBaseDao<User> {

	//验证是否是登录用户
	boolean isValidateUser(String userNum, String password, String userType);

}
