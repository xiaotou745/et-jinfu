package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.IPublicProvinceCityDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IPublicProviceCityService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.entity.PublicProvinceCity;

@Service
public class PublicProviceCityService implements IPublicProviceCityService {

	@Autowired
	IPublicProvinceCityDao publicProvinceCityDao;
	@Autowired
	RedisService redisService;

	/**
	 * 获取开通城市
	 * */
	@Override
	public List<PublicProvinceCity> getAllOpenCity() {
		// TODO Auto-generated method stub
		String key = RedissCacheKey.PublicProvinceCity;
		@SuppressWarnings("unchecked")
		List<PublicProvinceCity> list = (List<PublicProvinceCity>) redisService
				.get(key, PublicProvinceCity.class);
//		list=null;
		if (list == null) {
			list = publicProvinceCityDao.getAllOpenCity();
			redisService.set(key, list);
		}
		return list;
	}

}
