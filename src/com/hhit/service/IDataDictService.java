package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.DataDict;
import com.hhit.entity.DataType;

public interface IDataDictService extends IDaoSupport<DataDict>{

	List<DataDict> findByType(DataType datatype);

}
