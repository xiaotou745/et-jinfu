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
import com.etaofinance.api.service.inter.IMenuInfoService;
import com.etaofinance.api.service.inter.IRoleAuthService;
import com.etaofinance.api.service.inter.IRoleInfoService;
import com.etaofinance.entity.MenuInfo;
import com.etaofinance.entity.RoleAuth;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.domain.MenuEntity;
import com.etaofinance.entity.req.PagedAccountInfoReq;

@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	private IMenuInfoService menuInfoService;
	@Autowired
	private IRoleInfoService roleInfoService;
	@Autowired
	private IRoleAuthService roleAuthService;
	
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "角色管理");
		model.addObject("viewPath", "role/list");
		return model;
	}

	@RequestMapping("listdo")
	public ModelAndView listdo(PagedAccountInfoReq req) {
		ModelAndView model = new ModelAndView("role/listdo");
		List<RoleInfo> datalist=roleInfoService.selectList();
		model.addObject("listData", datalist);
		return model;
	}
	@RequestMapping("add")
	@ResponseBody
	public int add(HttpServletRequest request,String roleName) {
		
		
		// 先校验roleName 是否已经存在
		RoleInfo role = roleInfoService.getRoleInfoByName(roleName);
		
		if(null!=role){
			return -1;
		}
		
		UserContext context = UserContext.getCurrentContext(request);
		RoleInfo record=new RoleInfo();
		record.setIslock(false);
		record.setOptname(context.getUserName());
		record.setCreatename(context.getUserName());
		record.setRolename(roleName);
		
		return roleInfoService.insert(record);
	}
	@RequestMapping(value = "authlist", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAuthList(int roleID) {
		List<MenuEntity> menuList = menuInfoService.getMenuListByRoleID(roleID);
		return MenuHelper.getAuthJson(menuList);
	}

	@RequestMapping("saveauth")
	@ResponseBody
	public String saveauth(HttpServletRequest request,int roleID, String newAuth, String oldAuth) {
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
		
		if (diffList.size() == 0&&oldList.size() == 0) {
			return "没有任何修改，不需要保存";
		}
		List<RoleAuth> insertList = new ArrayList<>();
		List<RoleAuth> deleteList = new ArrayList<>();
		UserContext context = UserContext.getCurrentContext(request);
		for (String authid : diffList) {
			RoleAuth authset = new RoleAuth();
			authset.setRoleid(roleID);
			authset.setCreatename(context.getUserName());
			authset.setMenuid(Integer.parseInt(authid));
			insertList.add(authset);
		}
		for (String authid : oldList) {
			RoleAuth authset = new RoleAuth();
			authset.setRoleid(roleID);
			authset.setCreatename(context.getUserName());
			authset.setMenuid(Integer.parseInt(authid));
			deleteList.add(authset);
		}
		roleAuthService.modifyAuthList(deleteList,insertList);

		return "";
	}

	@RequestMapping("saverole")
	@ResponseBody
	public int saverole(HttpServletRequest request,int roleID,int islock,String newName) {
		UserContext context = UserContext.getCurrentContext(request);
		RoleInfo record=new RoleInfo();
		record.setId(roleID);
		record.setOptname(context.getUserName());
		record.setIslock(islock==1);
		record.setRolename(newName);
		return roleInfoService.update(record);
	}
}
