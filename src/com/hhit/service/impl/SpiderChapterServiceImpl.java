package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.SpiderChapter;
import com.hhit.service.ISpiderChapterService;

@Service
@Transactional
public class SpiderChapterServiceImpl extends DaoSupportImpl<SpiderChapter> implements ISpiderChapterService{

}
