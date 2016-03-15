package com.etaofinance.admin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.admin.common.MenuHelper;
import com.etaofinance.admin.common.UserContext;
import com.etaofinance.api.service.inter.IAccountInfoService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.api.service.inter.IMenuInfoService;
import com.etaofinance.api.service.inter.IRoleInfoService;
import com.etaofinance.core.util.StringUtils;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.MenuInfo;
import com.etaofinance.entity.RoleAuth;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.domain.MenuEntity;
import com.etaofinance.entity.req.PagedAccountInfoReq;

@Controller
@RequestMapping("authmanage")
public class AuthManageController {
	@Autowired
	private IAccountInfoService accountService;
	@Autowired
	private IMenuInfoService authorityMenuClassService;
	@Autowired
	private IAccountAuthService authorityAccountMenuSetService;
	@Autowired
	private IRoleInfoService authorityRoleService;
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "个人账户权限管理");
		model.addObject("viewPath", "authmanage/list");
		List<RoleInfo> datalist=authorityRoleService.selectList();
		model.addObject("roleData", datalist);
		return model;
	}

	@RequestMapping("listdo")
	public ModelAndView listdo(PagedAccountInfoReq req) {
		PagedResponse<AccountInfo> resp = accountService.queryAccount(req);
		ModelAndView model = new ModelAndView("authmanage/listdo");
		model.addObject("listData", resp);
		return model;
	}
	@RequestMapping("getroleid")
	@ResponseBody
	public int getRoleID(int userID) {
		AccountInfo userAccount = accountService.getByID(userID);
		if (userAccount!=null) {
			return userAccount.getRoleid();
		}
		return -1;
	}
	@RequestMapping("updateroleid")
	@ResponseBody
	public int updateRoleID(int userID,int newRoleID) {
		return accountService.updateRoleID(userID, newRoleID);
	}
	@RequestMapping(value = "authlist", produces= "application/json; charset=utf-8")
	@ResponseBody
	public String getAuthList(int userID) {
		List<MenuEntity> menuList = authorityMenuClassService.getAuthSettingList(userID);
		return MenuHelper.getAuthJson(menuList);
	}

	@RequestMapping("saveauth")
	@ResponseBody
	public String saveauth(HttpServletRequest request,int userID, String newAuth, String oldAuth) {
		List<String> newList = new ArrayList<>();
		List<String> oldList = new ArrayList<>();
		List<String> diffList = new ArrayList<>();
		if (newAuth != null && !newAuth.isEmpty()) {
			Collections.addAll(newList, newAuth.split(","));
			Collections.addAll(diffList, newAuth.split(","));
		}
		if (oldAuth != null && !oldAuth.isEmpty()) {
			Collections.addAll(oldList, oldAuth.split(","));
		}

		diffList.removeAll(oldList);// diffList中剩余的是新增的权限id
		oldList.removeAll(newList);// oldList中剩余的是被删掉的权限id
	
		if (diffList.size() == 0&&oldList.size()==0) {
			return "";
		}
		List<AccountAuth> insertList = new ArrayList<>();
		List<AccountAuth> deleteList = new ArrayList<>();
		UserContext context = UserContext.getCurrentContext(request);
		for (String authid : diffList) {
			AccountAuth authset = new AccountAuth();
			authset.setAccoutid(userID);
			authset.setCreatename(context.getUserName());
			authset.setMenuid(Integer.parseInt(authid));
			insertList.add(authset);
		}
		for (String authid : oldList) {
			AccountAuth authset = new AccountAuth();
			authset.setAccoutid(userID);
			authset.setCreatename(context.getUserName());
			authset.setMenuid(Integer.parseInt(authid));
			deleteList.add(authset);
		}
		authorityAccountMenuSetService.modifyAuthList(insertList,deleteList);

		return "";
	}
}
