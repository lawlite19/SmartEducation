package com.hhit.service;

import java.util.Collection;
import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.Privilege;

public interface IPrivilegeService extends IDaoSupport<Privilege> {

	// 查询所有顶级权限
	List<Privilege> findTopList();

	// 查找所有二级权限
	List<Privilege> findSecondList();

	// 查询所有权限url
	Collection<String> getAllPrivilegeUrls();

}
