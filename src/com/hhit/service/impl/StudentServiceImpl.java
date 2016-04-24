package com.hhit.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.digester.Digester;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Student;
import com.hhit.service.IStudentService;

@Service
@Transactional
public class StudentServiceImpl extends DaoSupportImpl<Student> implements IStudentService{

	@Override
	public Student findByNumAndPwd(String userNum, String password) {
		//加密对比
		String realPwd=DigestUtils.md5Hex(password);
		return (Student) getSession().createQuery(//
				"FROM Student WHERE stuNum=? AND stuPwd=?")//
				.setParameter(0, userNum)//
				.setParameter(1, realPwd)//
				.uniqueResult();
	}

}
