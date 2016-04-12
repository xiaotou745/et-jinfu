package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IMemberApplyDao;
import com.etaofinance.entity.MemberApply;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberApplyAuditModel;
import com.etaofinance.entity.domain.MemberApplyInvestModel;
import com.etaofinance.entity.req.MemberApplyAuditReq;
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
		return getMasterSqlSessionUtil().insert("IMemberApplyDao.insertSelective", record);	
		}

	@Override
	public MemberApply selectByPrimaryKey(Long id) {
	
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
	public MemberApply selectPending(long memberId) {
		return getMasterSqlSessionUtil().selectOne("IMemberApplyDao.selectPending", memberId);
	}
	
	@Override
	public PagedResponse<MemberApplyInvestModel> getMemberApplyList(
			PagedMemberReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IMemberApplyDao.getMemberApplyList", req);
	}

	@Override
	public MemberApplyAuditModel getMemberApplyInfo(long memberApplyId) {
		return getReadOnlySqlSessionUtil().selectOne("IMemberApplyDao.getMemberApplyInfo",memberApplyId);
	}

	@Override
	public int auditConfirm(MemberApplyAuditReq req) {
		return getMasterSqlSessionUtil().update("IMemberApplyDao.auditConfirm",req);
	}

	@Override
	public List<MemberApply> getMemberApplyInfoByMemberId(long memberId) {
		return getReadOnlySqlSessionUtil().selectList("IMemberApplyDao.getMemberApplyInfoByMemberId",memberId);
	}
	/**
	 * 是否存在未审核的申请
	 */
	@Override
	public boolean IsHasApply(Long uid) {
		int res =getReadOnlySqlSessionUtil().selectOne("IMemberApplyDao.IsHasApply",uid);
		return res>0;
	}

}
