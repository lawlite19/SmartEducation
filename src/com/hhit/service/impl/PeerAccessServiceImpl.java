package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.APeerAccess;
import com.hhit.entity.ATerm;
import com.hhit.entity.Teacher;
import com.hhit.service.IPeerAccessService;
@Service
@Transactional
public class PeerAccessServiceImpl extends DaoSupportImpl<APeerAccess> implements
		IPeerAccessService {


	public List<Teacher> findTeaByLeader(int id) {
		
		return null;
	}

	@Override
	public double findgradeByTeaAndTerm(Teacher teacher, ATerm term) {
		double peergrade=(double) getSession().
				createQuery("select avg(peerAccess) from APeerAccess p where p.teacher=? and ATerm=? group by p.teacher,p.ATerm").
				setParameter(0, teacher).setParameter(1, term).uniqueResult();
		return peergrade;
	}

}
