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


}
