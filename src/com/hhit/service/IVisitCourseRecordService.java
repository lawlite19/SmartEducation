package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.SpiderCourse;
import com.hhit.entity.Student;
import com.hhit.entity.VisitCourseRecord;

public interface IVisitCourseRecordService extends IDaoSupport<VisitCourseRecord>{

	//根据学生和课程查找
	VisitCourseRecord findByStuAndCourse(Student stuFind, SpiderCourse courseFind);

}
