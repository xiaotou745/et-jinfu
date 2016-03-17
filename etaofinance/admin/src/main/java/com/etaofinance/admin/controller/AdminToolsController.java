package com.etaofinance.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.admin.common.MenuHelper;
import com.etaofinance.admin.common.UserContext;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.ICustomerSqlService;
import com.etaofinance.api.service.inter.IGlobalConfigService;
import com.etaofinance.api.service.inter.IMenuInfoService;
import com.etaofinance.core.util.JsonUtil;
import com.etaofinance.entity.GlobalConfig;
import com.etaofinance.entity.MenuInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.GlobalConfigModel;
import com.etaofinance.entity.domain.MenuEntity;
import com.etaofinance.entity.req.ConfigSaveReq;
import com.etaofinance.entity.req.PagedGlobalConfigReq;

@Controller
@RequestMapping("admintools")
public class AdminToolsController {
	@Autowired
	private RedisService redisService;
	@Autowired
	private IGlobalConfigService globalConfigService;

	@Autowired
	private IMenuInfoService menuInfoService;
	@Autowired
	private ICustomerSqlService customerSqlService;

	/**
	 * 根据key模糊查询或精确查询
	 * 
	 * @param key
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("redismanage")
	public ModelAndView redisManage() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "管理员");
		view.addObject("currenttitle", "redis查询工具");
		view.addObject("viewPath", "admintools/redismanage");
		return view;
	}

	/**
	 * 根据key模糊查询或精确查询
	 * 
	 * @param key
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("redisdo")
	@ResponseBody
	public String redisdo(String key, String sType) {
		if (sType.equals("1")) {
			Set<String> mSet = redisService.keys(key);
			return String.join(",", mSet);
		} else {
			try {
				Object m = redisService.get(key, Object.class, false);
				if (m == null) {
					return "";
				}
				if (m instanceof List) {
					List<Object> mt = (ArrayList<Object>) m;
					List<String> jsonList = mt.stream()
							.map(t -> JsonUtil.obj2string(t))
							.collect(Collectors.toList());
					return String.join(",", jsonList);
				} else {
					return JsonUtil.obj2string(m);
				}
			} catch (Exception e) {
				return "值无法反序列化" + e.getMessage();
			}

		}
	}

	@RequestMapping("redisremove")
	@ResponseBody
	public Integer redisRemove(String key) {
		if (key == null || key.isEmpty()) {
			return 0;
		}
		redisService.remove(key,false);
		return 1;
	}

	/**
	 * 菜单管理
	 * 
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("menulist")
	public ModelAndView menuList(Integer parId) {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "管理员");
		view.addObject("currenttitle", "菜单管理");
		view.addObject("viewPath", "admintools/menulist");
		List<MenuEntity> menuList = menuInfoService.getListMenuAll();
		String menuJson = MenuHelper.getAuthJson(menuList);
		view.addObject("menuJson", menuJson);
		return view;
	}

	/**
	 * 菜单详情
	 * 
	 * @author 茹化肖
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("menudetail")
	@ResponseBody
	public String menudetail(int parId) {
		MenuInfo detail = menuInfoService.getMenuDetail(parId);
		return JsonUtil.obj2string(detail);
	}

	/**
	 * 添加菜单
	 * 
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("addnewmenu")
	@ResponseBody
	public int addNewMenu(HttpServletRequest request, MenuInfo req) {
		UserContext context = UserContext.getCurrentContext(request);
		req.setCreatename(context.getUserName());
		req.setOptname(context.getUserName());
		return menuInfoService.addMenu(req);
	}

	/**
	 * 编辑菜单
	 * 
	 * @author 茹化肖
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("editmenu")
	@ResponseBody
	public int updateMenu(HttpServletRequest request, MenuInfo req) {
		UserContext context = UserContext.getCurrentContext(request);
		req.setCreatename(context.getUserName());
		req.setOptname(context.getUserName());
		return menuInfoService.updateMenu(req);
	}

	/**
	 * 公共配置
	 * 
	 * @param request
	 * @param res
	 * @return
	 */
	@RequestMapping("globalconfig")
	public ModelAndView globalConfigManager(HttpServletRequest request,
			HttpServletResponse res) {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "公共配置管理");
		model.addObject("viewPath", "admintools/globalconfig");
		return model;
	}

	/**
	 * 公共配置分页列表
	 * 
	 * @param searchWebReq
	 * @param request
	 * @return
	 */
	@RequestMapping("globalconfigmanagerlistdo")
	public ModelAndView listdo(PagedGlobalConfigReq searchWebReq,
			HttpServletRequest request) {
		ModelAndView view = new ModelAndView(
				"admintools/globalconfigmanagerlistdo");
		PagedResponse<GlobalConfigModel> resp = globalConfigService
				.getPagedGlobalConfigModels(searchWebReq);
		view.addObject("listData", resp);
		return view;
	}

	/* 保存修改全局变量值 */
	@RequestMapping(value = "saveconfig", method = RequestMethod.POST)
	@ResponseBody
	public int saveConfig(ConfigSaveReq par) {
		return globalConfigService.update(par);

	}

	/* 添加全局变量值 */
	@RequestMapping("addconfig")
	@ResponseBody
	public int addConfig(GlobalConfig par) {
		return globalConfigService.insert(par);
	}

	/**
	 * sql查询工具
	 * 
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("sql")
	public ModelAndView sql() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "管理员");
		view.addObject("currenttitle", "sql工具");
		view.addObject("viewPath", "admintools/sql");
		return view;
	}

	/**
	 * 执行sql语句 茹化肖 2015年12月2日10:14:18
	 * 
	 * @return
	 */
	@RequestMapping("execsql")
	@ResponseBody
	public String execSQl(String sql, String type, String appName) {
		try {
			if (type.equals("1")) {// 查询SQL
				if (sql.toUpperCase().indexOf("UPDATE ") >= 0) {
					return "查询时SQL不可以带UPDATE关键字";
				}
				if (sql.toUpperCase().indexOf("INSERT ") >= 0) {
					return "查询时SQL不可以带INSERT关键字";
				}
				if (sql.toUpperCase().indexOf("DELETE ") >= 0) {
					return "查询时SQL不可以带DELETE关键字";
				}
				if (sql.toUpperCase().indexOf(" LIMIT ") < 0) {
					return "查询时SQL必须加LIMIT关键字";
				}
				return list2Table(customerSqlService.select(sql));
			}
			if (type.equals("2")) {
				// 非查询SQL
				int res = customerSqlService.update(sql);
				return "执行成功,影响行数:" + res;
			}
			return "";
		} catch (Exception e) {
			return "出错了!!!错误信息:" + e.getMessage();
		}
	}

	private String list2Table(List<Map<String, String>> listMap) {
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder
				.append("<table class=\"table table-striped table-bordered table-hover dataTables-example\"><thead>");
		if (listMap.size() == 0) {
			return "没有查到数据";
		}
		Map<String, String> map = listMap.get(0);
		Set<String> sets = map.keySet();// 获取所有的key
		sbBuilder.append("<tr>");
		for (String str : sets) {// 生成表头
			sbBuilder.append("<th>" + str + "</th>");
		}
		sbBuilder.append("</tr>");
		sbBuilder.append("</thead><tbody>");
		for (Map<String, String> maps : listMap) {// 生成行数据
			sbBuilder.append("<tr>");
			for (String key : sets) {
				sbBuilder.append("<td>" + String.valueOf(maps.get(key))
						+ "</td>");
			}
			sbBuilder.append("</tr>");
		}
		sbBuilder.append("</tbody></table>");
		return sbBuilder.toString();
	}
}
