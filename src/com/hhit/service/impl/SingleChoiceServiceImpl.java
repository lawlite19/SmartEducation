package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.SingleChoice;
import com.hhit.service.ISingleChoiceService;

@Service
@Transactional
public class SingleChoiceServiceImpl extends DaoSupportImpl<SingleChoice> implements ISingleChoiceService{

}
