package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.dao.inter.IAccountInfoDao;
import com.etaofinance.api.dao.inter.IRoleAuthDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IRoleAuthService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.RoleAuth;
import com.etaofinance.entity.RoleInfo;

@Service
public class RoleAuthService implements IRoleAuthService{

	@Autowired
	private IRoleAuthDao roleAuthDao;
	@Autowired
	private IAccountInfoDao accountInfoDao;
	@Autowired
	private RedisService redisService;
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public boolean modifyAuthList(List<RoleAuth> deleteList,List<RoleAuth> insertList) {
		boolean deleteResult=true;
		boolean insertResult=true;
		int roleid=0;
		if ((insertList==null||insertList.size()==0)&&
				(deleteList==null||deleteList.size()==0)) {
			return false;
		}
		if (insertList!=null&&insertList.size()>0) {
			insertResult= roleAuthDao.insertAuthList(insertList);
			roleid=insertList.get(0).getRoleid();
		}
		if (deleteList!=null&&deleteList.size()>0) {
			deleteResult= roleAuthDao.deleteAuthList(deleteList);
			roleid=deleteList.get(0).getRoleid();
		}
		//如果这个角色的权限更新成功，则将该角色下的所有用户的权限缓存移除
		if (insertResult&&deleteResult) {
			List<AccountInfo> roleAccounts=accountInfoDao.getByRoleID(roleid);
			for (AccountInfo accountInfo : roleAccounts) {
				String key=RedissCacheKey.Menu_Auth+accountInfo.getId();
				redisService.remove(key);
			}
		}
		return true;
	}


}
