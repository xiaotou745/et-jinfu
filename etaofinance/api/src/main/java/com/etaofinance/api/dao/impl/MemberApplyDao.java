package com.etaofinance.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IMemberApplyDao;
import com.etaofinance.entity.MemberApply;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberApplyInvestModel;
import com.etaofinance.entity.req.PagedMemberReq;

@Repository
public class MemberApplyDao extends DaoBase implements IMemberApplyDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MemberApply record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(MemberApply record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberApply selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(MemberApply record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(MemberApply record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PagedResponse<MemberApplyInvestModel> getMemberApplyList(
			PagedMemberReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IMemberApplyDao.getMemberApplyList", req);
	}

}
