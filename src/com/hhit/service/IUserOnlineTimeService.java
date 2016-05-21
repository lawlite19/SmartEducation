package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.User;
import com.hhit.entity.UserOnlineTime;

public interface IUserOnlineTimeService extends IDaoSupport<UserOnlineTime>{

	//查询最后一条记录
	UserOnlineTime findByUser(User userFind);

}
