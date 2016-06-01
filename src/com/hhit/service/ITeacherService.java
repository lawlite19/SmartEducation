package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Department;
import com.hhit.entity.Teacher;

public interface ITeacherService extends IDaoSupport<Teacher>{

	//根据老师工号查找
	Teacher findByTeacherNum(String teacherNum);

	//根据部门查找
	List<Teacher> findByDept(Department deptFind);

}
