package com.hhit.service.impl;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Course;
import com.hhit.entity.Department;
import com.hhit.entity.UserDetails;
import com.hhit.service.ICourseService;
@SuppressWarnings("unchecked")

@Service
@Transactional
public class CourseServiceImpl extends DaoSupportImpl<Course> implements
		ICourseService {
	@Override
	public List<Course> findByUser(UserDetails userDetails) {
		return getSession().createQuery("FROM Course WHERE teacher=?")//
				.setParameter(0, userDetails)//
				.list();
	}

}
