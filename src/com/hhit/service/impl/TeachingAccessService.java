package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.ATeachingAccess;
import com.hhit.entity.ATeachingAccount;
import com.hhit.entity.ATerm;
import com.hhit.service.ITeachingAccessService;
@Service
@Transactional
public class TeachingAccessService extends DaoSupportImpl<ATeachingAccess> implements
		ITeachingAccessService {

	@Override
	public List<ATeachingAccess> findByTerm(ATerm term) {
		@SuppressWarnings("unchecked")
		List<ATeachingAccess> teachaccoutList=getSession()
				.createQuery("select t from ATeachingAccess t where t.ATerm=?" )
				.setParameter(0, term).list();
		return teachaccoutList;
	}
	
	
}
