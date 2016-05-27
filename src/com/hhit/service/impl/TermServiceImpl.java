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

	@Override
	public int findMaxId() {
        String hql="select max(t.id) from ATerm t";
		return (int)getSession().createQuery(hql).uniqueResult();
	}

	@Override
	public List<String> findTermName() {
		@SuppressWarnings("unchecked")
		List<String> termlist=getSession().//
				createQuery("select t.name from ATerm t  order by id asc").
				list();
		return termlist;
	}
	
	
}
