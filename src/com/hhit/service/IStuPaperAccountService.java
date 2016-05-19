package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.entity.StuPaperAccount;

public interface IStuPaperAccountService extends IDaoSupport<StuPaperAccount>{

	//根据学号查找
	StuPaperAccount findByStuNum(String stuNum);
	//根据学号和课程查找
	StuPaperAccount findByStuNumAndCourse(String stuNum, Course courseFind);
	//根据课程和班级查找
	List<StuPaperAccount> findByCourseAndClass(Course courseFind, Class_ classFind);

}
