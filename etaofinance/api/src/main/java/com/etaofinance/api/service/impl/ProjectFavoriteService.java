package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.common.TransactionalRuntimeException;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.dao.inter.IBankDao;
import com.etaofinance.api.dao.inter.IProjectDao;
import com.etaofinance.api.dao.inter.IProjectFavoriteDao;
import com.etaofinance.api.dao.inter.IProjectSubscriptionDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.api.service.inter.IBankService;
import com.etaofinance.api.service.inter.IProjectFavoriteService;
import com.etaofinance.api.service.inter.IProjectSubscriptionService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.ADVertEnum;
import com.etaofinance.core.enums.returnenums.HttpReturnRnums;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.ProjectFavorite;
import com.etaofinance.entity.ProjectSubscription;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectFavoriteDM;
import com.etaofinance.entity.domain.ProjectFavoriteInvestModel;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedProjectFavReq;
import com.etaofinance.entity.req.ProFavoriteReq;
import com.etaofinance.entity.resp.ADVertResp;


@Service
public class ProjectFavoriteService implements  IProjectFavoriteService{


	@Autowired
	private IProjectFavoriteDao projectFavoriteDao;
	@Autowired
	private IProjectDao projectDao;
	/**
	 * 我关注的项目列表
	 */
	@Override
	public List<ProjectFavoriteDM> getListMore(ProFavoriteReq record) {
		return projectFavoriteDao.getListMore(record);
	}

	@Override
	public int insert(ProjectFavorite record) {
		
		return projectFavoriteDao.insert(record);
		
	}

	@Override
	public HttpResultModel<Object> followProject(ProjectFavorite proFavorite) {
	
		
		HttpResultModel<Object> followRes=new HttpResultModel<Object>();
		
		followRes.setCode(HttpReturnRnums.Fail.value());
		followRes.setMsg(HttpReturnRnums.Fail.desc());
		
		int insertRes =this.insertSelective(proFavorite);
		
		//int followedCnt = this.getFavoriteCntByProId(proFavorite.getProjectid());
		
		//followRes.setData(followRes);
		
		if(0!=insertRes)
		{	
			followRes.setCode(HttpReturnRnums.Success.value());
			followRes.setMsg(HttpReturnRnums.Success.desc());
		}
		
		return followRes;
		
	}

	@Override
	public HttpResultModel<Object> followByPrimaryKeySelective(
			ProjectFavorite profavorite) {
		
		HttpResultModel<Object> followRes=new HttpResultModel<Object>();
		
		followRes.setCode(HttpReturnRnums.Fail.value());
		followRes.setMsg(HttpReturnRnums.Fail.desc());
		
	
		//int followedCnt = this.getFavoriteCntByProId(profavorite.getProjectid());
		
		//followRes.setData(followRes);
		
		int updateRes =this.updateByPrimaryKeySelective(profavorite);
		
		if(0!=updateRes)
		{	
			followRes.setCode(HttpReturnRnums.Success.value());
			followRes.setMsg(HttpReturnRnums.Success.desc());
		}
		
		return followRes;
	}

	@Override
	public int updateByPrimaryKeySelective(ProjectFavorite record) {

		return projectFavoriteDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ProjectFavorite record) {
	
		return projectFavoriteDao.updateByPrimaryKey(record);
	}

	@Override
	public int getFavoriteCntByProId(Long proId) {
		
		return projectFavoriteDao.getFavoriteCntByProId(proId);
	}
	/**
	 * 关注 取消关注
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public HttpResultModel<Object> follow(ProjectFavorite profavorite) {
		HttpResultModel<Object> followRes = new HttpResultModel<Object>();		
		if(profavorite.getIsdel()==0)//增加关注
		{
			projectFavoriteDao.cancelFavorite(profavorite.getMemberid(), profavorite.getProjectid());
			profavorite.setIsdel(null);
			int r1=projectFavoriteDao.insertSelective(profavorite);
			int r2=projectDao.changeFlowNum(profavorite.getProjectid(), 1);
			if (r1 != 1 || r2 != 1)// 每个操作都应该只有一条影响
			{
				throw new TransactionalRuntimeException("关注失败!");
			}
		}else {
			int r1=projectDao.changeFlowNum(profavorite.getProjectid(), -1);
			int r2=projectFavoriteDao.cancelFavorite(profavorite.getMemberid(), profavorite.getProjectid());
			if (r1 != 1)// 每个操作都应该只有一条影响
			{
				throw new TransactionalRuntimeException("取消关注失败!");
			}
		}
		followRes.setCode(1);
		followRes.setMsg("操作成功!");
		return followRes;
	}

	@Override
	public int insertSelective(ProjectFavorite record) {
		
		return projectFavoriteDao.insertSelective(record);
	}

	@Override
	public List<ProjectFavoriteDM> getListMore(ProjectFavoriteDM record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagedResponse<ProjectFavoriteInvestModel> getFavoritePageList(
			PagedProjectFavReq req) {
		return projectFavoriteDao.getFavoritePageList(req);
	}

	@Override
	public int isMyFavorite(Long uid, Long pid) {
		return projectFavoriteDao.isMyFavorite( uid,  pid);
	}

}
