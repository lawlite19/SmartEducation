package com.hhit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.WeiboInfo;
import com.hhit.service.IWeiboInfoService;
@Service
@Transactional
public class WeiboInfoServiceImpl extends DaoSupportImpl<WeiboInfo> implements IWeiboInfoService{

	@Override
	public WeiboInfo findByIdenfier(String identifier) {
		if((identifier==null)||(identifier.length()==0))
			return null;
		return (WeiboInfo) getSession().createQuery(//
				"FROM WeiboInfo WHERE identifier=?")//
				.setParameter(0, identifier)//
				.uniqueResult();
	}

}
