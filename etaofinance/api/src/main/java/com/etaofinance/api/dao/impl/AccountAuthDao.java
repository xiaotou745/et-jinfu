package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.entity.AccountAuth;


/**
 * 用户菜单设置
 * @author pengyi
 * @date 20150828
 *
 */
@Repository
public class AccountAuthDao extends DaoBase implements IAccountAuthDao{

	@Override
	public List<Integer> getMenuIdsByAccountId(Integer id) {
		return getReadOnlySqlSessionUtil().selectList(
				"IAccountAuthDao.getMenuIdsByAccountId",id);
	}

	@Override
	public boolean deleteAuthList(List<AccountAuth> authList) {
		return getMasterSqlSessionUtil()
				.delete("IAccountAuthDao.deleteAuthList",
						authList) > 0;
	}

	@Override
	public boolean insertAuthList(List<AccountAuth> authList) {
		return getMasterSqlSessionUtil()
				.insert("IAccountAuthDao.insertAuthList",
						authList) > 0;
	}

}
