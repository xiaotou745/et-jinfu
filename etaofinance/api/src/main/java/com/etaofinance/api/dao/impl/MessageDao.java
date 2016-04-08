package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IMessageDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.Message;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
@Repository
public class MessageDao extends DaoBase implements IMessageDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Message record) {
		int insertRes =0;
		
		insertRes =  getMasterSqlSessionUtil().insert("IMessageDao.insert",record);
		
		return insertRes;
	}

	@Override
	public int insertSelective(Message record) {
		
		int insertRes =0;
		
		insertRes =  getMasterSqlSessionUtil().insert("IMessageDao.insertSelective",record);
		
		return insertRes;
	}

	@Override
	public Message selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Message record) {
		
		int updateRes =0;
		
		updateRes =  getMasterSqlSessionUtil().update("IMessageDao.updateByPrimaryKeySelective",record);
		
		return updateRes;
	}

	@Override
	public int updateByPrimaryKey(Message record) {
		int updateRes =0;
		
		updateRes =  getMasterSqlSessionUtil().update("IMessageDao.updateByPrimaryKey",record);
		
		return updateRes;
	}

	@Override
	public List<Message> getList(Message record) {
		 List<Message> list=null;
		 list=getReadOnlySqlSessionUtil()
				.selectList(
						"IMessageDao.getList",record.getMemberid());
		 
		 return list;
	}
}
