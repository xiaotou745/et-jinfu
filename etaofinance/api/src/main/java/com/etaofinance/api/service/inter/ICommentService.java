package com.etaofinance.api.service.inter;

import com.etaofinance.entity.Comment;
import com.etaofinance.entity.FeedBack;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.domain.ProjectComment;
import com.etaofinance.entity.req.PagedCommentReq;
import com.etaofinance.entity.req.PagedFeedBackReq;
import com.etaofinance.entity.req.PagedProjectCommentReq;

public interface ICommentService {
	int deleteByPrimaryKey(Long id);

	HttpResultModel<Object> insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    HttpResultModel<Object> updateByPrimaryKeyAndMem(Comment record);
    
    PagedResponse<Comment> getCommentPagingList(PagedCommentReq req);
    
	/**
	 * 分页获取评论列表(WAP)
	 * @param req
	 * @return
	 */
	PagedResponse<ProjectComment> getProjectComment(PagedProjectCommentReq req);
}
