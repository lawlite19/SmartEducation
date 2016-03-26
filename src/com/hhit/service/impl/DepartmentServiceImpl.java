package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Department;
import com.hhit.service.IDepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements IDepartmentService {

	@Override
	public List<Department> findTopList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findChildren(Integer parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
