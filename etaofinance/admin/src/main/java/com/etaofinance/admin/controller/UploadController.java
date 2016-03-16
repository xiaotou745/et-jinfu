package com.etaofinance.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etaofinance.admin.common.UploadFileHelper;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("upload")
public class UploadController {
	
	
	@RequestMapping("img")
	@ResponseBody
	public String img(HttpServletRequest request) throws Exception {
	return UploadFileHelper.UploadImg(request);
	}

}
