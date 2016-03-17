package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.QA;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedQAReq;

public interface IQADao {
    int deleteByPrimaryKey(Integer id);

    int insert(QA record);

    int insertSelective(QA record);

    QA selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QA record);

    int updateByPrimaryKey(QA record);
    
    PagedResponse<QA>  query(PagedQAReq req);
}