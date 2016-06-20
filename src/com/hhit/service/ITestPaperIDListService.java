package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.TestPaperIDList;

public interface ITestPaperIDListService extends IDaoSupport<TestPaperIDList>{
	public TestPaperIDList findbytestpaperid(String testpaperid);
}
