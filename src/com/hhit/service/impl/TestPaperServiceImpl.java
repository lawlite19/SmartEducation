package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.entity.TestPaper;
import com.hhit.service.ITestPaperService;

@Service
@Transactional
public class TestPaperServiceImpl extends DaoSupportImpl<TestPaper> implements ITestPaperService{

	@SuppressWarnings("unchecked")
	@Override
	public List<TestPaper> findByClassAndCourse(Class_ classFind,Course courseFind) {
		return getSession().createQuery("FROM TestPaper WHERE class_=? AND course=?")//
				.setParameter(0, classFind)//
				.setParameter(1, courseFind)//
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestPaper> findByTeaNumAndCourse(String teaNum, Course courseFind) {
		return getSession().createQuery("FROM TestPaper WHERE teaNum=? AND course=?")//
				.setParameter(0, teaNum)//
				.setParameter(1, courseFind)//
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestPaper> findByClassAndCourse(Class_ classFind) {
		return getSession().createQuery("FROM TestPaper WHERE class_=?")//
				.setParameter(0, classFind)//
				.list();
	}

}
