package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;


@Service
public class AccountAuthService implements IAccountAuthService{
@Autowired
	private RedisService redisService;
	@Autowired
	private IAccountAuthDao accountAuthDao;
	@Override
	public List<Integer> getMenuIdsByAccountId(Integer id) {
		return accountAuthDao.getMenuIdsByAccountId(id);
	}
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public boolean modifyAuthList(List<AccountAuth> deleteList,List<AccountAuth> insertList) {
		boolean deleteResult=true;
		boolean insertResult=true;
		int accoutid=0;
		if ((insertList==null||insertList.size()==0)&&
				(deleteList==null||deleteList.size()==0)) {
			return false;
		}
		if (insertList!=null&&insertList.size()>0) {
			insertResult= accountAuthDao.insertAuthList(insertList);
			accoutid=insertList.get(0).getAccoutid();
		}
		if (deleteList!=null&&deleteList.size()>0) {
			deleteResult= accountAuthDao.deleteAuthList(deleteList);
			accoutid=deleteList.get(0).getAccoutid();
		}
		if (insertResult&&deleteResult) {
			String key=RedissCacheKey.Menu_Auth+accoutid;
			redisService.remove(key);
		}
		return true;
	}
}
