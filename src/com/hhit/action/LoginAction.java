package com.hhit.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Student;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<Student>{
	private String randomCode;
	
	public String mLogin() throws Exception{
		
		return "mLogin";
	}
}
