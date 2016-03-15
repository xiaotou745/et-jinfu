package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.IMenuInfoDao;
import com.etaofinance.api.service.inter.IMenuInfoService;
import com.etaofinance.entity.MenuInfo;
import com.etaofinance.entity.domain.MenuDetail;
import com.etaofinance.entity.domain.MenuEntity;

@Service
public class MenuInfoService implements IMenuInfoService {
	@Autowired
	private IMenuInfoDao dao;
	
	@Override
	public List<MenuInfo> getMenuListByUserID(int userID) {
		return dao.getMenuListByUserID(userID);
	}

	@Override
	public boolean checkHasAuth(int userID,int menuID) {
		List<MenuInfo> data=dao.getMenuListByUserID(userID);
		if (data!=null&&data.size()>0) {
			for (MenuInfo menuEntity : data) {
				if (menuID==menuEntity.getId()) {
					return true;
				}
			}
		}

		return false;
	}
	/**
	 * 根据AuthCode判断用户是否有该权限
	 * 2015年12月2日14:42:12
	 * 茹化肖
	 */
	@Override
	public boolean checkHasAuthByCode(int userID,String authCode) {
		List<MenuInfo> data=dao.getMenuListByUserID(userID);
		if (data!=null&&data.size()>0) {
			for (MenuInfo menuEntity : data) {
				if (authCode.equals(menuEntity.getAuthcode())) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public List<MenuEntity> getAuthSettingList(int userID) {
		return dao.getAuthSettingList(userID);
	}

	@Override
	public List<MenuEntity> getMenuListByRoleID(int roleID) {
		return dao.getMenuListByRoleID(roleID);
	}

	@Override
	public List<MenuInfo> getListMenuByParId(int parId) {
		return dao.getListMenuByParId(parId);
	}

	@Override
	public MenuInfo getMenuById(int id) {
		return dao.getMenuById(id);
	}

	@Override
	public List<MenuEntity> getListMenuAll() {
return dao.getListMenuAll();
	}

	@Override
	public int addMenu(MenuInfo record) {
return dao.addMenu(record);
	}

	@Override
	public int updateMenu(MenuInfo record) {
return dao.updateMenu(record);
	}

	@Override
	public MenuDetail getMenuDetail(int id) {
return dao.getMenuDetail(id);
	}




}
