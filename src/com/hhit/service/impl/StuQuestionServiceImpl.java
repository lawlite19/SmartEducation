package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.StuQuestion;
import com.hhit.service.IStuQuestionService;

@Service
@Transactional
public class StuQuestionServiceImpl extends DaoSupportImpl<StuQuestion> implements IStuQuestionService{

}
