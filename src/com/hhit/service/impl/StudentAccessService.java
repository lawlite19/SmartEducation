package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.AStudentAccess;
import com.hhit.entity.ATerm;
import com.hhit.entity.Student;
import com.hhit.entity.Teacher;
import com.hhit.entity.TeacherClassGrade;
import com.hhit.service.IStudentAccessService;
@Service
@Transactional

public class StudentAccessService extends DaoSupportImpl<AStudentAccess> implements
		IStudentAccessService {

	@SuppressWarnings("unchecked")
	@Override
	public List<AStudentAccess> findByTeaAndStuAndTerm(Teacher teafind,
			Student stufind, ATerm termfind) {
		
		return getSession().createQuery("FROM AStudentAccess s WHERE s.Teacher=? and s.Student=? and s.ATerm=?" )//
				.setParameter(0, teafind).setParameter(1, stufind).setParameter(2, termfind)//
				.list();
	}

	@Override
	public double findgradeByTeaAndTerm(Teacher teacher, ATerm term) {
		double stugrade=(double) getSession().
				createQuery("select avg(s.studentAccess) from AStudentAccess s where s.Teacher=? and s.ATerm=? group by s.Teacher,s.ATerm").setParameter(0, teacher).setParameter(1, term).uniqueResult();
		return stugrade;
	}

	@Override
	public List<Object> findclassgradeByTeaAndTerm(Teacher teacher, ATerm term) {
		@SuppressWarnings("unchecked")
		
		List<Object> classgrade=getSession().
		createSQLQuery("select cl.ClassName as classname ,c.CourseName as coursename,count(s.Student_Id) as sumgrade,AVG(s.Student_Access)as avggrade from A_StudentAccess s,T_ClassSelectCourse t,T_Course c,T_Class cl where s.Tea_Id=? and s.Term_Id=? and s.Course_Id=t.Course_Id and s.Term_Id=t.Term_Id and t.Course_Id=c.Id and t.Class_Id=cl.Id group by coursename,classname").
		setParameter(0, teacher).setParameter(1, term).list();
		return classgrade;
	}


}
