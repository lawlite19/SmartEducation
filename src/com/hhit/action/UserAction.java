package com.hhit.action;

import javax.annotation.Resource;

import org.aspectj.weaver.IUnwovenClassFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.service.IUserService;
import com.hhit.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
//注入
@Controller
@Scope("prototype")
public class UserAction extends ActionSupport{
	//获得
	@Resource
	private IUserService userService;
	/**  列表  */
	public String list() throws Exception{
		userService.findAll();
		return "list";
	}
	/**  删除  */
	public String delete() throws Exception{
		return "toList";
	}
	/**  添加  */
	public String add() throws Exception{
		return "toList";
	}
	/**  添加页面  */
	public String addUI() throws Exception{
		return "addUI";
	}
	/**  修改  */
	public String edit() throws Exception{
		return "toList";
	}
	/**  修改页面  */
	public String editUI() throws Exception{
		return "editUI";
	}
}
