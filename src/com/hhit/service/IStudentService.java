package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Student;

public interface IStudentService extends IDaoSupport<Student>{

	//根据学号查找
	Student findByStuNum(String stuNum);
}
