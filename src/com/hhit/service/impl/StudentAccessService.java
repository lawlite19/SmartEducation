package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.AStudentAccess;
import com.hhit.service.IStudentAccessService;
@Service
@Transactional

public class StudentAccessService extends DaoSupportImpl<AStudentAccess> implements
		IStudentAccessService {


}
