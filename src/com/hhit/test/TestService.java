package com.hhit.test;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.entity.User;

//在容器中才可以注入
@Service("testService")
public class TestService {
	//注入bean的资源
	@Resource
	private SessionFactory sessionFactory;
	
	
	//开事务
	@Transactional
	public void saveTwoUsers(){
		Session session=sessionFactory.getCurrentSession();
		session.save(new User());
		session.save(new User());
	}
}
