package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedAccountInfoReq;
import com.etaofinance.entity.req.UpdatePwdReq;
import com.etaofinance.entity.resp.*;


public interface IADVertService {
   

    ADVert selectByPrimaryKey(Integer id);
    
	int deleteByPrimaryKey(Integer id) ;

    
	PagedResponse<ADVert>  query(PagedADVertReq req);
	
	HttpResultModel<ADVertResp> create(ADVert record);
		

	HttpResultModel<ADVertResp> modify(ADVert record);
	/**
	 * 获取首页轮播图
	 * @return
	 */
	List<ADVert> getListForWap();

}
