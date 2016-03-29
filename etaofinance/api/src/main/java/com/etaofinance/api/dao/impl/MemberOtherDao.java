package com.etaofinance.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IMemberDao;
import com.etaofinance.api.dao.inter.IMemberOtherDao;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.req.PagedMemberReq;
@Repository
public class MemberOtherDao  extends DaoBase implements IMemberOtherDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MemberOther record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(MemberOther record) {
		return getMasterSqlSessionUtil().insert("IMemberOtherDao.insertSelective",record);
	}

	@Override
	public MemberOther selectByPrimaryKey(Long id) {
		return getMasterSqlSessionUtil().selectOne("IMemberOtherDao.selectByPrimaryKey",id);
	}

	@Override
	public int updateByPrimaryKeySelective(MemberOther record) {
		return getMasterSqlSessionUtil().update("IMemberOtherDao.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(MemberOther record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByMemberIdSelective(MemberOther record)
	{
		return getMasterSqlSessionUtil().update("IMemberOtherDao.updateByMemberIdSelective",record);
	}
	
}
