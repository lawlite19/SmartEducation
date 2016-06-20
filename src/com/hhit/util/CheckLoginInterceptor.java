package com.hhit.util;

import com.hhit.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class CheckLoginInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		// 获取信息
		User user = (User) ActionContext.getContext().getSession().get("user"); // 当前登录用户
		//    /
		String namespace = invocation.getProxy().getNamespace();
		//   xxx_xxxx
		String actionName = invocation.getProxy().getActionName();
		//     /xxx_xxxx
		String privUrl = namespace + actionName; // 对应的权限URL

		// 如果未登录
		if (user == null) {
			if (privUrl.startsWith("/logintestmonitor")||privUrl.equals("/rand")) { 
				// "/user_logintestmonitorUI",
				// "/user_logintestmonitor"
				// 如果是去登录，就放行
				return invocation.invoke();
			} else {
				// 如果不是去登录
					//-->绑定
				if(privUrl.startsWith("/qqLoginInfo")||privUrl.startsWith("/weiboInfo"))
					return invocation.invoke();
				return "logintestmonitorUI";
			}
		}
		return invocation.invoke();
	}

}
