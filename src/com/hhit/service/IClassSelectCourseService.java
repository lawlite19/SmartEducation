package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.ClassSelectCourse;
import com.hhit.entity.Course;

public interface IClassSelectCourseService extends IDaoSupport<ClassSelectCourse>{

	//根据老师工号和课程查询
	List<ClassSelectCourse> findByTeacherNumAndCourse(String teaNum,
			Course courseFind);

}
