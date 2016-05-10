package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Teacher;

public interface ITeacherService extends IDaoSupport<Teacher>{

	//根据老师工号查找
	Teacher findByTeacherNum(String teacherNum);

}
