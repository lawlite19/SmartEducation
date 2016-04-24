package com.hhit.util;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.enterprise.inject.New;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.entity.DataDict;
import com.hhit.entity.DataType;
import com.hhit.entity.Department;
import com.hhit.entity.Privilege;
import com.hhit.entity.Role;
import com.hhit.entity.Teacher;
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
		User user=new User();
		user.setUserNum("admin");
		user.setPassword(DigestUtils.md5Hex("admin"));
		session.save(user);
	
// ==============================================================
		//保存部门
		Department depart1=new Department("淮海工学院",  1, "校级",  null);
		Department depart2=new Department("计算机学院", 2, "院级", depart1);
		Department depart3=new Department("软件工程",    3, "系级",  depart2);
		Department depart4=new Department("网络工程",    3, "系级", depart2);
		Department depart5=new Department("计算机技术",  3, "系级",  depart2);
		Department depart6=new Department("理学院",2, "院级", depart1);
		Department depart7=new Department("数学专业",  3, "系级",  depart6);
		Department depart8=new Department("电气工程",  3, "系级", depart6);
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
//		UserDetails userDetails1=new UserDetails("王晓曦", "2012122710", "女", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart1);
//		UserDetails userDetails2=new UserDetails("test1", "2012122711", "男", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart2);
//		UserDetails userDetails3=new UserDetails("test2", "2012122712", "男", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart3);
//		UserDetails userDetails4=new UserDetails("test3", "2012122713", "女", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart4);
//		UserDetails userDetails5=new UserDetails("test4", "2012122714", "男", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart5);
//		UserDetails userDetails6=new UserDetails("test5", "2012122715", "女", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart6);
//		UserDetails userDetails7=new UserDetails("test6", "2012122716", "男", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart7);
//		UserDetails userDetails8=new UserDetails("test7", "2012122717", "男", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart8);
//		
//		UserDetails userDetails9=new UserDetails("test8", "2012122718", "男", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart1);
//		UserDetails userDetails10=new UserDetails("test9", "2012122719", "男", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart2);
//		UserDetails userDetails11=new UserDetails("test10", "2012122720", "男", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart3);
//		UserDetails userDetails12=new UserDetails("test11", "2012122721", "女", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart4);
//		UserDetails userDetails13=new UserDetails("test12", "2012122722", "男", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart5);
//		UserDetails userDetails14=new UserDetails("test13", "2012122723", "女", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart6);
//		UserDetails userDetails15=new UserDetails("test14", "2012122724", "男", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart7);
//		UserDetails userDetails16=new UserDetails("test15", "2012122725", "男", "857489330@qq.com", "18356784958", "574839038", 12, 5, depart8);
//		session.save(userDetails1);
//		session.save(userDetails2);
//		session.save(userDetails3);
//		session.save(userDetails4);
//		session.save(userDetails5);
//		session.save(userDetails6);
//		session.save(userDetails7);
//		session.save(userDetails8);
//		
//		session.save(userDetails9);
//		session.save(userDetails10);
//		session.save(userDetails11);
//		session.save(userDetails12);
//		session.save(userDetails13);
//		session.save(userDetails14);
//		session.save(userDetails15);
//		session.save(userDetails16);
// ==============================================================
		// 保存用户信息
//		String pwd=DigestUtils.md5Hex("123456");
//		User user1=new User("2012122710", pwd, 1, "管理员",userDetails1);
//		User user2=new User("2012122711", pwd, 1, "负责人", userDetails2);
//		User user3=new User("2012122712", pwd, 1, "负责人", userDetails3);
//		User user4=new User("2012122713", pwd, 1, "教师", userDetails4);
//		User user5=new User("2012122714", pwd, 1, "教师", userDetails5);
//		User user6=new User("2012122715", pwd, 1, "学生", userDetails6);
//		User user7=new User("2012122716", pwd, 1, "学生", userDetails7);
//		User user8=new User("2012122717", pwd, 1, "管理员", userDetails8);
//		
//		User user9=new User("2012122718", pwd, 1, "管理员",userDetails9);
//		User user10=new User("2012122719", pwd, 1, "负责人", userDetails10);
//		User user11=new User("2012122720", pwd, 1, "负责人", userDetails11);
//		User user12=new User("2012122721", pwd, 1, "教师", userDetails12);
//		User user13=new User("2012122722", pwd, 1, "教师", userDetails13);
//		User user14=new User("2012122723", pwd, 1, "学生", userDetails14);
//		User user15=new User("2012122724", pwd, 1, "学生", userDetails15);
//		User user16=new User("2012122725", pwd, 1, "管理员", userDetails16);
//		session.save(user1);
//		session.save(user2);
//		session.save(user3);
//		session.save(user4);
//		session.save(user5);
//		session.save(user6);
//		session.save(user7);
//		session.save(user8);
//		session.save(user9);
//		session.save(user10);
//		session.save(user11);
//		session.save(user12);
//		session.save(user13);
//		session.save(user14);
//		session.save(user15);
//		session.save(user16);
// ==============================================================
		// 保存角色
		Role role1=new Role("管理员", "管理员功能", new Timestamp(new Date().getTime()));
		Role role2=new Role("课程负责人", "课程负责人功能", new Timestamp(new Date().getTime()));
		Role role3=new Role("普通教师", "普通教师功能", new Timestamp(new Date().getTime()));
		Role role4=new Role("学生", "学生功能", new Timestamp(new Date().getTime()));
		session.save(role1);
		session.save(role2);
		session.save(role3);
		session.save(role4);
		
