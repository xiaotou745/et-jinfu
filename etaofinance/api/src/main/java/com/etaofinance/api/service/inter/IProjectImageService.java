package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.ProjectImage;

public interface IProjectImageService {

	List<ProjectImage> getByProjectId(long projectId);
	
}
