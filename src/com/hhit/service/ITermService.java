package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.ATerm;

public interface ITermService extends IDaoSupport<ATerm> {

	List<ATerm> findList();
	int findMaxId();
	List<String> findTermName();
}
