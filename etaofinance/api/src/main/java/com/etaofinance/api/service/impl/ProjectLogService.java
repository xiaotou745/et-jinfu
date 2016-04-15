package com.etaofinance.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.IProjectLogDao;
import com.etaofinance.api.service.inter.IProjectLogService;
import com.etaofinance.entity.ProjectLogModel;
@Service
public class ProjectLogService implements IProjectLogService{
	
	@Autowired
	private IProjectLogDao projectLogDao;
	@Override
	public int insert(ProjectLogModel req) {
		return projectLogDao.insert(req);
	}
}
