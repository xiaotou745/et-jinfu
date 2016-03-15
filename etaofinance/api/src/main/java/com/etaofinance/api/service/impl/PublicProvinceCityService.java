package com.etaofinance.api.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.IPublicProvinceCityDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IPublicProvinceCityService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.AreaLevel;
import com.etaofinance.entity.PublicProvinceCity;
import com.etaofinance.entity.domain.ModifyCityDomain;
import com.etaofinance.entity.domain.OpenCityModel;
import com.etaofinance.entity.req.HotAndPublicCityReq;
import com.etaofinance.entity.req.ModifyCityReq;



@Service
public class PublicProvinceCityService implements IPublicProvinceCityService {
	@Autowired
	private IPublicProvinceCityDao publicProvinceCityDao;
	@Autowired
	private RedisService redisService;

	/**
	 * 获取开放城市列表（非分页）
	 * 
	 * @author CaoHeYang
	 */
	@Override
	public List<OpenCityModel> getOpenCityList(HotAndPublicCityReq hotAndPublicCityReq) {
		return publicProvinceCityDao.getOpenCityList(hotAndPublicCityReq);
	};

	

	
	/**
	 * 获取所有开发省份 城市 区域
	 * 
	 */
	@Override
	public List<PublicProvinceCity> getOpenCityListFromRedis() {
		redisService.remove(RedissCacheKey.RR_PublicProvinceCity);
		List<PublicProvinceCity> listdata=redisService.get(RedissCacheKey.RR_PublicProvinceCity, List.class); 
		if (listdata==null||listdata.size()==0) {
			listdata=publicProvinceCityDao.getAllOpenCity();
			if (listdata!=null&&listdata.size()>0) {
				redisService.set(RedissCacheKey.RR_PublicProvinceCity, listdata,360,TimeUnit.DAYS);
			}
		}
		return listdata;
	}
	/**
	 * 根据级别获取开发城市数据
	 */
	@Override
	public List<PublicProvinceCity> getOpenCityByJiBie(AreaLevel jiBie)
	{
		List<PublicProvinceCity> list = getOpenCityListFromRedis();
		return list
				.stream()
				.filter(k -> k.getJiBie().intValue() == jiBie.value())
				.sorted((a, b) -> a.getFirstLetter().compareTo(
						b.getFirstLetter())).collect(Collectors.toList());
	}
	
	/**
	 * 根据城市Id获取对应的区县列表
	 * @author zhaohailong
	 */
	@Override
	public List<PublicProvinceCity> getOpenCityDistrict(int cityId) {
		List<PublicProvinceCity> list = getOpenCityListFromRedis();
		return list
				.stream()
				.filter(k -> k.getJiBie().intValue() == AreaLevel.District
						.value() && k.getParentCode().intValue() == cityId)
				.collect(Collectors.toList());
	}





	@Override
	public Map<Integer, String> getOpenCityMap() {
		List<PublicProvinceCity> list = getOpenCityListFromRedis();
		Map<Integer, String> listnew = new HashMap<>();

		for (PublicProvinceCity item : list) {
			if (!listnew.containsKey(item.getCode())) {
				listnew.put(item.getCode(), item.getName());
			}
		}
		return listnew;
	}



/**
 * 只保存发生了变更的数据
 */
	@Override
	public int modifyCity(ModifyCityReq modifyCityReq) {
		List<String> oldOpenHotCityCodeList = new ArrayList<String>();
		List<String> oldOpenPublicCityCodeList = new ArrayList<String>();
		List<String> oldCloseHotCityCodeList = new ArrayList<String>();
		List<String> oldClosePublicCityCodeList = new ArrayList<String>();
		if (modifyCityReq.getOldOpenHotCityCodeList().length() > 0) {
			oldOpenHotCityCodeList = Arrays.asList(modifyCityReq
					.getOldOpenHotCityCodeList().split(","));
		}
		if (modifyCityReq.getOldOpenPublicCityCodeList().length() > 0) {
			oldOpenPublicCityCodeList = Arrays.asList(modifyCityReq
					.getOldOpenPublicCityCodeList().split(","));
		}
		if (modifyCityReq.getOldCloseHotCityCodeList().length() > 0) {
			oldCloseHotCityCodeList = Arrays.asList(modifyCityReq
					.getOldCloseHotCityCodeList().split(","));
		}
		if (modifyCityReq.getOldClosePublicCityCodeList().length() > 0) {
			oldClosePublicCityCodeList = Arrays.asList(modifyCityReq
					.getOldClosePublicCityCodeList().split(","));
		}
		ModifyCityDomain modifyCityDomain = new ModifyCityDomain();
		modifyCityDomain.setCityCode(modifyCityReq.getCityCode());
		modifyCityDomain.setProvenceCode(modifyCityReq.getProvenceCode());
		if (modifyCityReq.getOpenPublicCityCodeList().length() > 0) {
			modifyCityDomain.setOpenPublicCityCodeList(new ArrayList<String>());
			Collections.addAll(modifyCityDomain.getOpenPublicCityCodeList(), modifyCityReq.getOpenPublicCityCodeList().split(","));
			modifyCityDomain.getOpenPublicCityCodeList().removeAll(oldOpenPublicCityCodeList);
		}
		if (modifyCityReq.getClosePublicCityCodeList().length() > 0) {
			modifyCityDomain.setClosePublicCityCodeList(new ArrayList<String>());
			Collections.addAll(modifyCityDomain.getClosePublicCityCodeList(), modifyCityReq.getClosePublicCityCodeList().split(","));
			modifyCityDomain.getClosePublicCityCodeList().removeAll(oldClosePublicCityCodeList);
		}
		if (modifyCityReq.getOpenHotCityCodeList().length() > 0) {
			modifyCityDomain.setOpenHotCityCodeList(new ArrayList<String>());
			Collections.addAll(modifyCityDomain.getOpenHotCityCodeList(), modifyCityReq.getOpenHotCityCodeList().split(","));
			modifyCityDomain.getOpenHotCityCodeList().removeAll(oldOpenHotCityCodeList);
		}
		if (modifyCityReq.getCloseHotCityCodeList().length() > 0) {
			modifyCityDomain.setCloseHotCityCodeList(new ArrayList<String>());
			Collections.addAll(modifyCityDomain.getCloseHotCityCodeList(), modifyCityReq.getCloseHotCityCodeList().split(","));
			modifyCityDomain.getCloseHotCityCodeList().removeAll(oldCloseHotCityCodeList);
		}

		int result = publicProvinceCityDao.modifyCity(modifyCityDomain);
		if (result > 0) {
			redisService.remove(RedissCacheKey.RR_PublicProvinceCity);
		}

		return result;
	}




	@Override
	public Map<Integer, PublicProvinceCity> getOpenCityDetailMap() {
		List<PublicProvinceCity> list = getOpenCityListFromRedis();
		Map<Integer, PublicProvinceCity> listnew = new HashMap<>();

		for (PublicProvinceCity item : list) {
			if (!listnew.containsKey(item.getCode())) {
				listnew.put(item.getCode(), item);
			}
		}
		return listnew;
	}




}
