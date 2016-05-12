package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Judgement;
import com.hhit.entity.SingleChoice;
import com.hhit.entity.TestQuestion;
import com.hhit.service.ITestQuestionService;

@Service
@Transactional
public class TestQuestionServiceImpl extends DaoSupportImpl<TestQuestion> implements ITestQuestionService{

	@Override
	public void deleteBySingleChoice(SingleChoice singleFind) {
		 getSession().createQuery("DELETE FROM TestQuestion WHERE singleChoice=?")//
		.setParameter(0, singleFind)//
		.executeUpdate();
	}

	@Override
	public void deleteByJudgement(Judgement judgeFind) {
		getSession().createQuery("DELETE FROM TestQuestion WHERE judgement=?")//
		.setParameter(0, judgeFind)//
		.executeUpdate();
	}

}
