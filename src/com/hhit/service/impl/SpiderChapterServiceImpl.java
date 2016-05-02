package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.SpiderChapter;
import com.hhit.entity.SpiderCourse;
import com.hhit.service.ISpiderChapterService;

@Service
@Transactional
public class SpiderChapterServiceImpl extends DaoSupportImpl<SpiderChapter> implements ISpiderChapterService{

	@SuppressWarnings("unchecked")
	@Override
	public List<SpiderChapter> findByCourse(SpiderCourse courseFind) {
		return getSession().createQuery("FROM SpiderChapter WHERE spiderCourse=?")//
				.setParameter(0, courseFind)//
				.list();
	}
	
}
