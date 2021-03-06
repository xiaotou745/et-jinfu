package com.etaofinance.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IProjectSubscriptionDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.ProjectSubscription;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.domain.ProjectMember;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedProjectSubReq;
import com.etaofinance.entity.req.ProSubInvestReq;
import com.mongodb.util.Hash;
@Repository
public class ProjectSubscriptionDao extends DaoBase implements IProjectSubscriptionDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ProjectSubscription record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ProjectSubscription record) {
		
		return getMasterSqlSessionUtil().insert("IProjectSubscriptionDao.insertSelective", record);
	}

	@Override
	public ProjectSubscription selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ProjectSubscription record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ProjectSubscription record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProjectSubscriptionDM> getListMore(ProSubInvestReq record) {
		 List<ProjectSubscriptionDM> list=null;
		 list=getReadOnlySqlSessionUtil()
				.selectList(
						"IProjectSubscriptionDao.getListMore",record);
		 
		 return list;
	}

	@Override
	public PagedResponse<ProjectSubscription> getProjectSubPageList(
			PagedProjectSubReq req) {
		// TODO Auto-generated method stub
		return getReadOnlySqlSessionUtil().selectPageList("IProjectSubscriptionDao.getProjectSubList",req);
	}
	/**
	 * ��ȡ��Ŀ��ͷ����Ϣ
	 */
	@Override
	public List<ProjectMember> getProjectLeadMember(Long projectId) {
		return getReadOnlySqlSessionUtil().selectList("IProjectSubscriptionDao.getProjectLeadMember",projectId);
 
	}
	/**
	 * 该项目是否已经认购
	 */
	@Override
	public int isMyHave(Long pid, Long uid) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pid", pid);
		map.put("uid", uid);
		return getMasterSqlSessionUtil().selectOne("IProjectSubscriptionDao.isMyHave",map);
	}	

}
