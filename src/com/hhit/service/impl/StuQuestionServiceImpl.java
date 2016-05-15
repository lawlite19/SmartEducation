package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.StuQuestion;
import com.hhit.service.IStuQuestionService;

@Service
@Transactional
public class StuQuestionServiceImpl extends DaoSupportImpl<StuQuestion> implements IStuQuestionService{

	@SuppressWarnings("unchecked")
	@Override
	public List<StuQuestion> findByStuNum(String stuNum) {
		return getSession().createQuery("FROM StuQuestion WHERE stuNum=?")//
				.setParameter(0, stuNum)//
				.list();
	}

}
