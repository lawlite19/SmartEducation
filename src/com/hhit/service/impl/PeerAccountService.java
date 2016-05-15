package com.hhit.service.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhit.base.DaoSupportImpl;
import com.hhit.entity.APeerAccessAccount;
import com.hhit.service.IPeerAccountService;

@Service
@Transactional
public class PeerAccountService extends DaoSupportImpl<APeerAccessAccount> implements
		IPeerAccountService {


}
