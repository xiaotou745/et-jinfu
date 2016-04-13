package com.etaofinance.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etaofinance.admin.common.UploadFileHelper;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("upload")
public class UploadController {
	
	/**
	 * 图像上传
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("img")
	@ResponseBody
	public String img(HttpServletRequest request) throws Exception {
	return UploadFileHelper.UploadImg(request);
	}
	/**
	 * 文件上传
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("file")
	@ResponseBody
	public String file(HttpServletRequest request) throws Exception {
	return UploadFileHelper.UploadImg(request);
	}

}
