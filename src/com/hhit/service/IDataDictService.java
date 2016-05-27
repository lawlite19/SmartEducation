package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.DataDict;
import com.hhit.entity.DataType;

public interface IDataDictService extends IDaoSupport<DataDict>{

	//根据编号查找
	DataDict findByDictNum(String dictNum);
	//根据学年查找对应的学期
	List<DataDict> findByYear(String year);
	//当前学期--id最大的
	DataDict findCurrentTerm(DataType dataTypeFind);

}
