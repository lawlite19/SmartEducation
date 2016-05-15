package com.hhit.service.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.ATeachingAccount;

import com.hhit.service.ITeachAccountService;
import com.hhit.util.QueryHelper;

@Service
@Transactional
public class TeachAccountServiceImpl extends DaoSupportImpl<ATeachingAccount> implements
		ITeachAccountService {

	
}
