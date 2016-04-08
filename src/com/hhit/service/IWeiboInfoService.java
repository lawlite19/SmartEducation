package com.hhit.service;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.WeiboInfo;

public interface IWeiboInfoService extends IDaoSupport<WeiboInfo>{

	//根据传递的标识查找微博信息
	WeiboInfo findByIdenfier(String identifier);

}
