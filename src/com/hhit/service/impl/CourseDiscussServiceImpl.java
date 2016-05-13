package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.CourseDiscuss;
import com.hhit.service.ICourseDiscussService;
@Service
@Transactional
public class CourseDiscussServiceImpl extends DaoSupportImpl<CourseDiscuss> implements ICourseDiscussService{

}
