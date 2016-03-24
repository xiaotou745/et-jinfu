package com.etaofinance.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.dao.inter.IBankDao;
import com.etaofinance.api.dao.inter.IFeedBackDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.api.service.inter.IBankService;
import com.etaofinance.api.service.inter.IFeedBackService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.ADVertEnum;
import com.etaofinance.core.enums.PublicEnum;
import com.etaofinance.core.enums.QAEnum;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.FeedBack;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.resp.ADVertResp;
import com.etaofinance.entity.resp.FeedBackResp;
import com.etaofinance.entity.resp.QAResp;


@Service
public class FeedBackService implements IFeedBackService{


	@Autowired
	private IFeedBackDao feedBackDao;

	@Override
	public HttpResultModel<FeedBackResp> create(FeedBack record) {	

		HttpResultModel<FeedBackResp> resp = new HttpResultModel<FeedBackResp>();			
		record.setCreatetime(new Date());
		record.setIsdel(false);	      
		feedBackDao.insert(record);
		resp.setCode(PublicEnum.Success.value());
		resp.setMsg(PublicEnum.Success.desc());		
		return resp;
	}



}
