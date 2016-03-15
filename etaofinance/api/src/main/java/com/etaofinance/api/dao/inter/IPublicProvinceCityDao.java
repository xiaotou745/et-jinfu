package com.etaofinance.api.dao.inter;
import java.util.List;

import com.etaofinance.entity.PublicProvinceCity;
import com.etaofinance.entity.domain.ModifyCityDomain;
import com.etaofinance.entity.domain.OpenCityModel;
import com.etaofinance.entity.req.HotAndPublicCityReq;
import com.etaofinance.entity.req.ModifyCityReq;

public interface IPublicProvinceCityDao {    
	/**
	 * 获取开放城市列表（非分页）
	 * @author CaoHeYang 
	 */
	List<OpenCityModel> getOpenCityList(HotAndPublicCityReq  hotAndPublicCityReq);

   
	/**
	 * 获取开通城市的省市区 
	 * @author CaoHeYang
	 * @Date 20150727
	 * @return 
	 */
	
    List<PublicProvinceCity> getAllOpenCity();


	int modifyCity(ModifyCityDomain modifyCityReq);
	


}