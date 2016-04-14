package com.etaofinance.entity.req;

import com.etaofinance.entity.common.PagedRequestBase;

public class PagedProjectCommentReq extends PagedRequestBase{
private Long projectId;
private Long memberId;
public Long getProjectId() {
	return projectId;
}
public void setProjectId(Long projectId) {
	this.projectId = projectId;
}
public Long getMemberId() {
	return memberId;
}
public void setMemberId(Long memberId) {
	this.memberId = memberId;
}
}
