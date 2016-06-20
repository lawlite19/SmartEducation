package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.TestingControl;

public interface ITestingControlService extends IDaoSupport<TestingControl>{
	/**查找TestingControl表中最大的RNumber*/
	public int findlastRNumber();
	
	/**插入list*/
	public void save(List<TestingControl> tcs);
	
	/**查找本场考试的TestingControl*/
	public List<TestingControl> findTestingControl(int testroomid);
	
	/**更根据学号查找本场考试的TestingControl*/
	public TestingControl findbytesternum(String stunum);

	public String findstatebytesternum(String stunum);
	
	public String getuploadstate(String stunum);
}
