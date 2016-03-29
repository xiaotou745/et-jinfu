package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.IMemberApplyDao;
import com.etaofinance.api.service.inter.IMemberApplyService;
import com.etaofinance.entity.MemberApply;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberApplyAuditModel;
import com.etaofinance.entity.domain.MemberApplyInvestModel;
import com.etaofinance.entity.req.MemberApplyAuditReq;
import com.etaofinance.entity.req.PagedMemberReq;

@Service
public class MemberApplyService implements IMemberApplyService{

	@Autowired
	private IMemberApplyDao memberApplyDao;
	
	@Override
	public PagedResponse<MemberApplyInvestModel> getMemberApplyList(
			PagedMemberReq req) {
		return memberApplyDao.getMemberApplyList(req);
	}

	@Override
	public MemberApplyAuditModel getMemberApplyInfo(long memberApplyId) {
		 return memberApplyDao.getMemberApplyInfo(memberApplyId);
	}

	@Override
	public int auditConfirm(MemberApplyAuditReq req) {
		return memberApplyDao.auditConfirm(req);
	}
	/*
	 * 根据会员id，获取会员申请的最近两条记录，一条是领投人信息 ，一条是跟投人信息 wangchao
	 */
	@Override
	public List<MemberApply> getMemberApplyInfoByMemberId(long memberId) {
		return memberApplyDao.getMemberApplyInfoByMemberId(memberId);
	}

}
