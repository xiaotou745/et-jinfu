package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.IProjectImageDao;
import com.etaofinance.api.service.inter.IProjectImageService;
import com.etaofinance.entity.ProjectImage;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectComment;
import com.etaofinance.entity.req.PagedCommentReq;

@Service
public class ProjectImageService implements IProjectImageService{

	@Autowired
	private IProjectImageDao projectImageDao;
	@Override
	public List<ProjectImage> getByProjectId(long projectId) {
		return projectImageDao.getByProjectId(projectId);
	}
	
}
