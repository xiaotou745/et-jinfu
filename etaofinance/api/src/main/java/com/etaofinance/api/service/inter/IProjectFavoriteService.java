package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.FeedBack;
import com.etaofinance.entity.ProjectFavorite;
import com.etaofinance.entity.ProjectSubscription;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectFavoriteDM;
import com.etaofinance.entity.domain.ProjectFavoriteInvestModel;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedAccountInfoReq;
import com.etaofinance.entity.req.PagedFeedBackReq;
import com.etaofinance.entity.req.PagedProjectFavReq;
import com.etaofinance.entity.req.ProFavoriteReq;
import com.etaofinance.entity.req.UpdatePwdReq;
import com.etaofinance.entity.resp.*;


public interface IProjectFavoriteService {
   
	  // 插入一条记录
	int insert(ProjectFavorite record);
	   int insertSelective(ProjectFavorite record);

	List<ProjectFavoriteDM> getListMore(ProjectFavoriteDM record);
	List<ProjectFavoriteDM> getListMore(ProFavoriteReq record);

	int updateByPrimaryKeySelective(ProjectFavorite record);

	int updateByPrimaryKey(ProjectFavorite record);

	HttpResultModel<Object> followProject(ProjectFavorite profavorite);

	HttpResultModel<Object> followByPrimaryKeySelective(
			ProjectFavorite profavorite);

	HttpResultModel<Object> follow(ProjectFavorite profavorite);

	
	int getFavoriteCntByProId(Long proId);
	
	PagedResponse<ProjectFavoriteInvestModel> getFavoritePageList(PagedProjectFavReq req);
	/**
	 * 项目是否已关注
	 * @param uid
	 * @param pid
	 * @return
	 */
	int isMyFavorite(Long uid,Long pid);
}
