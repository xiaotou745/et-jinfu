package com.etaofinance.entity.domain;

public class ModifyProjectImg {
	private int modifyId;
	
	private int modifyType;

	public int getModifyId() {
		return modifyId;
	}

	public void setModifyId(int modifyId) {
		this.modifyId = modifyId;
	}

	/*
	 * 1:表示修改project表中的项目图，2表示修改projectimage表中的图片
	 */
	public int getModifyType() {
		return modifyType;
	}

	public void setModifyType(int modifyType) {
		this.modifyType = modifyType;
	}
}
