package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.Member;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.req.ModifyMemberReq;
import com.etaofinance.entity.req.PagedMemberReq;

public interface IMemberDao {
    int deleteByPrimaryKey(Long id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Long id);
    /**
     * 事务用到
     * @param id
     * @return
     */
    Member selectById(Long id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
    
	/**
	 * 通过用户名查询用户
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
    Member selectByUserName(String username);
    
    /**
	 * 通过用email查询用户
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
    Member selectByemail(String email);
    
    /**
     * 通过手机号获取会员信息
     * @param phoneno
     * @return
     */
    Member selectByPhoneNo(String  phoneno);

	PagedResponse<MemberModel> getMemberList(PagedMemberReq req);

	int modifyMember(ModifyMemberReq req);
}