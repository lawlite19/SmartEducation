package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.APeerAccess;
import com.hhit.entity.Teacher;
import com.hhit.service.IPeerAccessService;
import com.hhit.util.QueryHelper;
@Service
@Transactional
public class PeerAccessServiceImpl extends DaoSupportImpl<APeerAccess> implements
		IPeerAccessService {


	public List<Teacher> findTeaByLeader(int id) {
		
		return null;
	}

}
