package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Department;

public interface IDepartmentService extends IDaoSupport<Department>{

	//查找顶级部门
	List<Department> findTopList();
	//查找子级部门
	List<Department> findChildren(Integer parentId);
	//根据部门级别查找
	List<Department> findByDeptLevel(int i);

}
