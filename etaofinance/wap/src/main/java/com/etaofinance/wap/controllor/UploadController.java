package com.etaofinance.wap.controllor;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etaofinance.wap.common.UploadFileHelper;
@Controller
@RequestMapping("upload")
public class UploadController {
	
	
	@RequestMapping("img")
	@ResponseBody
	public String img(HttpServletRequest request) throws Exception {
	return UploadFileHelper.UploadImg(request);
	}

}