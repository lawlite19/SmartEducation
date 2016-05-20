package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.entity.Judgement;
import com.hhit.entity.PageBean;

public interface IJudgementService extends IDaoSupport<Judgement>{

	//最后一条记录Id
	Judgement findMaxRecord();

	//第一条记录Id
	Judgement findMinRecord();
	//课程、章节记录总数
	Long findByCourseAndChapterList(Course courseFind, List<Chapter> chapterList);
	//分页数据
	PageBean getPageBeanByInList(Course courseFind,int random, int pageSize,int recordCount,
			List<Chapter> chapterList);
}
