package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.SpiderProfessionType;
import com.hhit.service.ISpiderProfessionTypeService;

@Service
@Transactional
public class SpiderProfessionTypeServiceImpl extends DaoSupportImpl<SpiderProfessionType> implements ISpiderProfessionTypeService{

}
