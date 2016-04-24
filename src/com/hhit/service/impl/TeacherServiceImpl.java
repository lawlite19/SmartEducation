package com.hhit.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Student;
import com.hhit.entity.Teacher;
import com.hhit.service.ITeacherService;

@Service
@Transactional
public class TeacherServiceImpl extends DaoSupportImpl<Teacher> implements
		ITeacherService {

	@Override
	public Teacher findByNumAndPwd(String userNum, String password) {
		String realPwd=DigestUtils.md5Hex(password.trim());
		return (Teacher) getSession().createQuery(//
				"FROM Teacher WHERE teaNum=? AND teaPwd=?")//
				.setParameter(0, userNum)//
				.setParameter(1, realPwd)//
				.uniqueResult();

	}

}
