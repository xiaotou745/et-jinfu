package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.Recharge;
import com.etaofinance.entity.Withdrawform;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedRechargeReq;
import com.etaofinance.entity.req.PagedWithdrawReq;

public interface IRechargeDao {
    int deleteByPrimaryKey(Long id);

    int insert(Recharge record);

    int insertSelective(Recharge record);

    Recharge selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Recharge record);

    int updateByPrimaryKey(Recharge record);
    
	PagedResponse<Recharge> getRechargeList(PagedRechargeReq req);
}