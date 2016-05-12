package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Judgement;
import com.hhit.entity.SingleChoice;
import com.hhit.entity.TestQuestion;

public interface ITestQuestionService extends IDaoSupport<TestQuestion>{

	//根据单选删除
	void deleteBySingleChoice(SingleChoice singleFind);
	//根据判断删除
	void deleteByJudgement(Judgement judgeFind);

}
