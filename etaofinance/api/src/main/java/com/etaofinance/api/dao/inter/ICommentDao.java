package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.Comment;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectComment;
import com.etaofinance.entity.req.PagedCommentReq;
import com.etaofinance.entity.req.PagedProjectCommentReq;

public interface ICommentDao {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyAndMem(Comment record);
    
    int updateByPrimaryKey(Comment record);
    
    PagedResponse<Comment> getCommentPagingList(PagedCommentReq req);
    /**
	 * 分页获取评论列表
	 * @param req
	 * @return
	 */
	PagedResponse<ProjectComment> getProjectComment(PagedProjectCommentReq req);
}