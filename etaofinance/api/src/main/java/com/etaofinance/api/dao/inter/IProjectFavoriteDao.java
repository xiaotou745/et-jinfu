package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.FeedBack;
import com.etaofinance.entity.ProjectFavorite;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectFavoriteDM;
import com.etaofinance.entity.domain.ProjectFavoriteInvestModel;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.PagedFeedBackReq;
import com.etaofinance.entity.req.PagedProjectFavReq;
import com.etaofinance.entity.req.ProFavoriteReq;

public interface IProjectFavoriteDao {
    int deleteByPrimaryKey(Long id);

    // 插入一条记录
    int insert(ProjectFavorite record);

    int insertSelective(ProjectFavorite record);

    ProjectFavorite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectFavorite record);

    int updateByPrimaryKey(ProjectFavorite record);
    
    List<ProjectFavoriteDM> getListMore(ProFavoriteReq record);
    
    
    int getFavoriteCntByProId(Long proId);
    
    PagedResponse<ProjectFavoriteInvestModel> getFavoritePageList(PagedProjectFavReq req);
    
}