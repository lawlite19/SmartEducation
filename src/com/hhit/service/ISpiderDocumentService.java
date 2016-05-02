package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.SpiderCourse;
import com.hhit.entity.SpiderDocument;

public interface ISpiderDocumentService extends IDaoSupport<SpiderDocument>{

	//根据课程查找文档
	List<SpiderDocument> findByCourse(SpiderCourse courseFind);

}
