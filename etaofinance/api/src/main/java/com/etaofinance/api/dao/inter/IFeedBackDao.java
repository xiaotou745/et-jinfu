package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.FeedBack;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedFeedBackReq;

public interface IFeedBackDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FeedBack record);

    int insertSelective(FeedBack record);

    FeedBack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FeedBack record);

    int updateByPrimaryKey(FeedBack record);
    
    PagedResponse<FeedBack> getFeedBackList(PagedFeedBackReq req);
}