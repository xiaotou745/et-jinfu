package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.GlobalConfig;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.GlobalConfigModel;
import com.etaofinance.entity.req.ConfigSaveReq;
import com.etaofinance.entity.req.PagedGlobalConfigReq;


public interface IGlobalConfigDao {
	List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id);
	GlobalConfigModel getGlobalConfigByPrimaryId(Integer id);
	int update(ConfigSaveReq par);
	int insert(GlobalConfig par);	
	PagedResponse<GlobalConfigModel> getPagedGlobalConfigModels(PagedGlobalConfigReq search);
	String getValueByName(String name);
//	GlobalGroupConfigModel GlobalConfigMethod(int groupId);
	
}