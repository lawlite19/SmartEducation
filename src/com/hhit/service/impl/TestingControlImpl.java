package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl2;
import com.hhit.entity.TestingControl;
import com.hhit.service.ITestingControlService;
import com.hhit.util.CustomerContextHolder;


@Service
@Transactional
public class TestingControlImpl extends DaoSupportImpl2<TestingControl> implements ITestingControlService{

	@Override
	public int findlastRNumber() {
		return (int)getSession().createSQLQuery("select top 1 Rnumber from testingcontrol order by Rnumber desc ")
				.uniqueResult();
	}

	@Override
	public void save(List<TestingControl> tcs) {
		for (TestingControl testingControl : tcs) {
			save(testingControl);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestingControl> findTestingControl(int testroomid) {
		String sql="select a.Rnumber,a.TestName,a.TestPaperID,a.TestList,a.TesterID,a.TesterName,a.TestRoomName,a.ClassName,a.LoginFlag,a.TestRoomID,a.teaname,a.teaid "
				+ "from testingcontrol a left join TestPaperIDList b on a.TestPaperID=b.TestPaperID "
				+ "where a.TestRoomID='"+testroomid+"' and datediff(minute,getdate(),b.begintime)<=20 and datediff(minute,getdate(),b.endtime)>-20  order by TesterID asc";
		return (List<TestingControl>)getSession().createSQLQuery(sql)
				.addEntity("a",TestingControl.class)
				.list();
	}

	@Override
	public TestingControl findbytesternum(String stunum) {
		String sql="select a.Rnumber,a.TestName,a.TestPaperID,a.TestList,a.TesterID,a.TesterName,a.TestRoomName,a.ClassName,a.LoginFlag,a.TestRoomID,a.teaname,a.teaid "
				+ "from testingcontrol a left join TestPaperIDList b on a.TestPaperID=b.TestPaperID "
				+ "where a.TesterID=? and datediff(minute,getdate(),b.begintime)<=20 and datediff(minute,getdate(),b.endtime)>-20";
		return (TestingControl)getSession().createSQLQuery(sql)
				.addEntity("a", TestingControl.class)//该方法用来将类与sql中的查询实体a绑定，不加该方法，hibernate无法自动赋值
				.setParameter(0, stunum)
				.uniqueResult();
	}

	@Override
	public String findstatebytesternum(String stunum) {
		String sql="select a.LoginFlag from testingcontrol a left join TestPaperIDList b on a.TestPaperID=b.TestPaperID "
				+ "where a.TesterID=? and datediff(minute,getdate(),b.begintime)<=20 and datediff(minute,getdate(),b.endtime)>-20";
		return (String)getSession().createSQLQuery(sql)
				.setParameter(0, stunum)
				.uniqueResult();
	}

	@Override
	public String getuploadstate(String stunum) {
		String sql="select a.UploadFile from testingcontrol a left join TestPaperIDList b on a.TestPaperID=b.TestPaperID "
				+ "where a.TesterID=? and datediff(minute,getdate(),b.begintime)<=20 and datediff(minute,getdate(),b.endtime)>-20";
		return (String)getSession().createSQLQuery(sql)
				.setParameter(0, stunum)
				.uniqueResult();
	}

}
