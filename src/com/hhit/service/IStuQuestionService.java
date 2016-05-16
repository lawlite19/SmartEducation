package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.StuQuestion;

public interface IStuQuestionService extends IDaoSupport<StuQuestion>{

	//查询学生提问的问题
	List<StuQuestion> findByStuNum(String stuNum);

}
