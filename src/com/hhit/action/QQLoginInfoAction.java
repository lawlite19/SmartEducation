package com.hhit.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.QQLoginInfo;
import com.hhit.entity.User;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller("qqLoginInfoAction")
@Scope("prototype")
public class QQLoginInfoAction extends BaseAction<QQLoginInfo> {

	private String userNum;
	private String password;
	private String userType;
	/** 跳转到绑定界面或者网站首页 */
	public String bindUserUI() throws Exception {
		QQLoginInfo qqLoginInfo = qqLoginService.findByOpenId(model.getOpenId());
		if (qqLoginInfo != null) 
		{   //如果存在就跳转首页
			if(qqLoginInfo.getUser()!=null)
			{
				ActionContext.getContext().getSession().put("user", qqLoginInfo.getUser());
				return "toIndex";
			}
			else
			{
				ActionContext.getContext().put("openId", model.getOpenId());
				return "bindUserUI";
			}
		}
		else
		{
			//否则保存到qq第三方登录信息表，跳转绑定用户界面,将openId携带过去
			qqLoginService.save(model);
			return "bindUserUI";
		}
	}
	/** 绑定用户 */
	public String bindUser() throws Exception{
		User userFind=userService.findUserByNumAndPwd(userNum, password, userType);
		if(null!=userFind)
		{
			//如果属性的绑定信息合法，查找到qq信息表信息，设置对应user，更新数据
			QQLoginInfo qqLoginInfo = qqLoginService.findByOpenId(model.getOpenId());
			qqLoginInfo.setUser(userFind);
			qqLoginService.update(qqLoginInfo);
			ActionContext.getContext().getSession().put("user", userFind);
			return "toIndex";
		}
		else
		{
			addFieldError("bindInfo", "信息输入有误！");
			return "bindUserUI";
		}
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
