package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.StuFlowerInfo;
import com.hhit.entity.StuScoreRecord;

public interface IStuFlowerInfoService extends IDaoSupport<StuFlowerInfo>{

	//根据分数记录找
	StuFlowerInfo findByStuScoreRecord(StuScoreRecord recordFind);

	//根据学号查找
	List<StuFlowerInfo> findByStuNum(String stuNum);

}
