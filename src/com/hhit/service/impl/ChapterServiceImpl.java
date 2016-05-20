package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Chapter;
import com.hhit.entity.Course;
import com.hhit.service.IChapterService;

@Service
@Transactional
public class ChapterServiceImpl extends DaoSupportImpl<Chapter> implements IChapterService{

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> findByCourse(Course courseFind) {
		return getSession().createQuery("FROM Chapter WHERE course=?")//
		.setParameter(0, courseFind)//
		.list();
	}

	@Override
	public Chapter findByCourseAndLikeChapterName(Course courseFind,String string) {
		return (Chapter) getSession().createQuery("FROM Chapter WHERE course=? AND chapterName LIKE ?")//
				.setParameter(0, courseFind)//
				.setParameter(1, '%'+string+'%')//
				.uniqueResult();
	}

	@Override
	public Chapter findByCourseAndLikeChapterNum(Course courseFind,
			String string) {
		return (Chapter) getSession().createQuery("FROM Chapter WHERE parent=? AND chapterNum LIKE ?")//
				.setParameter(0, courseFind)//
				.setParameter(1, '%'+string+'%')//
				.uniqueResult();
	}

	@Override
	public Chapter findByParentAndLikeChapterNum(Chapter chapter, String string) {
		return (Chapter) getSession().createQuery("FROM Chapter WHERE parent=? AND chapterNum LIKE ?")//
				.setParameter(0, chapter)//
				.setParameter(1, '%'+string+'%')//
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> findByParent(Chapter parentFind) {
		return getSession().createQuery("FROM Chapter WHERE parent=?")//
				.setParameter(0, parentFind)//
				.list();
	}

}
