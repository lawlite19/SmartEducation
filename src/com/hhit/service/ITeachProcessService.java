package com.hhit.service;

import java.sql.Timestamp;
import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.entity.TeachProcess;
import com.hhit.entity.Teacher;

public interface ITeachProcessService extends IDaoSupport<TeachProcess>{

	//根据课程查找教学进程
	List<TeachProcess> findByTeacherAndCourse(Teacher teaFind, Course courseFind);

	//根据章节、老师、课程查询
	List<TeachProcess> findByChapterAndTeaAndCourse(Chapter chapterFind,
			Teacher teaFind, Course courseFind);

	//根据老师、课程、时间查询符合的
	List<TeachProcess> findByTeacherAndCourseAndTime(Teacher teaFind,
			Course courseFind, Timestamp nowTime);

}
