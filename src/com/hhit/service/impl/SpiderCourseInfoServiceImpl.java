package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.SpiderCourse;
import com.hhit.entity.SpiderCourseInfo;
import com.hhit.service.ISpiderCourseInfoService;

@Service
@Transactional
public class SpiderCourseInfoServiceImpl extends DaoSupportImpl<SpiderCourseInfo> implements ISpiderCourseInfoService{

	@Override
	public SpiderCourseInfo findByCourse(SpiderCourse courseFind) {
		return (SpiderCourseInfo) getSession().createQuery("FROM SpiderCourseInfo WHERE spiderCourse=?")//
		.setParameter(0, courseFind)//
		.uniqueResult();
	}

}
