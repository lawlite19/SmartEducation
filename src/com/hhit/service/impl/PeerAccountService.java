package com.hhit.service.impl;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.APeerAccessAccount;
import com.hhit.entity.ATerm;
import com.hhit.service.IPeerAccountService;

@Service
@Transactional
public class PeerAccountService extends DaoSupportImpl<APeerAccessAccount> implements
		IPeerAccountService {

	@Override
	public List<APeerAccessAccount> findByTerm(ATerm term) {
		@SuppressWarnings("unchecked")
		List<APeerAccessAccount> peeraccoutList=getSession()
				.createQuery("select p from APeerAccessAccount p where p.ATerm=?" )
				.setParameter(0, term).list();
		return peeraccoutList;
	}


}
