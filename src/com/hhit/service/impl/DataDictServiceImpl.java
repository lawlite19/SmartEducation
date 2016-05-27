package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.DataDict;
import com.hhit.entity.DataType;
import com.hhit.service.IDataDictService;

@Service
@Transactional
public class DataDictServiceImpl extends DaoSupportImpl<DataDict> implements IDataDictService{

	@Override
	public DataDict findByDictNum(String dictNum) {
		return (DataDict) getSession().createQuery("FROM DataDict WHERE dictNum=?")//
				.setParameter(0, dictNum)//
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataDict> findByYear(String year) {
		return getSession().createQuery("FROM DataDict WHERE dictName LIKE ?")//
				.setParameter(0, '%'+year+'%')//
				.list();
	}

	@Override
	public DataDict findCurrentTerm(DataType dataTypeFind) {
		return (DataDict) getSession().createQuery("FROM DataDict WHERE dataType=? ORDER BY id DESC")//
				.setParameter(0, dataTypeFind)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

}
