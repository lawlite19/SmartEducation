package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.StuScoreRecord;
import com.hhit.entity.TestPaper;
import com.hhit.service.IStuScoreRecordService;

@Service
@Transactional
public class StuScoreRecordServiceImpl extends DaoSupportImpl<StuScoreRecord> implements IStuScoreRecordService{

	@Override
	public StuScoreRecord findByStuNumAndPaper(String stuNum, TestPaper testPaperFind) {
		return (StuScoreRecord) getSession().createQuery("FROM StuScoreRecord WHERE stuNum=? AND testPaper=?")//
				.setParameter(0, stuNum)//
				.setParameter(1, testPaperFind)//
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StuScoreRecord> findByPaper(TestPaper paperFind) {
		return getSession().createQuery("FROM StuScoreRecord WHERE testPaper=?")//
				.setParameter(0, paperFind)//
				.list();
	}
	
}
