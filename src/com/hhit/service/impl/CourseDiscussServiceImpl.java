package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.CourseDiscuss;
import com.hhit.entity.SpiderCourse;
import com.hhit.service.ICourseDiscussService;
@Service
@Transactional
public class CourseDiscussServiceImpl extends DaoSupportImpl<CourseDiscuss> implements ICourseDiscussService{

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseDiscuss> findByCourse(SpiderCourse courseFind) {
		return getSession().createQuery("FROM CourseDiscuss WHERE spiderCourse=?")//
				.setParameter(0, courseFind)//
				.list();
	}

}
