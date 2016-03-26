package com.hhit.dao;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.User;

@Deprecated
public interface IUserDao extends IDaoSupport<User>{

	//根据用户账号、密码、类型查找
	User findByNumPwdAndType(String userNum, String password, String userType);
	
}
