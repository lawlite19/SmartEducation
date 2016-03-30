package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.PageBean;
import com.hhit.entity.UserDetails;

public interface IUserDetailsService extends IDaoSupport<UserDetails> {

	//查询分页信息--用户list
	PageBean getPageBean(int pageNum, int pageSize);

}
