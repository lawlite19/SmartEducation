package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.ATeachingAccess;
import com.hhit.entity.ATeachingAccount;
import com.hhit.entity.ATerm;

public interface ITeachingAccessService extends IDaoSupport<ATeachingAccess> {

	List<ATeachingAccess> findByTerm(ATerm term);

}
