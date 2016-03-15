package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IMenuInfoDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.entity.MenuInfo;
import com.etaofinance.entity.domain.MenuDetail;
import com.etaofinance.entity.domain.MenuEntity;


@Repository
public class MenuInfoDao extends DaoBase implements
		IMenuInfoDao {
	@Autowired
	private RedisService redisService;


	@Override
	public List<MenuInfo> getMenuListByUserID(int accountId) {
		String key=RedissCacheKey.Menu_Auth+accountId;
		List<MenuInfo> result=redisService.get(key, List.class);
		if (result==null||result.size()==0) {
			List<MenuInfo> list = getReadOnlySqlSessionUtil()
					.selectList(
							"IMenuInfoDao.getMenuListByUserID",
							accountId);
			redisService.set(key, list);
			return list;
		}

		return result;
	}

	@Override
	public List<MenuEntity> getAuthSettingList(int userID) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IMenuInfoDao.getAuthSettingList",
						userID);
	}

	@Override
	public List<MenuEntity> getMenuListByRoleID(int roleID) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IMenuInfoDao.getMenuListByRoleID",
						roleID);
	}

	@Override
	public List<MenuInfo> getListMenuByParId(int parId) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IMenuInfoDao.getListMenuByParId",
						parId);
	}

	@Override
	public MenuInfo getMenuById(int id) {
		return getReadOnlySqlSessionUtil()
				.selectOne(
						"IMenuInfoDao.getMenuById",
						id);
	}

	@Override
	public List<MenuEntity> getListMenuAll() {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IMenuInfoDao.getListMenuAll");
	}

	@Override
	public int addMenu(MenuInfo record) {
		return getMasterSqlSessionUtil().insert("IMenuInfoDao.addMenu", record);
	}

	@Override
	public int updateMenu(MenuInfo record) {
return getMasterSqlSessionUtil().update("IMenuInfoDao.updateMenu", record);
	}

	@Override
	public MenuDetail getMenuDetail(int id) {
		return getReadOnlySqlSessionUtil()
				.selectOne(
						"IMenuInfoDao.getMenuDetail",id);
	}

}
