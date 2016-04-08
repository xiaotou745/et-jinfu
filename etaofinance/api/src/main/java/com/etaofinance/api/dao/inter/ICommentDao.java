package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.Comment;

public interface ICommentDao {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyAndMem(Comment record);
    
    int updateByPrimaryKey(Comment record);
}