package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.entity.Judgement;
import com.hhit.entity.PageBean;
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
	public Long findByCourseAndChapterList(Course courseFind, List<Chapter> chapterList) {
		return (Long) getSession().createQuery("SELECT COUNT(*) FROM Judgement WHERE course=? AND chapter IN (:chapterList)")//
				.setParameter(0, courseFind)//
				.setParameterList("chapterList", chapterList)//
				.uniqueResult();
	}

	@Override
	public PageBean getPageBeanByInList(Course courseFind,int random, int pageSize,int recordCount,List<Chapter> chapterList) {
		List list=getSession().createQuery("FROM Judgement WHERE course=? AND chapter IN (:chapterList)")
					.setParameter(0, courseFind)//
					.setParameterList("chapterList", chapterList)
					.setFirstResult((random-1)*pageSize)//
					.setMaxResults(pageSize)//
					.list();
		return new PageBean(random, pageSize, recordCount, list);
	}
	

}
