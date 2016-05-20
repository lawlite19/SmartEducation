package com.hhit.service;

import java.sql.Timestamp;
import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.entity.TestPaper;

public interface ITestPaperService extends IDaoSupport<TestPaper>{

	//根据班级和课程查找--降序
	List<TestPaper> findByClassAndCourse(Class_ classFind, Course courseFind, Timestamp nowTime);
	//根据老师工号和课程查找
	List<TestPaper> findByTeaNumAndCourse(String teaNum, Course courseFind);
	//根据班级查找
	List<TestPaper> findByClass(Class_ classFind, Timestamp nowTime);
	//根据老师工号查找--id倒序
	List<TestPaper> findByTeaNum(String teaNum);
	//根据班级和课程查找---升序
	List<TestPaper> findByClassAndCourseASC(Class_ classFind, Course courseFind);
}
