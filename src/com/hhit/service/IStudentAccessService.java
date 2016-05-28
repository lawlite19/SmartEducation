package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.AStudentAccess;
import com.hhit.entity.ATerm;
import com.hhit.entity.Student;
import com.hhit.entity.Teacher;
import com.hhit.entity.TeacherClassGrade;

public interface IStudentAccessService extends IDaoSupport<AStudentAccess> {

	List<AStudentAccess> findByTeaAndStuAndTerm(Teacher teafind, Student stufind, ATerm termfind);

	double findgradeByTeaAndTerm(Teacher teacher, ATerm term);

	List<Object> findclassgradeByTeaAndTerm(Teacher teacher, ATerm term);

}
