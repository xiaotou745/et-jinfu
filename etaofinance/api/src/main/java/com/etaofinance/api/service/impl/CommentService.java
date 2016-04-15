package com.etaofinance.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.ICommentDao;
import com.etaofinance.api.service.inter.ICommentService;
import com.etaofinance.core.enums.WithdrawformEnum;
import com.etaofinance.entity.Comment;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.domain.ProjectComment;
import com.etaofinance.entity.req.PagedCommentReq;
import com.etaofinance.entity.req.PagedProjectCommentReq;

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
	public HttpResultModel<Object> insert(Comment record) {
		
		HttpResultModel<Object> resp = new HttpResultModel<Object>();	
		
		if(record.getMemberid() ==null && record.getMemberid().equals(""))
		{	
			resp.setCode(WithdrawformEnum.MemberIdIsNull.value());
			resp.setMsg(WithdrawformEnum.MemberIdIsNull.desc());
			return resp;			
		}
		
		//recommentid is null：commnent;
		//recommentid not null:reply;
		if(record.getRecommentid() !=null && !record.getRecommentid().equals(""))
		{
			record.setIsreply((byte)1);
		}
		else
		{
			record.setIsreply((byte)0);
			record.setRecommentid((long)0);
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
	public HttpResultModel<Object> updateByPrimaryKeyAndMem(Comment record) {
		
		HttpResultModel<Object> resp = new HttpResultModel<Object>();	
		
		if((record.getMemberid() ==null && record.getMemberid().equals("")) 
				||
		   (record.getId() ==null && record.getId().equals(""))
		  )
		{	
			resp.setCode(WithdrawformEnum.MemberIdIsNull.value());
			resp.setMsg(WithdrawformEnum.MemberIdIsNull.desc());
			return resp;			
		}
		record.setIsdel((byte)1);
		int row = commentDao.updateByPrimaryKeyAndMem(record);
		
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
	public int updateByPrimaryKey(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int updateByPrimaryKeySelective(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PagedResponse<Comment> getCommentPagingList(PagedCommentReq req) {
		return commentDao.getCommentPagingList(req);
	}
	/**
	 * 分页获取评论列表
	 */
	@Override
	public PagedResponse<ProjectComment> getProjectComment(PagedProjectCommentReq req) {
		return commentDao.getProjectComment(req);
	}

}