// ==============================================================
		// 保存权限数据
		Privilege menu1, menu2, menu3, menu4, menu5,menu6;

		// --------------------系统管理模块
		menu1 = new Privilege("系统管理", null, null);
		menu2 = new Privilege("系统设置", null, menu1);
		menu3=new Privilege("部门管理", "/department_list", menu2);	
		
		menu4=new Privilege("系统功能", null, menu1);
		menu5=new Privilege("系统功能管理", "/privilege_toPrivilegeUI", menu4);
		menu6= new Privilege("系统日志", "/logFile_list", menu4);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		session.save(menu6);
		
		session.save(new Privilege("部门列表", "/department_list", menu3));
		session.save(new Privilege("部门删除", "/department_delete", menu3));
		session.save(new Privilege("部门添加", "/department_add", menu3));
		session.save(new Privilege("部门修改", "/department_edit", menu3));
		// --------------------用户权限模块
		menu1 = new Privilege("用户权限", null, null);
		menu2=new Privilege("用户管理", null, menu1);
		menu3=new Privilege("教师管理", "/teacher_list", menu2);
		menu4=new Privilege("学生管理", "/student_list", menu2);
		
		menu5=new Privilege("角色管理", null, menu1);
		menu6=new Privilege("角色权限管理", "/role_list", menu5);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		session.save(menu6);
		
		session.save(new Privilege("教师列表", "/teacher_list", menu3));
		session.save(new Privilege("教师删除", "/teacher_delete", menu3));
		session.save(new Privilege("教师添加", "/teacher_add", menu3));
		session.save(new Privilege("教师修改", "/teacher_edit", menu3));
		session.save(new Privilege("初始化密码", "/teacher_initPassword", menu3));
		
		session.save(new Privilege("学生列表", "/student_list", menu4));
		session.save(new Privilege("学生删除", "/student_delete", menu4));
		session.save(new Privilege("学生添加", "/student_add", menu4));
		session.save(new Privilege("学生修改", "/student_edit", menu4));
		session.save(new Privilege("初始化密码", "/student_initPassword", menu4));
		
		session.save(new Privilege("角色列表", "/role_list", menu5));
		session.save(new Privilege("角色删除", "/role_delete", menu5));
		session.save(new Privilege("角色添加", "/role_add", menu5));
		session.save(new Privilege("角色修改", "/role_edit", menu5));
		
		// --------------------作业管理模块
		menu1 = new Privilege("作业管理", null, null);
		menu2=new Privilege("作业菜单", null, menu1);
		session.save(menu1);
		session.save(menu2);
		
		
		// --------------------教学管理模块
		menu1=new Privilege("教学管理", null, null);
		menu2=new Privilege("教学管理菜单", null, menu1);
		menu3=new Privilege("课程管理", "/course_list", menu2);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);

		session.save(new Privilege("课程列表", "/course_list", menu3));
		session.save(new Privilege("课程删除", "/course_delete", menu3));
		session.save(new Privilege("课程添加", "/course_add", menu3));
		session.save(new Privilege("课程修改", "/course_edit", menu3));
		
		
		// --------------------问卷管理模块
		menu1 = new Privilege("问卷管理", null, null);
		session.save(menu1);
		
		
		// --------------------平台信息模块
		menu1 = new Privilege("平台信息", null, null);
		session.save(menu1);
		
		// --------------------接口管理模块
		menu1 = new Privilege("接口管理", null, null);
		session.save(menu1);
// ==============================================================
		// 保存数据字典
		DataType dataType1=new DataType("001","教学方式");
		session.save(dataType1);
		session.save(new DataDict("001","讲课",dataType1));
		session.save(new DataDict("002","实验",dataType1));
		session.save(new DataDict("003","大作业",dataType1));
		session.save(new DataDict("004","小测验",dataType1));
		session.save(new DataDict("005","课程讨论",dataType1));
		session.save(new DataDict("006","复习",dataType1));
	}
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();
	}
}
