package com.hhit.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 跳转jsp功能
 * @author bob
 */
@Controller
@Scope("prototype")
public class InterfaceAPIAction {

	/** 登录  */
	public String loginUI() throws Exception{
		return "loginUI";
	}
}
