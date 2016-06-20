package com.hhit.util;

import java.util.List;
import java.util.Map;

import com.hhit.entity.Chapter;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.entity.Department;
import com.hhit.entity.Role;
import com.hhit.entity.SpiderChapter;
import com.hhit.entity.SpiderCourse;
import com.hhit.entity.SpiderCourseInfo;
import com.hhit.entity.SpiderDocument;
import com.hhit.entity.SpiderProfessionType;
import com.hhit.entity.Student;
import com.hhit.entity.Teacher;
import com.hhit.entity.TestRoom;

public class ClassPropertyFilter {
//实体类过滤
//======================================
	//学生过滤
	public static void StudentFilter(Map<String,Object> map,Student stu){
		String[] stuProperty={"role","department","class_"};
		map.put("student", JsonUtil.jsonFilter(stu, stuProperty));
	}
	//老师过滤
	public static void TeacherFilter(Map<String,Object> map,Teacher tea){
		String[] teaProperty={"department","roles","courses"};
		map.put("teacher", JsonUtil.jsonFilter(tea, teaProperty));
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
	//爬取课程信息过滤
	public static void SpiderCourseInfoFilter(Map<String,Object> map,SpiderCourseInfo spiderCourseInfo){
		String[] spiderCourseInfoProperty={"spiderCourse"};
		map.put("spiderCourseInfo", JsonUtil.jsonFilter(spiderCourseInfo, spiderCourseInfoProperty));
	}
	
	
//list过滤
//==============================================
	//角色list过滤
	public static void ListRoleFilter(Map<String,Object> map,List<Role> roles){
		String[] roleProperty={"privileges","teachers"};
		map.put("roles", JsonUtil.jsonListFilter(roles, roleProperty));
	}
	//课程list过滤
	public static void ListCourseFilter(Map<String,Object> map,List<Course> courses){
		String[] courseProperty={"departments","teachers"};
		map.put("courses", JsonUtil.jsonListFilter(courses, courseProperty));
	}
	//章节list过滤
	public static void ListChapterFilter(Map<String,Object> map,List<Chapter> chapters){
		String[] chapterProperty={"course"};
		map.put("chapters", JsonUtil.jsonListFilter(chapters, chapterProperty));
	}
	//班级list过滤
	public static void ListClassFilter(Map<String,Object> map,List<Class_> classList){
		String[] classProperty={"department","students"};
		map.put("classes", JsonUtil.jsonListFilter(classList, classProperty));
	}
	//爬虫专业类型list过滤
	public static void ListSpiderProfessTypeFilter(Map<String,Object> map,List<SpiderProfessionType> spiderProfessTypeList){
		String[] spiderProfessionTypeProperty={""};
		map.put("spiderProfessionTypes", JsonUtil.jsonListFilter(spiderProfessTypeList, spiderProfessionTypeProperty));
	}
	//爬虫课程list过滤
	public static void ListSpiderCourseFilter(Map<String,Object> map,List<SpiderCourse> spiderCourseList){
		String[] spiderCourseProperty={"spiderChapters","spiderDocuemnts","professionType"};
		map.put("spiderCourses", JsonUtil.jsonListFilter(spiderCourseList, spiderCourseProperty));
	}
	//爬取课程章节list过滤
	public static void ListSpiderChapterFilter(Map<String,Object> map,List<SpiderChapter> spiderChapterList){
		String[] spiderChapterProperty={"spiderCourse","parent"};
		map.put("spiderChapters", JsonUtil.jsonListFilter(spiderChapterList, spiderChapterProperty));
	}
	//爬取参考教材list过滤
	public static void ListSpiderDocumentrFilter(Map<String,Object> map,List<SpiderDocument> spiderDocumentList){
		String[] spiderDocumentProperty={"spiderCourse"};
		map.put("spiderDocuments", JsonUtil.jsonListFilter(spiderDocumentList, spiderDocumentProperty));
	}

	/**
	 * 考场类过滤
	 * map为将要放入Json的对象
	 * testRooms为需要过滤的变量
	 * Property为将要除去的属性名
	 * */
	public static void ListTestRoomFilter(Map<String, Object> map, List<TestRoom> testRooms,String[] Property) {
		map.put("data", JsonUtil.jsonListFilter(testRooms, Property));
	}
}
