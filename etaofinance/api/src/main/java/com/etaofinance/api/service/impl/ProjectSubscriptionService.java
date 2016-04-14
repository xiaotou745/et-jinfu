package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.IProjectSubscriptionDao;
import com.etaofinance.api.service.inter.IProjectSubscriptionService;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.ProjectSubscription;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectMember;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.PagedProjectSubReq;
import com.etaofinance.entity.req.ProSubInvestReq;



@Service
public class ProjectSubscriptionService implements  IProjectSubscriptionService{


	@Autowired
	private IProjectSubscriptionDao projectSubscriptionDao;

	@Override
	public List<ProjectSubscriptionDM> getListMore(ProSubInvestReq record) {
		return projectSubscriptionDao.getListMore(record);
	}
	/**
	 * 获取投资人列表
	 */
	@Override
	public List<ProjectMember> getProjectLeadMember(Long projectId) 
	{
		return projectSubscriptionDao.getProjectLeadMember(projectId);
	}

	@Override
	public PagedResponse<ProjectSubscription> getProjectSubPageList(
			PagedProjectSubReq req) {
		return projectSubscriptionDao.getProjectSubPageList(req);
	}

}
