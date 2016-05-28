package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.ATeacherGrade;
import com.hhit.entity.ATerm;
import com.hhit.entity.Teacher;
import com.hhit.service.ITeacherGradeService;
@Controller
@Transactional

public class TeacherGradeServiceImpl extends DaoSupportImpl<ATeacherGrade> implements
		ITeacherGradeService {

	@Override
	public ATeacherGrade findByTeaAndTerm(Teacher teacher, ATerm term) {
		ATeacherGrade teagrade=(ATeacherGrade) getSession().//
				createQuery("select t from ATeacherGrade t where Teacher=? and ATerm=?").
				setParameter(0, teacher).setParameter(1, term).uniqueResult();
		return teagrade;
	}

	@SuppressWarnings("unused")
	@Override
	public List<ATeacherGrade> findByTea(Teacher teacher) {
		@SuppressWarnings("unchecked")
		List<ATeacherGrade> teagradeList= (List<ATeacherGrade>)getSession().//
				createQuery("select t from ATeacherGrade t where Teacher=? order by ATerm desc").
				setParameter(0, teacher).list();
		return teagradeList;
	}

	@Override
	public List<Double> findPeerGrade(Teacher teacher) {
		@SuppressWarnings("unchecked")
		List<Double> peergrade=getSession().//
				createQuery("select t.peergrade from ATeacherGrade t where Teacher=? order by ATerm asc").
				setParameter(0, teacher).list();
		return peergrade;
	}

	@Override
	public List<Double> findSelfGrade(Teacher teacher) {
		@SuppressWarnings("unchecked")
		List<Double> selfgrade=getSession().//
				createQuery("select t.selfgrade from ATeacherGrade t where Teacher=? order by ATerm asc").
				setParameter(0, teacher).list();
		return selfgrade;
	}

	@Override
	public List<Double> findTeachGrade(Teacher teacher) {
		@SuppressWarnings("unchecked")
		List<Double> teachgrade=getSession().//
				createQuery("select t.teachgrade from ATeacherGrade t where Teacher=? order by ATerm asc").
				setParameter(0, teacher).list();
		return teachgrade;
	}

	@Override
	public List<Double> findStuGrade(Teacher teacher) {
		@SuppressWarnings("unchecked")
		List<Double> stugrade=getSession().//
				createQuery("select t.stugrade from ATeacherGrade t where Teacher=? order by ATerm asc").
				setParameter(0, teacher).list();
		return stugrade;
	}

	@Override
	public List<Double> findAllGrade(Teacher teacher) {
		@SuppressWarnings("unchecked")
		List<Double> allgrade=getSession().//
				createQuery("select t.allgrade from ATeacherGrade t where Teacher=? order by ATerm asc").
				setParameter(0, teacher).list();
		return allgrade;
	}

}
