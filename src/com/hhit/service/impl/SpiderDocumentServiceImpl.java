package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.SpiderCourse;
import com.hhit.entity.SpiderDocument;
import com.hhit.service.ISpiderDocumentService;

@Service
@Transactional
public class SpiderDocumentServiceImpl extends DaoSupportImpl<SpiderDocument> implements ISpiderDocumentService{

	@SuppressWarnings("unchecked")
	@Override
	public List<SpiderDocument> findByCourse(SpiderCourse courseFind) {
		return getSession().createQuery("FROM SpiderDocument WHERE spiderCourse=?")//
		.setParameter(0, courseFind)//
		.list();
	}

}
