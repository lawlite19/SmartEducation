package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.SpiderCourse;
import com.hhit.service.ISpiderCourseService;

@Service
@Transactional
public class SpiderCourseServiceImpl extends DaoSupportImpl<SpiderCourse> implements ISpiderCourseService{

}
