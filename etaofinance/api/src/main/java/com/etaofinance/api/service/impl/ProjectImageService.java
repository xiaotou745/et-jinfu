package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.impl.ProjectImageDao;
import com.etaofinance.api.dao.inter.IProjectImageDao;
import com.etaofinance.api.service.inter.IProjectImageService;
import com.etaofinance.entity.ProjectImage;

@Service
public class ProjectImageService implements IProjectImageService{

	@Autowired
	private IProjectImageDao projectImageDao;
	@Override
	public List<ProjectImage> getByProjectId(long projectId) {
		return projectImageDao.getByProjectId(projectId);
	}
}
