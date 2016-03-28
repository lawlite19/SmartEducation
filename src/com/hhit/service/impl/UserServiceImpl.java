package com.hhit.service.impl;






import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.User;
import com.hhit.entity.UserDetails;
import com.hhit.service.IUserService;
import com.opensymphony.xwork2.ActionContext;

//注入
@Service
//开事务
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements IUserService {

	//@Resource
	//private IUserDao userDao;
	@Override
	public boolean isValidateUser(String userNum, String password, String userType) {
		String md5Digest=DigestUtils.md5Hex(password);
		User user=(User) getSession().createQuery(//
				"FROM User WHERE userNum=? AND password=? AND userType=? AND isUsable=?")//
				.setParameter(0, userNum)//
				.setParameter(1, md5Digest)//
				.setParameter(2, userType)//
				.setParameter(3, 1)//
				.uniqueResult();
		if(user!=null)
		{
			//保存到session
			ActionContext.getContext().getSession().put("user", user);
			return true;
		}		
		return false;

	}
	@Override
	public User findByDetailsId(UserDetails userDetails) {
		return (User) getSession().createQuery("FROM User WHERE userDetails=?")//
		.setParameter(0, userDetails)//
		.list().get(0);
	}
	
}
