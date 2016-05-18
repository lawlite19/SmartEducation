package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.StuQuestion;
import com.hhit.entity.TeaAnswer;
import com.hhit.service.ITeaAnswerService;

@Service
@Transactional
public class TeaAnwerServiceImpl extends DaoSupportImpl<TeaAnswer> implements ITeaAnswerService{

	@SuppressWarnings("unchecked")
	@Override
	public List<TeaAnswer> findByQuestion(StuQuestion stuQuestionFind) {
		return getSession().createQuery("FROM TeaAnswer WHERE stuQuestion=?")//
				.setParameter(0, stuQuestionFind)//
				.list();
	}

}
