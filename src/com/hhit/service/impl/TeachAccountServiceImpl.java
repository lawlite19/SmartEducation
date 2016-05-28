package com.hhit.service.impl;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.ATeachingAccount;
import com.hhit.entity.ATerm;
import com.hhit.service.ITeachAccountService;

@Service
@Transactional
public class TeachAccountServiceImpl extends DaoSupportImpl<ATeachingAccount> implements
		ITeachAccountService {

	@Override
	public List<ATeachingAccount> findByTerm(ATerm term) {
		@SuppressWarnings("unchecked")
		List<ATeachingAccount> teachaccoutList=getSession()
				.createQuery("select t from ATeachingAccount t where t.ATerm=?" )
				.setParameter(0, term).list();
		return teachaccoutList;
	}
	
}
