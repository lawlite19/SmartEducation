package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Department;
import com.hhit.entity.Teacher;
import com.hhit.service.ITeacherService;
@SuppressWarnings("unchecked")
@Service
@Transactional
public class TeacherServiceImpl extends DaoSupportImpl<Teacher> implements
		ITeacherService {

	@Override
	public Teacher findByTeacherNum(String teacherNum) {
		return (Teacher) getSession().createQuery("FROM Teacher WHERE teaNum=?")//
		.setParameter(0, teacherNum)//
		.uniqueResult();
		
	}

	
	@Override
	public List<Teacher> findByDept(Department deptFind) {
		return getSession().createQuery("FROM Teacher WHERE department=?")//
				.setParameter(0, deptFind)//
				.list();
	}
}
