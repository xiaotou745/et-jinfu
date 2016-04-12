package com.etaofinance.entity.domain;

import com.etaofinance.entity.Member;

public class ProjectFavoriteInvestModel extends Member {
	
    private Long memberid;
    
    private Long projectcount;
    
    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }
    
    public Long GetProjectCount()
    {
    	return this.projectcount;
    }
    
    public void setProjectCount(Long projectcount)
    {
    	this.projectcount=projectcount;
    }
}
