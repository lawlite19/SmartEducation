package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.entity.PageBean;
import com.hhit.entity.SingleChoice;

public interface ISingleChoiceService extends IDaoSupport<SingleChoice>{

	//最后一条记录
	public SingleChoice findMaxRecord();
	//第一条记录
	public SingleChoice findMinRecord();
	public Long findByCourseAndChapterList(Course courseFind, List<Chapter> chapterList);
	//得到分页数据
	public PageBean getPageBeanByInList(Course courseFind,int randomSingle, int pageSize,
			int recordSingleCount, List<Chapter> chapterList);
}
