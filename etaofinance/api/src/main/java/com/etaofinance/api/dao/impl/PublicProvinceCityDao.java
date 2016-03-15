package com.etaofinance.api.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IPublicProvinceCityDao;
import com.etaofinance.entity.PublicProvinceCity;
import com.etaofinance.entity.domain.ModifyCityDomain;
import com.etaofinance.entity.domain.OpenCityModel;
import com.etaofinance.entity.req.HotAndPublicCityReq;
import com.etaofinance.entity.req.ModifyCityReq;
@Repository
public class PublicProvinceCityDao extends DaoBase implements
		IPublicProvinceCityDao {

	@Override
	public List<OpenCityModel> getOpenCityList(HotAndPublicCityReq hotAndPublicCityReq) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IPublicProvinceCityDao.getOpenCityList",
						hotAndPublicCityReq);
	}

	@Override
	public List<PublicProvinceCity> getAllOpenCity() {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IPublicProvinceCityDao.getAllOpenCity");
	}

	@Override
	public int modifyCity(ModifyCityDomain modifyCityReq) {
		return getMasterSqlSessionUtil().update("IPublicProvinceCityDao.modifyCity", modifyCityReq);
	}


}
