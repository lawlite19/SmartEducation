package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.CourseScore;
import com.hhit.entity.SpiderCourse;

public interface ICourseScoreService extends IDaoSupport<CourseScore>{

	//根据学号、课程查找
	CourseScore findByStuNumAndCourse(String stuNum,
			SpiderCourse spiderCourseFind);

}
