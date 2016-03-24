package com.hhit.dao.impl;

import org.springframework.stereotype.Repository;

import com.hhit.base.BaseDaoImpl;
import com.hhit.dao.IUserDao;
import com.hhit.entity.User;

//注入容器
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
}
