package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.entity.TestPaper;

public interface ITestPaperService extends IDaoSupport<TestPaper>{

	//根据班级和课程查找
	List<TestPaper> findByClassAndCourse(Class_ classFind, Course courseFind);
	//根据老师工号和课程查找
	List<TestPaper> findByTeaNumAndCourse(String teaNum, Course courseFind);
	//根据班级查找
	List<TestPaper> findByClassAndCourse(Class_ classFind);

}
