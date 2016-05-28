package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.ASelfAccessAccount;
import com.hhit.entity.AStudentAccessAccount;
import com.hhit.entity.ATerm;

public interface ISelfAccountService extends IDaoSupport<ASelfAccessAccount> {
	public List<ASelfAccessAccount> findByTerm(ATerm termFind);
}
