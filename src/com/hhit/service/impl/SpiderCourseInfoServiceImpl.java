package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.SpiderCourseInfo;
import com.hhit.service.ISpiderCourseInfoService;

@Service
@Transactional
public class SpiderCourseInfoServiceImpl extends DaoSupportImpl<SpiderCourseInfo> implements ISpiderCourseInfoService{

}
