package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Class_;
import com.hhit.entity.Department;

public interface IClassService extends IDaoSupport<Class_>{
	//根据部门查找班级，用于动态加载班级select
	List<Class_> findByDept(Department deptFind);

}
