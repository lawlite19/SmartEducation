package com.hhit.dao;

import com.hhit.base.IBaseDao;
import com.hhit.entity.User;

public interface IUserDao extends IBaseDao<User>{

	//根据用户账号、密码、类型查找
	User findByNumPwdAndType(String userNum, String password, String userType);
	
}
