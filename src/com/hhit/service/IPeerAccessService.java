package com.hhit.service;


import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.APeerAccess;
import com.hhit.entity.Teacher;

public interface IPeerAccessService extends IDaoSupport<APeerAccess> {
	public List<Teacher> findTeaByLeader(int id);
	
}
