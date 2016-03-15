package com.etaofinance.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.etaofinance.entity.req.PagedMemberReq;

@Controller
@RequestMapping("member")
public class MemberController {
	
	/*
	 * 会员列表 wangchao
	 */
	@RequestMapping("memberlist")
	public ModelAndView memberlist() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "会员管理");
		model.addObject("currenttitle", "会员列表");
		model.addObject("viewPath", "member/memberlist");
		return model;
	}

	@RequestMapping("memberlistdo")
	public ModelAndView memberlistdo(PagedMemberReq req) { 
		ModelAndView model = new ModelAndView("member/memberlistdo");
		return model;
	}
	
	/*
	 * 跟投人列表 wangchao
	 */
	@RequestMapping("followinvestlist")
	public ModelAndView followinvestlist() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "会员管理");
		model.addObject("currenttitle", "跟投人列表");
		model.addObject("viewPath", "member/followinvestlist");
		return model;
	} 
	@RequestMapping("followinvestlistdo")
	public ModelAndView followinvestlistdo(PagedMemberReq req) { 
		ModelAndView model = new ModelAndView("member/followinvestlistdo");
		return model;
	}
	
	/*
	 * 领投人列表 wangchao
	 */
	@RequestMapping("leadinvestlist")
	public ModelAndView leadinvestlist() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "会员管理");
		model.addObject("currenttitle", "领投人列表");
		model.addObject("viewPath", "member/leadinvestlist");
		return model;
	} 
	@RequestMapping("leadinvestlistdo")
	public ModelAndView leadinvestlistdo(PagedMemberReq req) { 
		ModelAndView model = new ModelAndView("member/leadinvestlistdo");
		return model;
	}
}
