package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.StuQuestion;
import com.hhit.entity.TeaAnswer;

public interface ITeaAnswerService extends IDaoSupport<TeaAnswer>{

	//根据问题找回答
	List<TeaAnswer> findByQuestion(StuQuestion stuQuestionFind);

}
