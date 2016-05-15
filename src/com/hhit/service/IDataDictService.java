package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.DataDict;

public interface IDataDictService extends IDaoSupport<DataDict>{

	//根据编号查找
	DataDict findByDictNum(String dictNum);

}
