package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.CourseDiscuss;
import com.hhit.entity.SpiderCourse;

public interface ICourseDiscussService extends IDaoSupport<CourseDiscuss>{

	//根据课程查找评论
	List<CourseDiscuss> findByCourse(SpiderCourse courseFind);

}
