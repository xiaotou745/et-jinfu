package com.etaofinance.api.service.inter;

import com.etaofinance.entity.Comment;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.ResponseBase;

public interface ICommentService {
	int deleteByPrimaryKey(Long id);

	HttpResultModel<Object> insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    HttpResultModel<Object> updateByPrimaryKeyAndMem(Comment record);
}
