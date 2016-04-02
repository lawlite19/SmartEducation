package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.QQLoginInfo;
import com.hhit.service.IQQLoginInfoService;

@Service("qqLoginInfoServiceImpl")
@Transactional
public class QQLoginInfoServiceImpl extends DaoSupportImpl<QQLoginInfo> implements IQQLoginInfoService {

	@Override
	public QQLoginInfo findByOpenId(String openId) {
		if(openId==null)
			return null;
		return (QQLoginInfo) getSession().createQuery(//
				"FROM QQLoginInfo WHERE openId=?")//
				.setParameter(0, openId)//
				.uniqueResult();
	}
}
