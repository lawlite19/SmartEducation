package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.DataDict;
import com.hhit.entity.StuCourseScore;

public interface IStuCourseScoreService extends IDaoSupport<StuCourseScore>{

	//根据学号和学期查找
	List<StuCourseScore> findByStuNumAndTerm(String stuNum, DataDict termFind);

	//根据学号和学期List查找
	List<StuCourseScore> findByStuNumAndTerms(String stuNum,List<DataDict> termList);
	//根据学号查找所有成绩
	List<StuCourseScore> findByStuNum(String stuNum);

}
