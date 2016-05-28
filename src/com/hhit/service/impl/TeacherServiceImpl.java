package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Department;
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
	public List<Teacher> findByLead(String num,Department department) {
		@SuppressWarnings("unchecked")
		List<Teacher> teacherlist=getSession()
				.createQuery("select t from Teacher t where t.department=:dep and t.teaNum<>:num" )
				.setParameter("dep", department).setParameter("num", num).list();
		return teacherlist;
	}

	@Override
	public Teacher findByTeaNum(String userNum) {
		Teacher teacher=(Teacher)getSession().
				createQuery("select t from Teacher t where t.teaNum=?").
				setParameter(0, userNum).uniqueResult();
		return teacher;
	}


}
