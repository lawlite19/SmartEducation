package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Favorite;
import com.hhit.entity.SpiderCourse;
import com.hhit.entity.Student;
import com.hhit.service.IFavoriteService;
@Service
@Transactional
public class FavoriteServiceImpl extends DaoSupportImpl<Favorite> implements IFavoriteService{

	@Override
	public Favorite findByStuAndCourse(Student student, SpiderCourse courseFind) {
		return (Favorite) getSession().createQuery("FROM Favorite WHERE student=? AND spiderCourse=?")//
		.setParameter(0, student)//
		.setParameter(1, courseFind)//
		.uniqueResult();
	}

	@Override
	public void deleteByStuAndCourse(Student student, SpiderCourse courseFind) {
		getSession().createQuery("DELETE FROM Favorite WHERE student=? AND spiderCourse=?")//
		.setParameter(0, student)//
		.setParameter(1, courseFind)//
		.executeUpdate();
	}

}
