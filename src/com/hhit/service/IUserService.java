package com.hhit.service;


import com.hhit.base.IDaoSupport;
import com.hhit.entity.User;
import com.hhit.entity.UserDetails;

public interface IUserService extends IDaoSupport<User> {

	//验证是否是登录用户
	boolean isValidateUser(String userNum, String password, String userType);
	//根据UserDetailsId查询User
	User findByDetailsId(UserDetails userDetails);
}
