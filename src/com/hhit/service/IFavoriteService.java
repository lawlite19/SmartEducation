package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Favorite;
import com.hhit.entity.SpiderCourse;
import com.hhit.entity.Student;

public interface IFavoriteService extends IDaoSupport<Favorite>{

	//根据学生和课程查找收藏
	Favorite findByStuAndCourse(Student student, SpiderCourse courseFind);
	
}
