package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.ClassSelectCourse;
import com.hhit.service.IClassSelectCourseService;

@Service
@Transactional
public class ClassSelectCourseServiceImpl extends DaoSupportImpl<ClassSelectCourse> implements IClassSelectCourseService{

}
