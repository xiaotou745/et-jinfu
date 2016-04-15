package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.ProjectImage;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectComment;
import com.etaofinance.entity.req.PagedCommentReq;

public interface IProjectImageService {

	List<ProjectImage> getByProjectId(long projectId);

	
	
	
	
}
