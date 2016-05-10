package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Teacher;
import com.hhit.service.ITeacherService;

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


}
