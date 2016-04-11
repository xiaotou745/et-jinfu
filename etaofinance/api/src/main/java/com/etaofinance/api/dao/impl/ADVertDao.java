package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
@Repository
public class ADVertDao extends DaoBase implements IADVertDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return getMasterSqlSessionUtil().delete("IADVertDao.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(ADVert record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ADVert record) {
		return getMasterSqlSessionUtil().insert(
				"IADVertDao.insertSelective", record);
	}

	@Override
	public ADVert selectByPrimaryKey(Integer id) {
		return getReadOnlySqlSessionUtil().selectOne("IADVertDao.selectByPrimaryKey",id);
	}

	@Override
	public int updateByPrimaryKeySelective(ADVert record) {
		return getMasterSqlSessionUtil().update("IADVertDao.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(ADVert record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public PagedResponse<ADVert>  query(PagedADVertReq req)
	{
		return getReadOnlySqlSessionUtil().selectPageList(
				"IADVertDao.query", req);
	}
	/**
	 * 获取轮播图列表
	 */
	@Override
	public List<ADVert> getListForWap() {
		return getReadOnlySqlSessionUtil().selectList(
				"IADVertDao.getListForWap");
	}

}
