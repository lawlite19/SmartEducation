package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.ATerm;
import com.hhit.entity.Department;
import com.hhit.service.ITermService;

@Service
@Transactional
public class TermServiceImpl extends DaoSupportImpl<ATerm> implements
		ITermService {

	@SuppressWarnings("unchecked")
	@Override
	public List<ATerm> findList() {
		return (List<ATerm>) getSession().createQuery(//　
				"FROM ATerm t ")//
				.list();
	}

	
}
