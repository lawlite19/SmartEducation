package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.TestPaper;
import com.hhit.service.ITestPaperService;

@Service
@Transactional
public class TestPaperServiceImpl extends DaoSupportImpl<TestPaper> implements ITestPaperService{

}
