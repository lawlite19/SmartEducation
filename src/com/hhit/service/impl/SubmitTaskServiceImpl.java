package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.SubmitTask;
import com.hhit.service.ISubmitTaskService;

@Service
@Transactional
public class SubmitTaskServiceImpl extends DaoSupportImpl<SubmitTask> implements ISubmitTaskService{

}
