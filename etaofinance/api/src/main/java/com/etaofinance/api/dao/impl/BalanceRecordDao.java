package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IBalanceRecordDao;
import com.etaofinance.api.dao.inter.IBankDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.domain.MenuEntity;
import com.etaofinance.entity.req.PagedBalancerecordReq;
import com.etaofinance.entity.req.PagedMemberBalanceRecordReq;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PublicMemberReq;
@Repository
public class BalanceRecordDao extends DaoBase implements IBalanceRecordDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(BalanceRecord record) {
	
		
		return this.getMasterSqlSessionUtil().insert("IBalanceRecordDao.insertSelective",record);
	}

	@Override
	public BalanceRecord selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(BalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BalanceRecordDM> getListMore(PublicMemberReq record) {
		 List<BalanceRecordDM> list=null;
		 list=getReadOnlySqlSessionUtil()
				.selectList(
						"IBalanceRecordDao.getListMore",record.getMemberId());
		 
		 return list;
	}
	
	@Override
	public BalanceRecordDM selectDMByPrimaryKey(Long id)
	{
		return getReadOnlySqlSessionUtil().selectOne("IBalanceRecordDao.selectDMByPrimaryKey",id);
	}

	@Override
	public PagedResponse<BalanceRecord> getPageList(PagedMemberBalanceRecordReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IBalanceRecordDao.getPageList",req);
	}

	@Override
	public PagedResponse<BalanceRecord> getBalanceRecordList(
			PagedBalancerecordReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IBalanceRecordDao.getBuyList", req);
	}

	@Override
	public BalanceRecord GetLatestedModelByMbId(Long memberId) {
		
		BalanceRecord balance = null;
		
		balance = getReadOnlySqlSessionUtil().selectOne("IBalanceRecordDao.GetLatestedModelByMbId",memberId);
				
		return balance;
	}

	

}
