package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.DataType;

public interface IDataTypeService extends IDaoSupport<DataType>{

	//根据字典号查找
	DataType findByNum(String string);
}
