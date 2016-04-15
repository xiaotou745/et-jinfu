package com.etaofinance.entity.domain;

import com.etaofinance.entity.Comment;

public class ProjectComment extends Comment{

	private String commontName;
	private String replayName;
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
