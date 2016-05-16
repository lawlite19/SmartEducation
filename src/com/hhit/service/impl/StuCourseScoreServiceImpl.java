package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.DataDict;
import com.hhit.entity.StuCourseScore;
import com.hhit.service.IStuCourseScoreService;

@Service
@Transactional
public class StuCourseScoreServiceImpl extends DaoSupportImpl<StuCourseScore> implements IStuCourseScoreService{

	@SuppressWarnings("unchecked")
	@Override
	public List<StuCourseScore> findByStuNumAndTerm(String stuNum, DataDict termFind) {
		return getSession().createQuery("FROM StuCourseScore WHERE stuNum=? AND dataDict=?")//
				.setParameter(0, stuNum)//
				.setParameter(1, termFind)//
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StuCourseScore> findByStuNumAndTerms(String stuNum, List<DataDict> termList) {
		return getSession().createQuery("FROM StuCourseScore WHERE stuNum=? AND dataDict IN (:termList)")//
				.setParameter(0, stuNum)//
				.setParameterList("termList", termList)//
				.list();
	}

}
