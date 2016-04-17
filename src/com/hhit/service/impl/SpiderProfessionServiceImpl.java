package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.SpiderProfession;
import com.hhit.service.ISpiderProfessionService;
@Service
@Transactional
public class SpiderProfessionServiceImpl extends DaoSupportImpl<SpiderProfession> implements ISpiderProfessionService{

}
