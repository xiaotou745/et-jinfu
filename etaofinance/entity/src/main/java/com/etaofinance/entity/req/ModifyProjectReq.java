package com.etaofinance.entity.req;

import java.util.List;
 

import com.etaofinance.entity.domain.ModifyProjectImg;
import com.etaofinance.entity.domain.PublishProjectReq;

public class ModifyProjectReq extends PublishProjectReq{
	
	private List<ModifyProjectImg> modifyProjectImgList;

	public List<ModifyProjectImg> getModifyProjectImgList() {
		return modifyProjectImgList;
	}

	public void setModifyProjectImgList(List<ModifyProjectImg> modifyProjectImgList) {
		this.modifyProjectImgList = modifyProjectImgList;
	}
}
	
	
