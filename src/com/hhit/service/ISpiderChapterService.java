package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.SpiderChapter;
import com.hhit.entity.SpiderCourse;

public interface ISpiderChapterService extends IDaoSupport<SpiderChapter>{

	//根据课程查找章节
	List<SpiderChapter> findByCourse(SpiderCourse courseFind);

}
