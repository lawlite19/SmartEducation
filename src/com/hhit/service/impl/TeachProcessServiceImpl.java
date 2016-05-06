package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Course;
import com.hhit.entity.TeachProcess;
import com.hhit.entity.Teacher;
import com.hhit.service.ITeachProcessService;
@SuppressWarnings("unchecked")
@Service
@Transactional
public class TeachProcessServiceImpl extends DaoSupportImpl<TeachProcess> implements ITeachProcessService{


	@Override
	public List<TeachProcess> findByTeacherAndCourse(Teacher teaFind, Course courseFind) {
		return getSession().createQuery("FROM TeachProcess WHERE teacher=? AND course=?")//
				.setParameter(0, teaFind)//
				.setParameter(1, courseFind)//
				.list();
	}

}
