package com.hhit.action;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Role;
import com.hhit.entity.User;
import com.opensymphony.xwork2.ActionContext;

//注入
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{

	
	private String randomCode;

	private String oldPassword;
	private String newPassword;

	/** 跳转到登录界面 */
	public String loginUI() throws Exception {
		return "loginUI";
	}

	/** 登录验证，成功跳转主页，否则重新登录 */
	//说明:User表中只有两种类型，学生和老师，因为负责人、管理员也是教师
	@SuppressWarnings("unchecked")
	public String login() throws Exception {
		// String s1=user.getUserNum().trim();
		// String s2=user.getPassword().trim();
		// String s3=user.getUserType().trim();
		// 得到验证码
		String code = ((String) ActionContext.getContext().getSession().get("randomCode")).toLowerCase();
		if (code.equals((randomCode.trim().toLowerCase()))) {
			//超级管理员单独验证，他不对应学生和老师
			if(model.getUserType().equals("管理员")){
				User userFind=userService.findUserByNumAndPwd(model.getUserNum(),model.getPassword(), "超级管理员");
				if(null!=userFind){
					ActionContext.getContext().getSession().put("user", userFind);
					return "toIndex";
				}
			}
			if (model.getUserType().equals("学生")) {
				User userFind = userService.findUserByNumAndPwd(model.getUserNum(),model.getPassword(),model.getUserType());
				if (null != userFind) {
					ActionContext.getContext().getSession().put("user", userFind);
					return "toIndex";
				} else {
					addFieldError("login", "账号或密码错误！");
				}
			}
			else{
				//查找用户，userType属性为--》老师
				User userFind=userService.findUserByNumAndPwd(model.getUserNum(),model.getPassword(),"老师");
				if(null!=userFind){
					//查找对应的角色
					List<Role> rolesList=(List<Role>) userFind.getTeacher().getRoles();
					for (Role role : rolesList) {
						if(role.getRoleName().equals(model.getUserType())){
							ActionContext.getContext().getSession().put("user", userFind);
							return "toIndex";
						}
					}
					addFieldError("login", "账号或密码错误！");
				}
				else{
					addFieldError("login", "账号或密码错误！");
				}
			}
		}
		else
			addFieldError("login", "验证码不正确！");
		return "loginUI";
	}

	/** 跳转主页 */
	public String list() throws Exception {
		// setUsers(userService.findAll());
		// ActionContext.getContext().put("users", userService.findAll());

		// ActionContext.getContext().put("users", userService.findAll());

		return "list";
	}

	/** 注销账号 */
	public String logout() throws Exception {
		// 移除session
		ActionContext.getContext().getSession().remove("user");
		return "loginUI";
	}

	/** 跳转修改密码界面 */
	public String modifyPasswordUI() throws Exception {
		return "modifyPasswordUI";
	}

	/** 跳转修改密码界面 */
	public String modifyPassword() throws Exception {
		if ((DigestUtils.md5Hex(oldPassword.trim())).equals(getCurrentUser()
				.getPassword())) {
			String newPass = DigestUtils.md5Hex(newPassword);
			getCurrentUser().setPassword(newPass);
			userService.update(getCurrentUser());
			addFieldError("information", "密码修改成功");
		} else
			addFieldError("information", "旧密码错误");
		return "modifyPasswordUI";
	}

	public String getRandomCode() {
		return randomCode;
	}

	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	

}
