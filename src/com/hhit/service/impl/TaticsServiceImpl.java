package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.ATatics;
import com.hhit.entity.ATerm;
import com.hhit.service.ITaticsService;
@Service
@Transactional
public class TaticsServiceImpl extends DaoSupportImpl<ATatics> implements
		ITaticsService {


	@Override
	public List<ATatics> findByTerm(ATerm termFind) {
		// TODO Auto-generated method stub
		return null;
	}
	public int findMaxId() {
        String hql="select max(t.id) from ATatics t";
		return (int)getSession().createQuery(hql).uniqueResult();
	}
}
