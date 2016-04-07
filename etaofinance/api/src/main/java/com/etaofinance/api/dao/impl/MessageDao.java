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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Message record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Message selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Message record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Message record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Message> getList(Long memberid) {
		 List<Message> list=null;
		 list=getReadOnlySqlSessionUtil()
				.selectList(
						"IMessageDao.getList",memberid);
		 
		 return list;
	}
}
