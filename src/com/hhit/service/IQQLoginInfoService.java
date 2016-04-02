package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.QQLoginInfo;

public interface IQQLoginInfoService extends IDaoSupport<QQLoginInfo> {

	//根据openId查询登录信息
	QQLoginInfo findByOpenId(String opendId);

}
