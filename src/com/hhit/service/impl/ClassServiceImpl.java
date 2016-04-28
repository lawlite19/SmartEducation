package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Class_;
import com.hhit.service.IClassService;

@Service
@Transactional
public class ClassServiceImpl extends DaoSupportImpl<Class_> implements IClassService{

}
