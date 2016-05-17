package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.StuScoreRecord;
import com.hhit.entity.TestPaper;

public interface IStuScoreRecordService extends IDaoSupport<StuScoreRecord>{

	//根据学号和测试卷查找
	StuScoreRecord findByStuNumAndPaper(String stuNum, TestPaper testPaperFind);
	//根据测试卷查找
	List<StuScoreRecord> findByPaper(TestPaper paperFind);

}
