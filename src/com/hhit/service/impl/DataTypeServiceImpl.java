package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.DataType;
import com.hhit.service.IDataTypeService;

@Service
@Transactional
public class DataTypeServiceImpl extends DaoSupportImpl<DataType> implements IDataTypeService{

}
