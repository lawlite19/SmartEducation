package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.ClassSelectCourse;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.service.IClassSelectCourseService;

@Service
@Transactional
public class ClassSelectCourseServiceImpl extends DaoSupportImpl<ClassSelectCourse> implements IClassSelectCourseService{

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassSelectCourse> findByTeacherNumAndCourse(String teaNum, Course courseFind) {
		return getSession().createQuery("FROM ClassSelectCourse WHERE teacherNum=? AND course=?")//
				.setParameter(0, teaNum)//
				.setParameter(1, courseFind)//
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassSelectCourse> findByClass(Class_ classFind) {
		return getSession().createQuery("FROM ClassSelectCourse c WHERE c.class_=?")//
				.setParameter(0, classFind)//
				.list();
	}

	@Override
	public ClassSelectCourse findByClassAndCourse(Class_ classFind, Course courseFind) {
		return (ClassSelectCourse) getSession().createQuery("FROM ClassSelectCourse WHERE class_=? AND course=?")//
				.setParameter(0, classFind)//
				.setParameter(1, courseFind)//
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassSelectCourse> findByTeacherNum(String teaNum) {
		return getSession().createQuery("FROM ClassSelectCourse WHERE teacherNum=?")//
				.setParameter(0, teaNum)//
				.list();
	}

}