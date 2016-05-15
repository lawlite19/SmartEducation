package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.ASelfAccessAccount;
import com.hhit.entity.ATerm;
import com.hhit.service.ISelfAccountService;

@Service
@Transactional
public class SelfAccountServiceImpl extends DaoSupportImpl<ASelfAccessAccount> implements
		ISelfAccountService {

	@Override
	public List<ASelfAccessAccount> findByTerm(ATerm termFind) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
