package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.CourseScore;
import com.hhit.entity.SpiderCourse;

public interface ICourseScoreService extends IDaoSupport<CourseScore>{

	//根据学号、课程查找
	CourseScore findByStuNumAndCourse(String stuNum,
			SpiderCourse spiderCourseFind);

	//根据课程查找
	List<CourseScore> findByCourse(SpiderCourse courseFind);

}
