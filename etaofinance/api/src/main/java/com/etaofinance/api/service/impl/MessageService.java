package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.dao.inter.IBankDao;
import com.etaofinance.api.dao.inter.IMessageDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.api.service.inter.IBankService;
import com.etaofinance.api.service.inter.IMessageService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.ADVertEnum;
import com.etaofinance.core.enums.returnenums.HttpReturnRnums;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.Message;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.resp.ADVertResp;


@Service
public class MessageService implements IMessageService{


	@Autowired
	private IMessageDao messageDao;

	@Override
	public List<Message> getList(Long memberid) {
		return messageDao.getList(memberid);
	}

	@Override
	public HttpResultModel<Object> readMsg(Message msg) {
	
		HttpResultModel<Object> res = new HttpResultModel<Object>();
		
		res.setCode(HttpReturnRnums.Fail.value());
		
		res.setMsg(HttpReturnRnums.Fail.desc());

		if(null==msg || "null" == String.valueOf(msg.getId())){
			return res;
		}
		
		msg.setIsread(true);
		int  updateRes = this.messageDao.updateByPrimaryKeySelective(msg);
		
		if(0!=updateRes){
			res.setCode(HttpReturnRnums.Success.value());
			res.setMsg(HttpReturnRnums.Success.desc());
		}
		res.setData(updateRes);
		return res;
		
	}

	@Override
	public HttpResultModel<Object> delMsg(Message msg) {
		HttpResultModel<Object> delRes =new HttpResultModel<Object>();
		
		delRes.setCode(HttpReturnRnums.Fail.value());
		
		delRes.setMsg(HttpReturnRnums.Fail.desc());

		if(null==msg || "null" == String.valueOf(msg.getId())){
			return delRes;
		}
		msg.setIsdel(true);
		int  updateRes = this.messageDao.updateByPrimaryKeySelective(msg);
		
		if(0!=updateRes){
			delRes.setCode(HttpReturnRnums.Success.value());
			delRes.setMsg(HttpReturnRnums.Success.desc());
		}
		delRes.setData(updateRes);
		return delRes;
	}

	@Override
	public HttpResultModel<Object> addMsg(Message msg) {
		
		HttpResultModel<Object> res = new HttpResultModel<Object>();
		
		res.setCode(HttpReturnRnums.Fail.value());
		
		res.setMsg(HttpReturnRnums.Fail.desc());

		if(null==msg){
			return res;
		}
		
		int  insertRes = this.messageDao.insert(msg);;
		
		if(0!=insertRes){
			res.setCode(HttpReturnRnums.Success.value());
			res.setMsg(HttpReturnRnums.Success.desc());
		}
		res.setData(insertRes);
		return res;
	}

	@Override
	public HttpResultModel<Object> addMsgSelective(Message msg) {
	HttpResultModel<Object> res = new HttpResultModel<Object>();
		
		res.setCode(HttpReturnRnums.Fail.value());
		
		res.setMsg(HttpReturnRnums.Fail.desc());

		if(null==msg){
			return res;
		}
		
		int  insertRes = this.messageDao.insertSelective(msg);
		
		if(0!=insertRes){
			res.setCode(HttpReturnRnums.Success.value());
			res.setMsg(HttpReturnRnums.Success.desc());
		}
		res.setData(insertRes);
		return res;
	}

	@Override
	public List<Message> getList(Message record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message getByid(Long id) {
		return messageDao.selectByPrimaryKey(id);
	}
	
}
