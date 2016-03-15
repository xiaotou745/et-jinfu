package com.etaofinance.api.service.inter;

import java.util.List;
import java.util.Map; 

import com.etaofinance.core.enums.AreaLevel;
import com.etaofinance.entity.PublicProvinceCity;
import com.etaofinance.entity.domain.OpenCityModel;
import com.etaofinance.entity.req.HotAndPublicCityReq;
import com.etaofinance.entity.req.ModifyCityReq;





/**
 * 省市区通用  业务逻辑层  
 * @author CaoHeYang
 * 20150720
 */
public interface IPublicProvinceCityService {

	/**
	 * 获取开放城市列表（非分页）
	 * @author CaoHeYang 
	 * @param cityName 城市名称
	 */
	List<OpenCityModel> getOpenCityList(HotAndPublicCityReq hotAndPublicCityReq);
	 
	/**
	 * 从Redis获取开放城市列表（非分页）
	 * @author zhaohailong
	 * @param cityName 城市名称
	 */
	 List<PublicProvinceCity> getOpenCityListFromRedis();
	 
	 Map<Integer,String> getOpenCityMap();
	 Map<Integer,PublicProvinceCity> getOpenCityDetailMap();
	 /**
	  * 获取开通市
	  * 赵海龙(1是国家，2是省份，3是城市级别，4是区域)
	  * 2015年7月29日 10:40:36
	  * */
	 List<PublicProvinceCity> getOpenCityByJiBie(AreaLevel jiBie);
	 
	 
	/**
	 * 根据城市Id获取对应的区县列表
	 * @author zhaohailong
	 */
	 List<PublicProvinceCity> getOpenCityDistrict(int cityId);
	 /*
	  * 修改城市
	  * wangchao
	  */
	int modifyCity(ModifyCityReq modifyCityReq);

}
