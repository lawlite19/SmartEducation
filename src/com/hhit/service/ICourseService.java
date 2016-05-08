package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Course;
import com.hhit.entity.Department;
import com.hhit.entity.UserDetails;

public interface ICourseService extends IDaoSupport<Course>{

	//根据当前登录用户的详细信息查找对应课程
	List<Course> findByUser(UserDetails userDetails);

	//根据课程名查找
	Course findByCourseName(String string);

}
