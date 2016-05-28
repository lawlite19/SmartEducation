package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.AStudentAccessAccount;
import com.hhit.entity.ATerm;


public interface IStuAccountService extends IDaoSupport<AStudentAccessAccount> {
	List<AStudentAccessAccount> findList();
	List<AStudentAccessAccount> findByTerm(ATerm termFind);

	
}
