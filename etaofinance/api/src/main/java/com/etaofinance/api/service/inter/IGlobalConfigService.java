package com.etaofinance.api.service.inter;

import com.etaofinance.entity.GlobalConfig;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.GlobalConfigModel;
import com.etaofinance.entity.req.ConfigSaveReq;
import com.etaofinance.entity.req.PagedGlobalConfigReq;



public interface IGlobalConfigService {
	 int update(ConfigSaveReq par);
	 String getConfigValueByKey(int groupID,String key);
	 int insert(GlobalConfig par);
	 PagedResponse<GlobalConfigModel> getPagedGlobalConfigModels(PagedGlobalConfigReq search);
	 /**
	  * 通过名称获取键值
	  * @param nema
	  * @return
	  */
	 String getValueByName(String nema);
}
