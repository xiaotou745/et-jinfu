package com.etaofinance.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.ICommentDao;
import com.etaofinance.api.service.inter.ICommentService;
import com.etaofinance.core.enums.WithdrawformEnum;
import com.etaofinance.entity.Comment;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.ResponseBase;

@Service
public class CommentService implements ICommentService {
	
	@Autowired
	private ICommentDao commentDao;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HttpResultModel<ResponseBase> insert(Comment record) {
		
		HttpResultModel<ResponseBase> resp = new HttpResultModel<ResponseBase>();	
		
		if(record.getMemberid() ==null && record.getMemberid().equals(""))
		{	
			resp.setCode(WithdrawformEnum.MemberIdIsNull.value());
			resp.setMsg(WithdrawformEnum.MemberIdIsNull.desc());
			return resp;			
		}
		int row = commentDao.insert(record);
		
		if(row<=0)
		{
			resp.setCode(WithdrawformEnum.Err.value());
			resp.setMsg(WithdrawformEnum.Err.desc());
			return resp;	
		}
		resp.setCode(WithdrawformEnum.Success.value());
		resp.setMsg(WithdrawformEnum.Success.desc());		
		return resp;
	}

	@Override
	public int insertSelective(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Comment selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
