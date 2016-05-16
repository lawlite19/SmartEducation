package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.entity.SingleChoice;
import com.hhit.service.ISingleChoiceService;

@Service
@Transactional
public class SingleChoiceServiceImpl extends DaoSupportImpl<SingleChoice> implements ISingleChoiceService{

	@Override
	public SingleChoice findMaxRecord() {
		return (SingleChoice) getSession().createQuery("FROM SingleChoice ORDER BY id DESC")//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

	@Override
	public SingleChoice findMinRecord() {
		return (SingleChoice) getSession().createQuery("FROM SingleChoice")//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

	@Override
	public Long findByCourseAndChapter(Course courseFind, Chapter chapterFind) {
		return (Long) getSession().createQuery("SELECT COUNT(*) FROM SingleChoice WHERE course=? AND chapter=?")//
				.setParameter(0, courseFind)//
				.setParameter(1, chapterFind)//
				.uniqueResult();
	}

}
