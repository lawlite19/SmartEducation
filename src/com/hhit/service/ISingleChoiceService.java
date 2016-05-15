package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.entity.SingleChoice;

public interface ISingleChoiceService extends IDaoSupport<SingleChoice>{

	//最后一条记录
	public SingleChoice findMaxRecord();
	//第一条记录
	public SingleChoice findMinRecord();
	public Long findByCourseAndChapter(Course courseFind, Chapter chapterFind);
}
