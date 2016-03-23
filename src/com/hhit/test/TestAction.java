package com.hhit.test;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

//声明一个bean
/***
 * @author bob
 * @Controller("beanname")
 * @Service("beanname")
 * @Repository("beanname")
 * @Component("beanname")
 * 与下面一样，都是声明一个bean,与在spring中声明bean一样效果
 */
@Controller("testAction")
@Scope("prototype")
public class TestAction extends ActionSupport {
	@Resource
	private TestService testService;
	
	@Override
	public String execute() throws Exception{
		System.out.println("---->TestAction execute");
		testService.saveTwoUsers();
		return "success";
	}
}
