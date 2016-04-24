package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Teacher;

public interface ITeacherService extends IDaoSupport<Teacher>{

	//根据账号和密码查找老师
	Teacher findByNumAndPwd(String userNum, String password);

}
