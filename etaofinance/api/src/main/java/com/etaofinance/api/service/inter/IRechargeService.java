package com.etaofinance.api.service.inter;

import com.etaofinance.entity.Recharge;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedRechargeReq;

public interface IRechargeService {
    int deleteByPrimaryKey(Long id);

    int insert(Recharge record);

    int insertSelective(Recharge record);

    Recharge selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Recharge record);

    int updateByPrimaryKey(Recharge record);
    
	PagedResponse<Recharge> getRechargeList(PagedRechargeReq req);
	
	/**
	 * 充值
	 * @param 
	 * @return
	 */
	HttpResultModel<Object> recharge(Recharge recharge);
}