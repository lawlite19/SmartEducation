package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.ATeacherGrade;
import com.hhit.entity.ATerm;
import com.hhit.entity.Teacher;

public interface ITeacherGradeService extends IDaoSupport<ATeacherGrade> {

	ATeacherGrade findByTeaAndTerm(Teacher teacher, ATerm term);

	List<ATeacherGrade> findByTea(Teacher teacher);

	List<Double> findPeerGrade(Teacher teacher);

	List<Double> findSelfGrade(Teacher teacher);

	List<Double> findTeachGrade(Teacher teacher);

	List<Double> findStuGrade(Teacher teacher);

	List<Double> findAllGrade(Teacher teacher);

}
