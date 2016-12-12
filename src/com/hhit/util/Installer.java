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
import com.hhit.entity.Student;
import com.hhit.entity.Teacher;
import com.hhit.entity.User;
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
		user.setUserType("老师");
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
		// 保存学生信息
		Student stu1=new Student("2012122710", "王晓曦", "女", "2015");
		Student stu2=new Student("2012122711", "test1", "男", "2014");
		Student stu3=new Student("2012122712", "test2", "女", "2015");
		Student stu4=new Student("2012122713", "test3", "女", "2015");
		Student stu5=new Student("2012122714", "test4", "女", "2013");
		Student stu6=new Student("2012122715", "test5", "男", "2015");
		Student stu7=new Student("2012122716", "test6", "女", "2015");
		Student stu8=new Student("2012122717", "test7", "女", "2012");
		Student stu9=new Student("2012122718", "test8", "女", "2015");
		Student stu10=new Student("2012122719", "test10", "男", "2013");
		Student stu11=new Student("2012122720", "test11", "女", "2015");
		Student stu12=new Student("2012122721", "test12", "女", "2015");
		Student stu13=new Student("2012122722", "test13", "男", "2015");
		Student stu14=new Student("2012122723", "test14", "女", "2015");
		Student stu15=new Student("2012122724", "test15", "女", "2015");
		session.save(stu1);
		session.save(stu2);
		session.save(stu3);
		session.save(stu4);
		session.save(stu5);
		session.save(stu6);
		session.save(stu7);
		session.save(stu8);
		
		session.save(stu9);
		session.save(stu10);
		session.save(stu11);
		session.save(stu12);
		session.save(stu13);
		session.save(stu14);
		session.save(stu15);
// ==============================================================
		//保存老师信息
		Teacher tea1=new Teacher("01001", "teaTest1");
		Teacher tea2=new Teacher("01002", "teaTest2");
		Teacher tea3=new Teacher("01003", "teaTest3");
		Teacher tea4=new Teacher("01004", "teaTest4");
		Teacher tea5=new Teacher("01005", "teaTest5");
		Teacher tea6=new Teacher("01006", "teaTest6");
		Teacher tea7=new Teacher("01007", "teaTest7");
		Teacher tea8=new Teacher("01008", "teaTest8");
		Teacher tea9=new Teacher("01009", "teaTest9");
		Teacher tea10=new Teacher("01010", "teaTest10");
		Teacher tea11=new Teacher("01011", "teaTest11");
		session.save(tea1);
		session.save(tea2);
		session.save(tea3);
		session.save(tea4);
		session.save(tea5);
		session.save(tea6);
		session.save(tea7);
		session.save(tea8);
		session.save(tea9);
		session.save(tea10);
		session.save(tea11);
// ==============================================================
		// 保存用户信息
		String pwd=DigestUtils.md5Hex("123456");
		User user1=new User("2012122710", pwd, "学生",stu1,null);
		User user2=new User("2012122711", pwd, "学生", stu2,null);
		User user3=new User("2012122712", pwd, "学生", stu3,null);
		User user4=new User("2012122713", pwd, "学生", stu4,null);
		User user5=new User("2012122714", pwd, "学生", stu5,null);
		User user6=new User("2012122715", pwd, "学生", stu6,null);
		User user7=new User("2012122716", pwd, "学生", stu7,null);
		User user8=new User("2012122717", pwd, "学生", stu8,null);
		
		User user9=new User("2012122718", pwd, "学生",stu9,null);
		User user10=new User("2012122719", pwd, "学生", stu10,null);
		User user11=new User("2012122720", pwd, "学生", stu11,null);
		User user12=new User("2012122721", pwd, "学生", stu12,null);
		User user13=new User("2012122722", pwd, "学生", stu13,null);
		User user14=new User("2012122723", pwd, "学生", stu14,null);
		User user15=new User("2012122724", pwd, "学生", stu15,null);
		session.save(user1);
		session.save(user2);
		session.save(user3);
		session.save(user4);
		session.save(user5);
		session.save(user6);
		session.save(user7);
		session.save(user8);
		session.save(user9);
		session.save(user10);
		session.save(user11);
		session.save(user12);
		session.save(user13);
		session.save(user14);
		session.save(user15);
		
		session.save(new User("01001", pwd, "老师", null, tea1));
		session.save(new User("01002", pwd, "老师", null, tea2));
		session.save(new User("01003", pwd, "老师", null, tea3));
		session.save(new User("01004", pwd, "老师", null, tea4));
		session.save(new User("01005", pwd, "老师", null, tea5));
		session.save(new User("01006", pwd, "老师", null, tea6));
		session.save(new User("01007", pwd, "老师", null, tea7));
		session.save(new User("01008", pwd, "老师", null, tea8));
		session.save(new User("01009", pwd, "老师", null, tea9));
		session.save(new User("010010", pwd, "老师", null, tea10));
		session.save(new User("010011", pwd, "老师", null, tea11));
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
		Privilege menu1, menu2, menu3, menu4, menu5,menu6,menu7;

		// --------------------系统管理模块
		menu1 = new Privilege("系统管理", null, null);
		menu2 = new Privilege("系统设置", null, menu1);
		menu3=new Privilege("部门管理", "/department_list", menu2);
		menu7=new Privilege("班级管理", "/mclass_list", menu2);
		
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
		
		session.save(new Privilege("班级列表", "/mclass_list", menu7));
		session.save(new Privilege("班级删除", "/mclass_delete", menu7));
		session.save(new Privilege("班级添加", "/mclass_add", menu7));
		session.save(new Privilege("班级修改", "/mclass_edit", menu7));
		
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
		
		session.save(new Privilege("角色列表", "/role_list", menu6));
		session.save(new Privilege("角色删除", "/role_delete", menu6));
		session.save(new Privilege("角色添加", "/role_add", menu6));
		session.save(new Privilege("角色修改", "/role_edit", menu6));
		
//		// --------------------作业管理模块
//		menu1 = new Privilege("作业管理", null, null);
//		menu2=new Privilege("作业菜单", null, menu1);
//		session.save(menu1);
//		session.save(menu2);
		
		
//		// --------------------教学管理模块
//		menu1=new Privilege("教学管理", null, null);
//		menu2=new Privilege("教学管理菜单", null, menu1);
//		menu3=new Privilege("课程管理", "/course_list", menu2);
//		session.save(menu1);
//		session.save(menu2);
//		session.save(menu3);
//
//		session.save(new Privilege("课程列表", "/course_list", menu3));
//		session.save(new Privilege("课程删除", "/course_delete", menu3));
//		session.save(new Privilege("课程添加", "/course_add", menu3));
//		session.save(new Privilege("课程修改", "/course_edit", menu3));
		
//		
//		// --------------------问卷管理模块
//		menu1 = new Privilege("问卷管理", null, null);
//		session.save(menu1);
//		
//		
//		// --------------------平台信息模块
//		menu1 = new Privilege("平台信息", null, null);
//		session.save(menu1);
//		
//		// --------------------接口管理模块
//		menu1 = new Privilege("接口管理", null, null);
//		session.save(menu1);
// ==============================================================
//		// 保存数据字典
//		DataType dataType1=new DataType("001","教学方式");
//		session.save(dataType1);
//		session.save(new DataDict("001","讲课",dataType1));
//		session.save(new DataDict("002","实验",dataType1));
//		session.save(new DataDict("003","大作业",dataType1));
//		session.save(new DataDict("004","小测验",dataType1));
//		session.save(new DataDict("005","课程讨论",dataType1));
//		session.save(new DataDict("006","复习",dataType1));
	}
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();
	}
}
