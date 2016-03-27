package com.hhit.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	private ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	
//	@Test
//	public void testBean() throws Exception{
//		TestAction testAction=(TestAction) ac.getBean("testAction");
//		System.out.println(testAction);
//	}
	//测试sessionFaction,创建sessionFactory的时候会自动建表
	@Test
	public void testSessionFaction() throws Exception{
		SessionFactory sessionFaction=(SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sessionFaction);
	}
//	//测试事务
//	@Test
//	public void testTransaction() throws Exception{
//		TestService testService=(TestService) ac.getBean("testService");
//		testService.saveTwoUsers();
//	}
}
