package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.AdminOptLog;
import com.etaofinance.entity.FeedBack;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedAdminoptLogReq;
import com.etaofinance.entity.req.PagedFeedBackReq;

public interface IAdminOptLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(AdminOptLog record);

    int insertSelective(AdminOptLog record);

    AdminOptLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminOptLog record);

    int updateByPrimaryKey(AdminOptLog record);
    
    PagedResponse<AdminOptLog> getAdminOptLogList(PagedAdminoptLogReq req);
}