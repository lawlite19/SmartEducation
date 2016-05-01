package com.hhit.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.QQLoginInfo;
import com.hhit.entity.Role;
import com.hhit.entity.User;
import com.hhit.entity.WeiboInfo;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class WeiboInfoAction extends BaseAction<WeiboInfo> {
	private String userNum;
	private String password;
	private String userType;

	/** 跳转到绑定界面或者网站首页 */
	public String bindUserUI() throws Exception {
		WeiboInfo weiboInfo=weiboInfoService.findByIdenfier(model.getIdentifier());
		
		if (weiboInfo != null) { // 如果存在就跳转首页
			if (weiboInfo.getUser() != null) {
				ActionContext.getContext().getSession().put("user", weiboInfo.getUser());
				return "toIndex";
			} else {
				ActionContext.getContext().put("identifier", model.getIdentifier());
				return "bindUserUI";
			}
		} else {
			// 否则保存到qq第三方登录信息表，跳转绑定用户界面,将openId携带过去
			weiboInfoService.save(model);
			return "bindUserUI";
		}
	}
	//绑定提取
	public void bind(User userFind){
		//如果属性的绑定信息合法，查找到qq信息表信息，设置对应user，更新数据
		WeiboInfo weiboInfo = weiboInfoService.findByIdenfier(model.getIdentifier());
		weiboInfo.setUser(userFind);
		weiboInfoService.update(weiboInfo);
		ActionContext.getContext().getSession().put("user", userFind);
	}
	/** 绑定用户 */
	public String bindUser() throws Exception {
		if(userType.equals("管理员")){
			User userFind=userService.findUserByNumAndPwd(userNum,password, "超级管理员");
			if(null!=userFind){
				//如果属性的绑定信息合法，查找到qq信息表信息，设置对应user，更新数据
				bind(userFind);
				return "toIndex";
			}
		}
		if (userType.equals("学生")) {
			User userFind = userService.findUserByNumAndPwd(userNum,password,userType);
			if (null != userFind) {
				//如果属性的绑定信息合法，查找到qq信息表信息，设置对应user，更新数据
				bind(userFind);
				return "toIndex";
			} else {
				addFieldError("bindInfo", "账号或密码错误！");
			}
		}
		else{
			//查找用户，userType属性为--》老师
			User userFind=userService.findUserByNumAndPwd(userNum,password,"老师");
			if(null!=userFind){
				//查找对应的角色
				List<Role> rolesList=(List<Role>) userFind.getTeacher().getRoles();
				for (Role role : rolesList) {
					if(role.getRoleName().equals(userType)){
						//如果属性的绑定信息合法，查找到qq信息表信息，设置对应user，更新数据
						bind(userFind);
						return "toIndex";
					}
				}
				addFieldError("bindInfo", "账号或密码错误！");
			}
			else{
				addFieldError("bindInfo", "账号或密码错误！");
			}
		}
		return "bindUserUI";
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
