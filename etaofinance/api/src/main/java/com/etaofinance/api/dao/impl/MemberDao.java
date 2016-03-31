package com.etaofinance.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IMemberDao;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.req.ModifyMemberReq;
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
		
		return getReadOnlySqlSessionUtil().selectOne("IMemberDao.selectByPrimaryKey",id);
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
	 * 通过用户名查询用户
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
	@Override
    public Member selectByUserName(String username)
    {
		return getReadOnlySqlSessionUtil().selectOne("IMemberDao.selectByUserName", username);
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
	/**
	 * 通过手机号查询会员信息
	 */
	@Override
	public Member selectByemail(String email) {
		return getReadOnlySqlSessionUtil().selectOne("IMemberDao.selectByemail", email);
	}
	/**
	 * 事务用到
	 */
	@Override
	public Member selectById(Long id) {
		return getMasterSqlSessionUtil().selectOne("IMemberDao.selectByPrimaryKey",id);
	}

	@Override
	public int modifyMember(ModifyMemberReq req) {
		return getMasterSqlSessionUtil().update("IMemberDao.modifyMember",req);
	}

}
