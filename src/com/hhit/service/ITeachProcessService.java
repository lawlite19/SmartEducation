package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Course;
import com.hhit.entity.TeachProcess;
import com.hhit.entity.Teacher;

public interface ITeachProcessService extends IDaoSupport<TeachProcess>{

	//根据课程查找教学进程
	List<TeachProcess> findByTeacherAndCourse(Teacher teaFind, Course courseFind);

}
