package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.SpiderCourse;
import com.hhit.entity.SpiderCourseInfo;

public interface ISpiderCourseInfoService extends IDaoSupport<SpiderCourseInfo>{

	//根据课程查找课程信息
	SpiderCourseInfo findByCourse(SpiderCourse courseFind);
}
