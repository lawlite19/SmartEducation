package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Judgement;

public interface IJudgementService extends IDaoSupport<Judgement>{

	//最后一条记录Id
	Judgement findMaxRecord();

	//第一条记录Id
	Judgement findMinRecord();
}
