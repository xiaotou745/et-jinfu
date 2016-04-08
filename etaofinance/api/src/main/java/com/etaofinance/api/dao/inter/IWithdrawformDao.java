package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Withdrawform;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.domain.WithdrawformDM;
import com.etaofinance.entity.req.PagedWithdrawReq;
import com.etaofinance.entity.req.PublicMemberReq;
public interface IWithdrawformDao {
    int deleteByPrimaryKey(Long id);

    int insert(Withdrawform record);

    int insertSelective(Withdrawform record);

    Withdrawform selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Withdrawform record);

    int updateByPrimaryKey(Withdrawform record);
    
    List<WithdrawformDM> getListMore(PublicMemberReq record);
    
    WithdrawformDM selectDMByPrimaryKey(Long id);

	PagedResponse<Withdrawform> getWithdrawList(PagedWithdrawReq req);
}