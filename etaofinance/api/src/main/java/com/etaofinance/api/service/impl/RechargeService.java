package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.dao.inter.IBankDao;
import com.etaofinance.api.dao.inter.IMessageDao;
import com.etaofinance.api.dao.inter.IProjectDao;
import com.etaofinance.api.dao.inter.IRechargeDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.api.service.inter.IBankService;
import com.etaofinance.api.service.inter.IMessageService;
import com.etaofinance.api.service.inter.IRechargeService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.ADVertEnum;
import com.etaofinance.core.enums.returnenums.HttpReturnRnums;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.Message;
import com.etaofinance.entity.Recharge;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedRechargeReq;
import com.etaofinance.entity.resp.ADVertResp;


@Service
public class RechargeService implements IRechargeService{

	
	@Autowired
	private IRechargeDao rechargeDao;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Recharge record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Recharge record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Recharge selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Recharge record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Recharge record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PagedResponse<Recharge> getRechargeList(PagedRechargeReq req) {
		return rechargeDao.getRechargeList(req);
	}


	
}
