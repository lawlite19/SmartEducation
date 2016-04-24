package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Student;

public interface IStudentService extends IDaoSupport<Student>{

	//根据账号密码查找学生
	Student findByNumAndPwd(String userNum, String password);

}
