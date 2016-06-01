package com.hhit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Department;
import com.hhit.service.IDepartmentService;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements
		IDepartmentService {

	@Override
	public List<Department> findTopList() {
		return (List<Department>) getSession().createQuery(//
				"FROM Department d WHERE d.parent IS NULL")//
				.list();
	}

	@Override
	public List<Department> findChildren(Integer parentId) {
		return getSession().createQuery(//
				"FROM Department d WHERE d.parent.id=?")//
				.setParameter(0, parentId)//
				.list();
	}

	@Override
	public List<Department> findByDeptLevel(int i) {
		return getSession().createQuery("FROM Department d WHERE d.deptLevel=?")//
				.setParameter(0, i)//
				.list();
	}

	@Override
	public List<Department> findByParent(Department deptLevel2) {
		return getSession().createQuery("FROM Department WHERE parent=?")//
				.setParameter(0, deptLevel2)//
				.list();
	}

}
