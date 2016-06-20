package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl2;
import com.hhit.entity.TestPaperIDList;
import com.hhit.service.ITestPaperIDListService;

@Transactional
@Service
public class TestPaperIDListServiceImpl extends DaoSupportImpl2<TestPaperIDList> implements ITestPaperIDListService{

	@Override
	public TestPaperIDList findbytestpaperid(String testpaperid) {
		return (TestPaperIDList)getSession().createQuery("FROM TestPaperIDList WHERE TestPaperID=?")
				.setParameter(0, testpaperid)
				.uniqueResult();
	}

}
