package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;

public interface IADVertDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ADVert record);

    int insertSelective(ADVert record);

    ADVert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ADVert record);

    int updateByPrimaryKey(ADVert record);
    
    PagedResponse<ADVert>  query(PagedADVertReq req);
}