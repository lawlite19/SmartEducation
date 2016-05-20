package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.entity.PageBean;
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
	public Long findByCourseAndChapterList(Course courseFind, List<Chapter> chapterList) {
		return (Long) getSession().createQuery("SELECT COUNT(*) FROM SingleChoice WHERE course=? AND chapter IN (:chapterList)")//
				.setParameter(0, courseFind)//
				.setParameterList("chapterList", chapterList)//
				.uniqueResult();
	}

	@Override
	public PageBean getPageBeanByInList(Course courseFind,int randomSingle, int pageSize,int recordSingleCount, List<Chapter> chapterList) {
		List recordList=getSession().createQuery("FROM SingleChoice WHERE course=? AND chapter IN (:chapterList)")//
				.setParameter(0, courseFind)//
				.setParameterList("chapterList", chapterList)//
				.setFirstResult((randomSingle-1)*pageSize)//
				.setMaxResults(pageSize)//
				.list();
		return new PageBean(randomSingle, pageSize, recordSingleCount, recordList);
	}

}
