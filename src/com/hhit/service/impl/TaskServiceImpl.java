package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Task;
import com.hhit.service.ITaskService;

@Service
@Transactional
public class TaskServiceImpl extends DaoSupportImpl<Task> implements ITaskService{

}
