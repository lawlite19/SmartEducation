package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.SpiderDocument;
import com.hhit.service.ISpiderDocumentService;

@Service
@Transactional
public class SpiderDocumentServiceImpl extends DaoSupportImpl<SpiderDocument> implements ISpiderDocumentService{

}
