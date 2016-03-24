package com.etaofinance.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.IMemberDao;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.req.PagedMemberReq;

@Service
public class MemberService implements IMemberService{
	@Autowired
	private IMemberDao memberDao;
 	@Override
	public Member selectByPhoneNo(String phoneno) {
		return memberDao.selectByPhoneNo(phoneno);
	}
	@Override
	public PagedResponse<MemberModel> getMemberList(PagedMemberReq req) {
		return memberDao.getMemberList(req);
	}

}
