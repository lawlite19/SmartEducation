package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.APeerAccessAccount;
import com.hhit.entity.ATerm;

public interface IPeerAccountService extends IDaoSupport<APeerAccessAccount> {

	List<APeerAccessAccount> findByTerm(ATerm term);

}
