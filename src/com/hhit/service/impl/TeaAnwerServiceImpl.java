package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.TeaAnswer;
import com.hhit.service.ITeaAnswerService;

@Service
@Transactional
public class TeaAnwerServiceImpl extends DaoSupportImpl<TeaAnswer> implements ITeaAnswerService{

}
