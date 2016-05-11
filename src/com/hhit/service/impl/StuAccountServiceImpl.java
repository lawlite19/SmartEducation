package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.AStudentAccessAccount;
import com.hhit.entity.ATerm;
import com.hhit.service.IStuAccountService;
import com.hhit.util.QueryHelper;
@SuppressWarnings("unchecked")
@Service
@Transactional
public class StuAccountServiceImpl extends DaoSupportImpl<AStudentAccessAccount> implements
		IStuAccountService {

	@Override
	public List<AStudentAccessAccount> findList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AStudentAccessAccount> findByTerm(ATerm termFind) {
		// TODO Auto-generated method stub
		return null;
	}

}
