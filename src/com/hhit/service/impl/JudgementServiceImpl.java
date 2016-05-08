package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Judgement;
import com.hhit.service.IJudgementService;

@Service
@Transactional
public class JudgementServiceImpl extends DaoSupportImpl<Judgement> implements IJudgementService{

}
