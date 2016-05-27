package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.ATatics;
import com.hhit.entity.ATerm;

public interface ITaticsService extends IDaoSupport<ATatics> {
	ATatics findByTerm(ATerm termFind);
	int findMaxId();
}
