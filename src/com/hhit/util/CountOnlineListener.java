package com.hhit.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hhit.entity.User;

public class CountOnlineListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("创建session......");
		HttpSession session=event.getSession();
		User userFind=(User) session.getAttribute("user");
		ServletContext context = event.getSession().getServletContext();
		Integer count = (Integer) context.getAttribute("count");
		if(userFind!=null){
			if (count == null) {
				count = new Integer(1);
			} else {
				int co = count.intValue();
				count = new Integer(co + 1);
			}
			System.out.println("当前用户人数：" + count);
		}
		context.setAttribute("count", count);// 保存人数
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("销毁session......");
		ServletContext context = event.getSession().getServletContext();
		Integer count = (Integer) context.getAttribute("count");
		int co = count.intValue();
		count = new Integer(co - 1);
		context.setAttribute("count", count);
		System.out.println("当前用户人数：" + count);
	}
}
