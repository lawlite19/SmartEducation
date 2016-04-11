package com.hhit.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.UserDetails;
import com.hhit.service.IUserDetailsService;

@Service
@Transactional
public class UserDetailsServiceImpl extends DaoSupportImpl<UserDetails>
		implements IUserDetailsService {

	@Override
	public UserDetails findByUserNum(String userNum) {
		return (UserDetails) getSession().createQuery(//
				"FROM UserDetails WHERE userNum=?")//
				.setParameter(0, userNum)//
				.uniqueResult();
	}

	// @Override
	// public PageBean getPageBean(int pageNum, int pageSize) {
	// Long count=(Long)
	// getSession().createQuery("SELECT COUNT(*) FROM UserDetails").uniqueResult();
	//
	// @SuppressWarnings("unchecked")
	// List<UserDetails> list=getSession().createQuery("FROM UserDetails")//
	// .setFirstResult((pageNum-1)*pageSize)//
	// .setMaxResults(pageSize)//
	// .list();
	// return new PageBean(pageNum, pageSize, count.intValue(), list);
	// }

}
