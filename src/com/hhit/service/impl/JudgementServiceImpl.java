package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.entity.Judgement;
import com.hhit.service.IJudgementService;

@Service
@Transactional
public class JudgementServiceImpl extends DaoSupportImpl<Judgement> implements IJudgementService{

	@Override
	public Judgement findMaxRecord() {
		return  (Judgement) getSession().createQuery("FROM Judgement ORDER BY id DESC")//
		.setFirstResult(0)//
		.setMaxResults(1)//
		.uniqueResult();
	}

	@Override
	public Judgement findMinRecord() {
		return (Judgement) getSession().createQuery("FROM Judgement")//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

	@Override
	public Long findByCourseAndChapter(Course courseFind, Chapter chapterFind) {
		return (Long) getSession().createQuery("SELECT COUNT(*) FROM Judgement WHERE course=? AND chapter=?")//
				.setParameter(0, courseFind)//
				.setParameter(1, chapterFind)//
				.uniqueResult();
	}
	

}
