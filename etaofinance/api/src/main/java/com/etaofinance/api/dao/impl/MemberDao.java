package com.etaofinance.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IMemberDao;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.req.PagedMemberReq;
@Repository
public class MemberDao  extends DaoBase implements IMemberDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Member record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Member record) {
		return getMasterSqlSessionUtil().insert("IMemberDao.insertSelective", record);
	}

	@Override
	public Member selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Member record) {
		return getMasterSqlSessionUtil().update("IMemberDao.updateByPrimaryKeySelective", record);		
	
	}

	@Override
	public int updateByPrimaryKey(Member record) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 通过手机号获取会员信息
	 */
	@Override
	public Member selectByPhoneNo(String phoneno) {
		return getReadOnlySqlSessionUtil().selectOne("IMemberDao.selectByPhoneNo", phoneno);
	}

	@Override
	public PagedResponse<MemberModel> getMemberList(PagedMemberReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IMemberDao.getMemberList",req);
	}

}
