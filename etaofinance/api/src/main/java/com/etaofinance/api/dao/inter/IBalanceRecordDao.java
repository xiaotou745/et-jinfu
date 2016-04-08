package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.req.PagedBalancerecordReq;
import com.etaofinance.entity.req.PagedMemberBalanceRecordReq;

public interface IBalanceRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(BalanceRecord record);

    int insertSelective(BalanceRecord record);

    BalanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BalanceRecord record);

    int updateByPrimaryKey(BalanceRecord record);
    
    List<BalanceRecordDM> getListMore(BalanceRecord record);
    
    BalanceRecordDM selectDMByPrimaryKey(Long id);

	PagedResponse<BalanceRecord> getPageList(PagedMemberBalanceRecordReq req);

	PagedResponse<BalanceRecord> getBalanceRecordList(PagedBalancerecordReq req);
}