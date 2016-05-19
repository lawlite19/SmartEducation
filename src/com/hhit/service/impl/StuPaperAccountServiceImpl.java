package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Class_;
import com.hhit.entity.Course;
import com.hhit.entity.StuPaperAccount;
import com.hhit.service.IStuPaperAccountService;
@Service
@Transactional
public class StuPaperAccountServiceImpl extends DaoSupportImpl<StuPaperAccount> implements IStuPaperAccountService{

	@Override
	public StuPaperAccount findByStuNum(String stuNum) {
		return (StuPaperAccount) getSession().createQuery("FROM StuPaperAccount WHERE stuNum=?")//
				.setParameter(0, stuNum)//
				.uniqueResult();
	}

	@Override
	public StuPaperAccount findByStuNumAndCourse(String stuNum,Course courseFind) {
		return (StuPaperAccount) getSession().createQuery("FROM StuPaperAccount WHERE stuNum=? AND course=?")//
				.setParameter(0, stuNum)//
				.setParameter(1, courseFind)//
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StuPaperAccount> findByCourseAndClass(Course courseFind, Class_ classFind) {
		return getSession().createQuery("FROM StuPaperAccount WHERE course=? AND class_=?")//
				.setParameter(0, courseFind)//
				.setParameter(1, classFind)//
				.list();
	}

}
