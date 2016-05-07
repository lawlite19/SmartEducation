package com.hhit.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.hhit.entity.*;

public class ClassPropertyFilter {
	//学生过滤
	public static void StudentFilter(Map<String,Object> map,Student stu){
		String[] stuProperty={"role","department","class_"};
		map.put("student", JsonUtil.jsonFilter(stu, stuProperty));
	}
	//老师过滤
	public static void TeacherFilter(Map<String,Object> map,Teacher tea){
		String[] teaProperty={"department","roles","courses"};
		map.put("student", JsonUtil.jsonFilter(tea, teaProperty));
	}
	//部门过滤
	public static void DepartmentFilter(Map<String,Object> map,Department dept){
		String[] deptProperty={"children","parent","students","teachers","classes","courses"};
		map.put("department", JsonUtil.jsonFilter(dept, deptProperty));
	}
	//班级过滤
	public static void ClassFilter(Map<String,Object> map,Class_ class_){
		String[] classProperty={"department","students"};
		map.put("class_", JsonUtil.jsonFilter(class_, classProperty));
	}
	//角色list过滤
	public static void ListRoleFilter(Map<String,Object> map,List<Role> roles){
		String[] roleProperty={"privileges","teachers"};
		map.put("roles", JsonUtil.jsonListFilter(roles, roleProperty));
	}
	//课程list过滤
	public static void ListCourseFilter(Map<String,Object> map,List<Course> courses){
		String[] roleProperty={"departments","teachers"};
		map.put("courses", JsonUtil.jsonListFilter(courses, roleProperty));
	}

	
}
