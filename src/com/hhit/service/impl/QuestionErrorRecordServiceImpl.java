package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.QuestionErrorRecord;
import com.hhit.service.IQuestionErrorRecordService;

@Service
@Transactional
public class QuestionErrorRecordServiceImpl extends DaoSupportImpl<QuestionErrorRecord> implements IQuestionErrorRecordService{

}
