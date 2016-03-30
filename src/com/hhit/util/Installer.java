package com.hhit.util;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.enterprise.inject.New;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.entity.Department;
import com.hhit.entity.Privilege;
import com.hhit.entity.Role;
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

		// ======================================================
		//保存超级管理员
//		User user=new User();
//		user.setIsUsable(1);
//		user.setPassword(DigestUtils.md5Hex("admin"));
//		user.setUserType("admin");
//		user.setUserNum("admin");
//		user.setUserDetails(null);
//		session.save(user);
//		
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
		// ==============================================================
		// 保存用户信息
		User user1=new User("2012122710", "123456", 1, "管理员",userDetails1);
		User user2=new User("2012122711", "123456", 1, "负责人", userDetails2);
		User user3=new User("2012122712", "123456", 1, "负责人", userDetails3);
		User user4=new User("2012122713", "123456", 1, "教师", userDetails4);
		User user5=new User("2012122714", "123456", 1, "教师", userDetails5);
		User user6=new User("2012122715", "123456", 1, "学生", userDetails6);
		User user7=new User("2012122716", "123456", 1, "学生", userDetails7);
		User user8=new User("2012122717", "123456", 1, "管理员", userDetails8);
		session.save(user1);
		session.save(user2);
		session.save(user3);
		session.save(user4);
		session.save(user5);
		session.save(user6);
		session.save(user7);
		session.save(user8);
//		// ==============================================================
//		// 保存角色
//		Role role1=new Role("管理员", "管理员功能", new Timestamp(new Date().getTime()));
//		Role role2=new Role("课程负责人", "课程负责人功能", new Timestamp(new Date().getTime()));
//		Role role3=new Role("普通教师", "普通教师功能", new Timestamp(new Date().getTime()));
//		Role role4=new Role("学生", "学生功能", new Timestamp(new Date().getTime()));
//		session.save(role1);
//		session.save(role2);
//		session.save(role3);
//		session.save(role4);
		
		// ==============================================================
		// 保存权限数据
//		Privilege menu1, menu2, menu3, menu4, menu5,menu6;
//
//		// --------------------系统管理模块
//		menu1 = new Privilege("系统管理", null, null);
//		menu2 = new Privilege("系统设置", null, menu1);
//		menu3=new Privilege("部门管理", "/department_list", menu2);	
//		menu4= new Privilege("系统日志", "", menu2);
//		menu5=new Privilege("系统功能", null, menu1);
//		menu6=new Privilege("系统功能管理", "", menu5);
//		session.save(menu1);
//		session.save(menu2);
//		session.save(menu3);
//		session.save(menu4);
//		session.save(menu5);
//		session.save(menu6);
//		
//		session.save(new Privilege("部门列表", "/department_list", menu3));
//		session.save(new Privilege("部门删除", "/department_delete", menu3));
//		session.save(new Privilege("部门添加", "/department_add", menu3));
//		session.save(new Privilege("部门修改", "/department_edit", menu3));
//		// --------------------用户权限模块
//		menu1 = new Privilege("用户权限", null, null);
//		menu2=new Privilege("用户管理", null, menu1);
//		menu3=new Privilege("用户管理", "/userDetail_list", menu2);
//		menu4=new Privilege("角色管理", null, menu1);
//		menu5=new Privilege("角色权限管理", "/role_list", menu4);
//		session.save(menu1);
//		session.save(menu2);
//		session.save(menu3);
//		session.save(menu4);
//		session.save(menu5);
//		
//		session.save(new Privilege("用户列表", "/userDetails_list", menu3));
//		session.save(new Privilege("用户删除", "/userDetails_delete", menu3));
//		session.save(new Privilege("用户添加", "/userDetails_add", menu3));
//		session.save(new Privilege("用户修改", "/userDetails_edit", menu3));
//		session.save(new Privilege("初始化密码", "/userDetails_initPassword", menu3));
//		
//		session.save(new Privilege("角色列表", "/role_list", menu5));
//		session.save(new Privilege("角色删除", "/role_delete", menu5));
//		session.save(new Privilege("角色添加", "/role_add", menu5));
//		session.save(new Privilege("角色修改", "/role_edit", menu5));
//		
//		// --------------------作业管理模块
//		menu1 = new Privilege("作业管理", null, null);
//		session.save(menu1);
//		
//		// --------------------教学管理模块
//		menu1 = new Privilege("教学管理", null, null);
//		session.save(menu1);
//		
//		// --------------------问卷管理模块
//		menu1 = new Privilege("问卷管理", null, null);
//		session.save(menu1);
//		
//		// --------------------信息交流模块
//		menu1 = new Privilege("信息交流", null, null);
//		session.save(menu1);
//		
//		// --------------------平台信息模块
//		menu1 = new Privilege("平台信息", null, null);
//		session.save(menu1);
//		
//		// --------------------接口管理模块
//		menu1 = new Privilege("接口管理", null, null);
//		session.save(menu1);
	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();
	}
}
