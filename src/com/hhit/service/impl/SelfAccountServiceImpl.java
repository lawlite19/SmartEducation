package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.APeerAccessAccount;
import com.hhit.entity.ASelfAccessAccount;
import com.hhit.entity.ATerm;
import com.hhit.service.ISelfAccountService;

@Service
@Transactional
public class SelfAccountServiceImpl extends DaoSupportImpl<ASelfAccessAccount> implements
		ISelfAccountService {

	@Override
	public List<ASelfAccessAccount> findByTerm(ATerm termFind) {
		@SuppressWarnings("unchecked")
		List<ASelfAccessAccount> selfaccoutList=getSession()
				.createQuery("select s from ASelfAccessAccount s where s.ATerm=?" )
				.setParameter(0, termFind).list();
		return selfaccoutList;
	}
	

}
