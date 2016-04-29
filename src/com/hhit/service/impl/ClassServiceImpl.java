package com.hhit.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Class_;
import com.hhit.entity.Department;
import com.hhit.service.IClassService;
@SuppressWarnings("unchecked")
@Service
@Transactional
public class ClassServiceImpl extends DaoSupportImpl<Class_> implements IClassService{

	
	@Override
	public List<Class_> findByDept(Department deptFind) {
		if(deptFind==null)
			return Collections.EMPTY_LIST;
		return getSession().createQuery("FROM Class_ WHERE department=?")//
		.setParameter(0, deptFind)//
		.list();
	}

}
