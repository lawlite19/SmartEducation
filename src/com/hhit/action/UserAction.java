package com.hhit.action;

import java.util.List;

import javax.annotation.Resource;

import org.aspectj.weaver.IUnwovenClassFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.entity.User;
import com.hhit.service.IUserService;
import com.hhit.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

//注入
@Controller
@Scope("prototype")
public class UserAction{
	//获得
	@Resource
	private IUserService userService;
	
	private User user;
	private List<User> users;
	
	/** 跳转到登录界面 */
	public String loginUI() throws Exception{
		return "loginUI";
	}
	/** 登录验证，成功跳转主页，否则重新登录 */
	public String login() throws Exception{
//		String s1=user.getUserNum().trim();
//		String s2=user.getPassword().trim();
//		String s3=user.getUserType().trim();	
		if(userService.isValidateUser(user.getUserNum().trim(),user.getPassword().trim(),user.getUserType().trim()))	
		{
			return "toIndex";
		}
		return "loginUI";
	}
	
	/**  列表  */
	public String list() throws Exception{
		setUsers(userService.findAll());
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}

}
