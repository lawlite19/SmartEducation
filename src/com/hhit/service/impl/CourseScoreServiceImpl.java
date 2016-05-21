package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.CourseScore;
import com.hhit.entity.SpiderCourse;
import com.hhit.service.ICourseScoreService;

@Service
@Transactional
public class CourseScoreServiceImpl extends DaoSupportImpl<CourseScore> implements ICourseScoreService{

	@Override
	public CourseScore findByStuNumAndCourse(String stuNum,
			SpiderCourse spiderCourseFind) {
		return (CourseScore) getSession().createQuery("FROM CourseScore WHERE stuNum=? AND spiderCourse=?")//
				.setParameter(0, stuNum)//
				.setParameter(1, spiderCourseFind)//
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseScore> findByCourse(SpiderCourse courseFind) {
		return getSession().createQuery("FROM CourseScore WHERE spiderCourse=?")//
				.setParameter(0, courseFind)//
				.list();
	}
	
}
