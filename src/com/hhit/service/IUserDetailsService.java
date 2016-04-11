package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.PageBean;
import com.hhit.entity.UserDetails;

public interface IUserDetailsService extends IDaoSupport<UserDetails> {

	//根据账号查询用户详细信息
	UserDetails findByUserNum(String userNum);

//	//查询分页信息 version-1--用户list
//	PageBean getPageBean(int pageNum, int pageSize);

}
