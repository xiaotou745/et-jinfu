package com.etaofinance.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.common.TransactionalRuntimeException;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.dao.inter.IBalanceRecordDao;
import com.etaofinance.api.dao.inter.IBankDao;
import com.etaofinance.api.dao.inter.IMemberOtherDao;
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
import com.etaofinance.core.enums.BalanceRecordType;
import com.etaofinance.core.enums.RechargeStatus;
import com.etaofinance.core.enums.returnenums.HttpReturnRnums;
import com.etaofinance.core.util.OrderNoHelper;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.Message;
import com.etaofinance.entity.Recharge;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedRechargeReq;
import com.etaofinance.entity.resp.ADVertResp;

@Service
public class RechargeService implements IRechargeService {

	@Autowired
	private IRechargeDao rechargeDao;

	@Autowired
	private IBalanceRecordDao balanceRecordDao;

	@Autowired
	private IMemberOtherDao memberOtherDao;

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
	
		
		return rechargeDao.insertSelective(record);
		
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
		
	/**
	 * 充值
	 */	
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public HttpResultModel<Object> recharge(Recharge record) {
		HttpResultModel<Object> res = new HttpResultModel<Object>();
		if(record.getAccounttype()==null || record.getAccounttype().equals(""))
			record.setAccounttype(1);
		
		//充值表
		Recharge charge = new Recharge();
		String uuid=OrderNoHelper.generatePrefixNoCode("CZ", record.getMemberid().intValue());
		charge.setNo(uuid);
		charge.setAccounttype(record.getAccounttype());
		charge.setAmount(record.getAmount());
		charge.setCreatename(record.getCreatename());
		charge.setMemberid( record.getMemberid());
		charge.setOptname(record.getCreatename());
		charge.setRemark("充值");
		charge.setStatus((short) RechargeStatus.Success.value());		
		int insertRechargeRes = this.insertSelective(charge);
		if (0 == insertRechargeRes) {
			throw new TransactionalRuntimeException("充值表异常");
		}
		
		//余额表
		MemberOther mbOther = new MemberOther();		
		mbOther.setMemberid(record.getMemberid());		
		mbOther.setBalanceprice(record.getAmount());			
		int updateMemberOterRes = memberOtherDao.updateMemberOther(mbOther);
		if (0 == updateMemberOterRes) {

			throw new TransactionalRuntimeException("余额表异常");
		}

		//余额流水表
		BalanceRecord balance =new BalanceRecord();	
		balance.setAmount(record.getAmount());	
		balance.setMemberid(record.getMemberid());
		balance.setWithwardid((long)0);
		balance.setRemark("充值流水");
		balance.setTypeid((short) BalanceRecordType.Recharge.value());
		balance.setRelationno(uuid);
		balance.setOptname(record.getCreatename());

		int insertBalanceRes = balanceRecordDao.insertBalanceRecord(balance);		
		if (0 == insertBalanceRes) {

			throw new TransactionalRuntimeException("流水表异常");
		}

		res.setCode(HttpReturnRnums.Success.value());
		res.setMsg(HttpReturnRnums.Success.desc());		
		String basePath = PropertyUtils.getProperty("java.wap.url");
		basePath+="/me/accountblance";	
		res.setUrl(basePath);		
		return res;
	}

}
