package com.etaofinance.entity.req;

import com.etaofinance.entity.common.PagedRequestBase;

/**
 * QA
 * */
public class PagedQAReq extends PagedRequestBase{
String question ;

public String getQuestion() {
	return question;
}

public void setQuestion(String question) {
	this.question = question;
}


}
