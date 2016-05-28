package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.ATeachingAccount;
import com.hhit.entity.ATerm;

public interface ITeachAccountService extends IDaoSupport<ATeachingAccount> {

	List<ATeachingAccount> findByTerm(ATerm term);

}
