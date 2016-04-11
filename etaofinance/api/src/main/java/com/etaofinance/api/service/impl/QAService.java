package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.dao.inter.IQADao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.api.service.inter.IQAService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.ADVertEnum;
import com.etaofinance.core.enums.QAEnum;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.QA;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedQAReq;
import com.etaofinance.entity.resp.ADVertResp;
import com.etaofinance.entity.resp.QAResp;


@Service
public class QAService implements IQAService{


	@Autowired
	private IQADao qADao;
	
	@Override
	public HttpResultModel<QAResp> create(QA record) {
		HttpResultModel<QAResp> resp = new HttpResultModel<QAResp>();		
		qADao.insertSelective(record);
		resp.setCode(QAEnum.Success.value());
		resp.setMsg(QAEnum.Success.desc());		
		return resp;
	}
	
	@Override
	public int remove (Integer id) {
		return qADao.deleteByPrimaryKey(id);
	}

	@Override
	public  HttpResultModel<QAResp> modify(QA record)
	{
		HttpResultModel<QAResp> resp = new HttpResultModel<QAResp>();
		qADao.updateByPrimaryKeySelective(record);
		resp.setCode(QAEnum.Success.value());
		resp.setMsg(QAEnum.Success.desc());		
		return resp;
	}
	

	@Override
	public QA getById(Integer id) {
		return qADao.selectByPrimaryKey(id);
	}


	@Override
	public PagedResponse<QA> query(PagedQAReq req) {
		return qADao.query(req);
	}
   
	@Override
	public List<QA> getList()
	{
		return qADao.getList();
	}
	/**
	 * Wap常见问题页面
	 */
	@Override
	public List<QA> getListForWap() {
		return qADao.getListForWap();
	}
	


}
