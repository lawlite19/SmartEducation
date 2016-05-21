package com.hhit.test;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.jar.JarFile;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hhit.entity.User;
import com.hhit.service.IUserService;

public class TestUserSize {
	private ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	private static volatile Instrumentation globalInstr;
	@Test
	public void testUserSize(){
		IUserService userService=(IUserService) ac.getBean("userServiceImpl");
		User userFind=userService.findById(1);
		long size = globalInstr.getObjectSize(userFind);
		System.out.println(size);
	}
}
