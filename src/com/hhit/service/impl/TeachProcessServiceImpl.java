package com.hhit.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Chapter;
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

	@Override
	public List<TeachProcess> findByChapterAndTeaAndCourse(Chapter chapterFind, Teacher teaFind, Course courseFind) {
		return getSession().createQuery("FROM TeachProcess WHERE chapter=? AND teacher=? AND course=?")//
				.setParameter(0, chapterFind)//
				.setParameter(1, teaFind)//
				.setParameter(2, courseFind)//
				.list();
	}

	@Override
	public List<TeachProcess> findByTeacherAndCourseAndTime(Teacher teaFind,Course courseFind, Timestamp nowTime) {
		return getSession().createQuery(//
				"FROM TeachProcess t WHERE t.teacher=? AND t.course=? AND t.startData>:time AND t.pushTime<:time")//
				.setParameter(0, teaFind)//
				.setParameter(1, courseFind)//
				.setDate("time", nowTime)//
				.setDate("time", nowTime)//
				.list();
		
	}

}
