package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.Role;
import com.hhit.service.IRoleService;


@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements IRoleService {
	
}
