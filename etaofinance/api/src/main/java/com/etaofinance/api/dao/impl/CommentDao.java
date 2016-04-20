package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.ICommentDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.Comment;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MenuEntity;
import com.etaofinance.entity.domain.ProjectComment;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedCommentReq;
import com.etaofinance.entity.req.PagedProjectCommentReq;

@Repository
public class CommentDao extends DaoBase implements ICommentDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}
	//用户插入项目评论
	@Override
	public int insert(Comment comment) {
		return getMasterSqlSessionUtil().insert("ICommentDao.insert", comment);
	}

	@Override
	public int insertSelective(Comment record) {
		
		return getMasterSqlSessionUtil().insert("ICommentDao.insertSelective", record);
	}

	@Override
	public Comment selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Comment record) {
	
		
		int res =0;
		
		res = this.getMasterSqlSessionUtil().update("ICommentDao.updateByPrimaryKeySelective", record);
		return res;
		
	}
	
	@Override
	public int updateByPrimaryKeyAndMem(Comment record) {
		return getMasterSqlSessionUtil().update(
				"ICommentDao.updateByPrimaryKeyAndMem", record);
	}
	@Override
	public int updateByPrimaryKey(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public PagedResponse<Comment> getCommentPagingList(PagedCommentReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("ICommentDao.getCommentListPaging",req);
	}
	
	/**
	 * 分页获取评论列表
	 */
	@Override
	public PagedResponse<ProjectComment> getProjectComment(PagedProjectCommentReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("ICommentDao.getProjectComment", req);
	}

}
