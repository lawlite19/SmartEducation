package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.StuFlowerInfo;
import com.hhit.entity.StuScoreRecord;
import com.hhit.service.IStuFlowerInfoService;
@Service
@Transactional
public class StuFlowerInfoServiceImpl extends DaoSupportImpl<StuFlowerInfo> implements IStuFlowerInfoService{

	@Override
	public StuFlowerInfo findByStuScoreRecord(StuScoreRecord recordFind) {
		return (StuFlowerInfo) getSession().createQuery("FROM StuFlowerInfo WHERE stuScoreRecord=?")//
				.setParameter(0, recordFind)//
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StuFlowerInfo> findByStuNum(String stuNum) {
		return getSession().createQuery("FROM StuFlowerInfo WHERE stuNum=?")//
				.setParameter(0, stuNum)//
				.list();
			
	}

}
