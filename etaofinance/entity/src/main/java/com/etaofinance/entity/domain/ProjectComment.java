package com.etaofinance.entity.domain;

import com.etaofinance.entity.Comment;

public class ProjectComment extends Comment{

	private String commontName;
	private String replayName;
	private String headImage;
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getCommontName() {
		return commontName;
	}
	public void setCommontName(String commontName) {
		this.commontName = commontName;
	}
	public String getReplayName() {
		return replayName;
	}
	public void setReplayName(String replayName) {
		this.replayName = replayName;
	}
}
