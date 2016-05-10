package com.hhit.service.impl;






import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Student;
import com.hhit.entity.Teacher;
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
	public User findUserByNumAndPwd(String userNum, String password,String userType) {
		String md5Digest=DigestUtils.md5Hex(password);
		return (User) getSession().createQuery(//
				"FROM User WHERE userNum=? AND password=? AND userType=?")//
				.setParameter(0, userNum)//
				.setParameter(1, md5Digest)//
				.setParameter(2, userType)//
				.uniqueResult();
	}
	@Override
	public User findByDetailsId(UserDetails userDetails) {
		return (User) getSession().createQuery("FROM User WHERE userDetails=?")//
		.setParameter(0, userDetails)//
		.list().get(0);
	}
	@Override
	public User findByStudent(Student stuFind) {
		return (User) getSession().createQuery("FROM User WHERE student=?")//
				.setParameter(0, stuFind)//
				.uniqueResult();
	}
	@Override
	public User findByTeacher(Teacher teaFind) {
		return (User) getSession().createQuery("FROM User WHERE teacher=?")//
				.setParameter(0, teaFind)//
				.uniqueResult();
	}

	
}
