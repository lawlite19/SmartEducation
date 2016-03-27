package com.hhit.util;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.entity.Department;
import com.hhit.entity.User;
import com.hhit.entity.UserDetails;
@Component
public class Installer {

	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 执行安装
	 */
	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();
		// ==============================================================
		//保存部门
		Department depart1=new Department("淮海工学院", "01", 1, "校级", 1, null);
		Department depart2=new Department("计算机学院", "02", 2, "院级", 1, depart1);
		Department depart3=new Department("软件工程",   "03", 3, "系级", 1, depart2);
		Department depart4=new Department("网络工程",   "04", 3, "系级", 1, depart2);
		Department depart5=new Department("计算机技术", "05", 3, "系级", 1, depart2);
		Department depart6=new Department("理学院", "06", 2, "院级", 1, depart1);
		Department depart7=new Department("数学专业", "07", 3, "系级", 1, depart6);
		Department depart8=new Department("电气工程", "08", 3, "系级", 1, depart6);
		session.save(depart1);
		session.save(depart2);
		session.save(depart3);
		session.save(depart4);
		session.save(depart5);
		session.save(depart6);
		session.save(depart7);
		session.save(depart8);
		
		// ==============================================================
		// 保存用户详细信息
		UserDetails userDetails1=new UserDetails("王勇智", "2012122710", "男", "849451478@qq.com", "18360539986", "849451478", 12, 5, depart1);
		UserDetails userDetails2=new UserDetails("司云轩", "2012122711", "男", "849451478@qq.com", "18360539986", "849451478", 12, 5, depart2);
		UserDetails userDetails3=new UserDetails("王伟奎", "2012122712", "男", "849451478@qq.com", "18360539986", "849451478", 12, 5, depart3);
		UserDetails userDetails4=new UserDetails("王禹钊", "2012122713", "女", "849451478@qq.com", "18360539986", "849451478", 12, 5, depart4);
		UserDetails userDetails5=new UserDetails("胡礼节", "2012122714", "男", "849451478@qq.com", "18360539986", "849451478", 12, 5, depart5);
		UserDetails userDetails6=new UserDetails("李晨", "2012122715", "女", "849451478@qq.com", "18360539986", "849451478", 12, 5, depart6);
		UserDetails userDetails7=new UserDetails("马傲", "2012122716", "男", "849451478@qq.com", "18360539986", "849451478", 12, 5, depart7);
		UserDetails userDetails8=new UserDetails("梁发宏", "2012122717", "男", "849451478@qq.com", "18360539986", "849451478", 12, 5, depart8);
		session.save(userDetails1);
		session.save(userDetails2);
		session.save(userDetails3);
		session.save(userDetails4);
		session.save(userDetails5);
		session.save(userDetails6);
		session.save(userDetails7);
		session.save(userDetails8);
		//		User user = new User();
//		user.setUserNum("201212");
//		user.setUserType("管理员");
//		user.setPassword(DigestUtils.md5Hex("123456"));
//		user.setIsUsable(1);
		//user.s
//		session.save(user); // 保存
		// ==============================================================
		// 保存用户详细信息

		// ==============================================================
		// 保存权限数据
//		Privilege menu, menu1, menu2, menu3, menu4, menu5;
//
//		// --------------------
//		menu = new Privilege("系统管理", null, null);
//		menu1 = new Privilege("岗位管理", "/role_list", menu);
//		menu2 = new Privilege("部门管理", "/department_list", menu);
//		menu3 = new Privilege("用户管理", "/user_list", menu);
//		session.save(menu);
//		session.save(menu1);
//		session.save(menu2);
//		session.save(menu3);
//
//		session.save(new Privilege("岗位列表", "/role_list", menu1));
//		session.save(new Privilege("岗位删除", "/role_delete", menu1));
//		session.save(new Privilege("岗位添加", "/role_add", menu1));
//		session.save(new Privilege("岗位修改", "/role_edit", menu1));
//
//		session.save(new Privilege("部门列表", "/department_list", menu2));
//		session.save(new Privilege("部门删除", "/department_delete", menu2));
//		session.save(new Privilege("部门添加", "/department_add", menu2));
//		session.save(new Privilege("部门修改", "/department_edit", menu2));
//
//		session.save(new Privilege("用户列表", "/user_list", menu3));
//		session.save(new Privilege("用户删除", "/user_delete", menu3));
//		session.save(new Privilege("用户添加", "/user_add", menu3));
//		session.save(new Privilege("用户修改", "/user_edit", menu3));
//		session.save(new Privilege("初始化密码", "/user_initPassword", menu3));
//
//		// --------------------
//		menu = new Privilege("网上交流", null, null);
//		menu1 = new Privilege("论坛管理", "/forumManage_list", menu);
//		menu2 = new Privilege("论坛", "/forum_list", menu);
//		session.save(menu);
//		session.save(menu1);
//		session.save(menu2);
//
//		// --------------------
//		menu = new Privilege("审批流转", null, null);
//		menu1 = new Privilege("审批流程管理", "/processDefinition_list", menu);
//		menu2 = new Privilege("申请模板管理", "/template_list", menu);
//		menu3 = new Privilege("起草申请", "/flow_templateList", menu);
//		menu4 = new Privilege("待我审批", "/flow_myTaskList", menu);
//		menu5 = new Privilege("我的申请查询", "/flow_myApplicationList", menu);
//		session.save(menu);
//		session.save(menu1);
//		session.save(menu2);
//		session.save(menu3);
//		session.save(menu4);
//		session.save(menu5);
	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();
	}
}
