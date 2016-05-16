package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.entity.Judgement;

public interface IJudgementService extends IDaoSupport<Judgement>{

	//最后一条记录Id
	Judgement findMaxRecord();

	//第一条记录Id
	Judgement findMinRecord();
	//课程、章节记录总数
	Long findByCourseAndChapter(Course courseFind, Chapter chapterFind);
}
