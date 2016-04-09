package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.LogFile;
import com.hhit.service.ILogFileService;

@Service
@Transactional
public class LogFileServiceImpl extends DaoSupportImpl<LogFile> implements ILogFileService {

}
