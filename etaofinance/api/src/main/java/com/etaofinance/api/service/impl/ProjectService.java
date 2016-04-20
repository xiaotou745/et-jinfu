package com.etaofinance.api.service.impl;

import java.nio.file.Path;
import java.sql.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.common.TransactionalRuntimeException;
import com.etaofinance.api.dao.inter.IBalanceRecordDao;
import com.etaofinance.api.dao.inter.IMemberDao;
import com.etaofinance.api.dao.inter.IMemberOtherDao;
import com.etaofinance.api.dao.inter.IProjectDao;
import com.etaofinance.api.dao.inter.IProjectImageDao;
import com.etaofinance.api.dao.inter.IProjectLogDao;
import com.etaofinance.api.dao.inter.IProjectStrategyDao;
import com.etaofinance.api.dao.inter.IProjectSubscriptionDao;
import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.core.enums.MemberTypeEnum;
import com.etaofinance.core.enums.ProjectStatus;
import com.etaofinance.core.enums.RecordType;
import com.etaofinance.core.security.MD5Util;
import com.etaofinance.core.util.ParseHelper;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.core.util.SmsUtils;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.ProjectImage;
import com.etaofinance.entity.ProjectLogModel;
import com.etaofinance.entity.ProjectSubscription;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.DataStatistics;
import com.etaofinance.entity.domain.ModifyProjectImg;
import com.etaofinance.entity.domain.ProjectModel;
import com.etaofinance.entity.domain.PublishProjectReq;
import com.etaofinance.entity.domain.ToDoDataStatistics;
import com.etaofinance.entity.req.ModifyProjectReq;
import com.etaofinance.entity.req.PagedProjectReq;
import com.etaofinance.entity.req.ProLaunchReq;
import com.etaofinance.entity.req.ProjectAuditReq;
import com.etaofinance.entity.req.ProjectStatusReq;
import com.etaofinance.entity.req.SubProjectReq;

@Service
public class ProjectService implements IProjectService {
	@Autowired
	private IProjectDao projectDao;
	@Autowired
	private IProjectStrategyDao projectStrategyDao;
	@Autowired
	private IProjectImageDao projectImageDao;
	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private IMemberOtherDao memberOtherDao;
	@Autowired
	private IProjectSubscriptionDao projectSubscriptionDao;
	@Autowired
	private IBalanceRecordDao blanceRecordDao;
	@Autowired
	private IProjectLogDao projectLogDao;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return projectDao.deleteByPrimaryKey(id);
	}

	@Override
	public long insert(Project record) {
		return projectDao.insert(record);
	}

	@Override
	public Project selectByPrimaryKey(Long id) {
		return projectDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(Project record) {
		return projectDao.updateByPrimaryKey(record);
	}

	@Override
	public PagedResponse<Project> queryProjectList(PagedProjectReq req) {
		
		PagedResponse<Project> res = null;
		
		res= projectDao.queryProjectList(req);
		
		return res;
	}

	/**
	 * Wap获取项目列表 茹化肖
	 */
	@Override
	public PagedResponse<ProjectModel> getProjectList(PagedProjectReq req) {
		return projectDao.getProjectList(req);
	}

	/**
	 * 认购项目 茹化肖
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 300)
	public HttpResultModel<Object> subproject(SubProjectReq req) {
		HttpResultModel<Object> result = new HttpResultModel<Object>();
		Member user = memberDao.selectById(req.getUserId());
		MemberOther userOther = memberOtherDao
				.selectByMemberId(req.getUserId());
		Project project = projectDao.selectByPrimaryKey(req.getProjectId());
		// 1.项目不在预热或进行中或已下架
		if (project == null) {
			result.setCode(-1);
			result.setMsg("该项目暂时不能购买!");
			return result;
		}
		// 2.验证用户支付密码
		if (!MD5Util.MD5(req.getPayPwd()).equals(userOther.getPaypassword())) {// 支付密码不同
			result.setCode(-1);
			result.setMsg("支付密码错误,请重试!");
			return result;
		}
		// 3项目预热中,购买人非领投人
		if (project.getProjectstatus() == ProjectStatus.Preheating.value()
				&& user.getLevel() != MemberTypeEnum.LeadInvestUser.value()) {
			result.setCode(-1);
			result.setMsg("预热中的项目只有领投资格会员可以认购");
			return result;
		}
		// 4项目众筹中,普通用户
		if (project.getProjectstatus() == ProjectStatus.Financeing.value()
				&& user.getLevel() == MemberTypeEnum.CommonUser.value()) {
			result.setCode(-1);
			result.setMsg("您尚未实名认证,请先进行实名认证!");
			return result;
		}
		// 5项目众筹中,实名认证用户
		if (project.getProjectstatus() == ProjectStatus.Financeing.value()
				&& user.getLevel() == MemberTypeEnum.CertificationUser.value()) {
			result.setCode(-1);
			result.setMsg("您尚未进行投资人认证,请先进行投资人认证!");
			return result;
		}

		if (req.getIsLead() == 1) {
			// 领投人
			if (user.getLevel() != MemberTypeEnum.LeadInvestUser.value()) {
				result.setCode(-1);
				result.setMsg("您当前不具备领投资格,请先进行领投人认证!");
				return result;
			}
			// 领投份数小于最低份额
			if (req.getQuantity() < project.getLeadminfenshu()) {
				result.setCode(-1);
				result.setMsg("您领投的份额低于该项目要求的最低领投份!");
				return result;
			}
			// 领投份数大于该项目的剩余可领投份额
			if (req.getQuantity() > (project.getPreheatmaxfenshu() - project
					.getRediduePreheatMaxFenShu())) {
				result.setCode(-1);
				result.setMsg("您领投的份额大于该项目的剩余领投份额!");
				return result;
			}
			// 领投份数大于该项目的剩余可领投份额
			if (req.getQuantity() > (project.getFenshu() - project.getRediduePreheatMaxFenShu())) {
				result.setCode(-1);
				result.setMsg("您领投的份额大于该项目的剩余份额!");
				return result;
			}
		} else {
			// 跟投.
			// 领投份数大于该项目的剩余可领投份额
			if (req.getQuantity() > (project.getFenshu() -project.getRedidueFenshu())) {
				result.setCode(-1);
				result.setMsg("您投资的份额大于该项目的剩余份额!");
				return result;
			}
		}
		// 验证金额
		if (req.getQuantity() * project.getUnitprice() > userOther
				.getBalanceprice()) {
			result.setCode(-1);
			result.setMsg("您认购项目所需金额大于现有余额,请先进行充值!");
			return result;
		}
		return dealSubproject(req, user, userOther, project);
	}

	/**
	 * 进行认购处理
	 * 
	 * @return
	 */
	public HttpResultModel<Object> dealSubproject(SubProjectReq req, Member m,
			MemberOther mo, Project p) {
		// 1.生成购买记录
		ProjectSubscription psr = new ProjectSubscription();
		psr.setMemberid(m.getId());
		psr.setProjectid(p.getId());
		psr.setIslead(req.getIsLead() == 1);
		psr.setAmount(req.getQuantity() * p.getUnitprice());
		psr.setFenshu(req.getQuantity());
		psr.setName(m.getTruename());
		psr.setPhoneno(m.getPhoneno());
		psr.setEmail(m.getEmail());
		psr.setIdcard(m.getIdcard());
		int res1 = projectSubscriptionDao.insertSelective(psr);
		// 2.扣减账户余额
		MemberOther updateOther = new MemberOther();
		// 余额=余额-应付款
		Float yF = (p.getUnitprice() * req.getQuantity());
		Double YR = mo.getBalanceprice();
		updateOther.setBalanceprice(YR - yF);
		updateOther.setAllowwithdrawprice(mo.getAllowwithdrawprice() - yF);
		updateOther.setMemberid(m.getId());
		int res2 = memberOtherDao.updateByMemberIdSelective(updateOther);
		// 3.插入流水.
		BalanceRecord bRecord = new BalanceRecord();
		bRecord.setAmount(-yF);
		bRecord.setMemberid(m.getId());
		bRecord.setAfteramount((float) (YR - yF));
		bRecord.setTypeid(ParseHelper.ToShort(RecordType.Invest.value()));
		bRecord.setProjectid(p.getId());
		bRecord.setRemark("认购项目");
		bRecord.setOptname(m.getTruename());
		int res3 = blanceRecordDao.insertSelective(bRecord);
		// 4.更新项目信息
		Project updateProject = new Project();
		updateProject.setId(p.getId());
		updateProject.setRedidueFenshu(p.getRedidueFenshu() + req.getQuantity());
		updateProject.setInvestmentnumber(p.getInvestmentnumber() + 1);
		// 已购买份数除以份数*100
		int rp = updateProject.getRedidueFenshu()*100 / p.getFenshu();
		updateProject.setSchedule(rp);
		if (req.getIsLead() == 1)// 领投
		{
			// 已购买的份数加上新的份数
			updateProject.setRediduePreheatMaxFenShu(p.getRediduePreheatMaxFenShu() + req.getQuantity());
		}
		int res4 = projectDao.updateByPrimaryKeySelective(updateProject);
		if (res1 != 1 || res2 != 1 || res3 != 1 || res4 != 1)// 每个操作都应该只有一条影响
		{
			throw new TransactionalRuntimeException("认购失败!");
		}
		int year=ParseHelper.GetInDate(new java.util.Date(), 1);
		int month=ParseHelper.GetInDate(new java.util.Date(), 2);
		int day=ParseHelper.GetInDate(new java.util.Date(), 3);
		
		 //您于*年*月*日认购易淘众筹项目“*”，已确认成功，确认金额*元。如有任何疑问，请致电联系客服4000-999-177
		 String content="您于"+year+"年"+month+"月"+day+"日"+"认购易淘众筹项目“"+p.getProjectname()+"”，"
		 		+ "已确认成功，确认金额"+yF+"元。"
		 		+ "如有任何疑问，请致电联系客服4000-999-177";

		// 发送认购短信
		SmsUtils.sendSMS(m.getPhoneno(), content);
		HttpResultModel<Object> rModel = new HttpResultModel<Object>();
		rModel.setCode(1);
		rModel.setMsg("认购成功!");
		rModel.setUrl(PropertyUtils.getProperty("java.wap.url")+"/home/detail?projectid="+p.getId());
		return rModel;
	}

	/**
	 * 我发起的项目
	 * 
	 * @param
	 * @author hulingbo
	 * @date 2016年3月31日11:46:29
	 * @return
	 */
	public List<Project> getListMore(ProLaunchReq record) {
		return projectDao.getListMore(record);
	}

	/*
	 * 发布项目 wangchao
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int publishProject(PublishProjectReq req) {
		// 1.项目主表project
		req.getProject().setCreatename(req.getPublishName());
		// String projectimage =req.getProject().getProjectimage();
		// projectimage=PropertyUtils.getProperty("ImgShowUrl")+projectimage;
		long i = projectDao.insert(req.getProject());
		if (i > 0) {
			// 2.插入策略表 projectstrategy
			req.getProjectStrategyList().forEach(
					action -> action.setProjectid(req.getProject().getId()));
			int proStrategyResult = projectStrategyDao.insertList(req
					.getProjectStrategyList());
			// 3.项目图片表 projectimage
			if (proStrategyResult > 0) {
				req.getProjectImageList()
						.forEach(
								action -> action.setProjectid(req.getProject()
										.getId()));
				int k = projectImageDao.insertList(req.getProjectImageList());
				if (k > 0) {
					return 1;
				}
			}
		}
		return 0;
	}

	/*
	 * 项目审核 通过or拒绝 wangchao
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int audit(ProjectAuditReq req) {
		// 记日志，更新审核状态
		ProjectLogModel pLogModel = new ProjectLogModel();
		pLogModel.setProjectId(req.getProjectId());
		pLogModel.setIsDel(0);
		pLogModel.setOperater(req.getAuditName());
		pLogModel.setRemark(req.getLogRemark());
		int a = projectLogDao.insert(pLogModel);
		if (a > 0) {
			int b = projectDao.audit(req);
			if (b > 0) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * 新手专享项目列表
	 */
	@Override
	public List<ProjectModel> getNoviceProject() {
		return projectDao.getNoviceProject();
	}

	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int modifyProject(ModifyProjectReq req) {
		Long projectId = req.getProject().getId();
		// 1.修改项目主表project，还没有日志记录
		req.getProject().setCreatename(req.getPublishName());
		long i = projectDao.updateByPrimaryKeySelective(req.getProject());
		if (i > 0) {
			// 2.修改策略表 projectstrategy 先删除原来的策略 在插入新的策略
			int upDel = projectStrategyDao.updateDeleteByProjectId(projectId); // 策略表打删除标记
			if (upDel > 0) {
				req.getProjectStrategyList().forEach(
						action -> action.setProjectid(projectId));
				int proStrategyResult = projectStrategyDao.insertList(req
						.getProjectStrategyList()); // 插入新的策略
				// 3.修改项目图片表 projectimage
				if (proStrategyResult > 0) {
					int updeImg = 0;
					// 先删除的原来的图片，在插入新的图片
					List<ModifyProjectImg> modifyProjectImgs = req
							.getModifyProjectImgList();
					if (modifyProjectImgs != null
							&& modifyProjectImgs.size() > 0) {
						List<ModifyProjectImg> updateImgs = modifyProjectImgs
								.stream().filter(t -> t.getModifyType() == 2)
								.collect(Collectors.toList());
						if (updateImgs != null && updateImgs.size() > 0) {
							updeImg = projectImageDao
									.updateDeleteById(updateImgs);
						}
					} else {
						updeImg = 1;
					}
					int insImg = 0;
					// 插入新的图片
					List<ProjectImage> projectImages = req
							.getProjectImageList();
					if (projectImages != null && projectImages.size() > 0) {
						req.getProjectImageList().forEach(
								action -> action.setProjectid(req.getProject()
										.getId()));
						insImg = projectImageDao.insertList(req
								.getProjectImageList());
					} else {
						insImg = 1;
					}
					if (updeImg > 0 && insImg > 0) {
						return 1;
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 获取项目详情
	 */
	@Override
	public ProjectModel getProjectDetail(Long projectid) {
		return projectDao.getProjectDetail(projectid);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int modifyProjectStatus(ProjectStatusReq req) {
		// 1.修改项目表project融资状态
		int a = projectDao.modifyProjectStatus(req);
		// 2.projectlog添加日志记录
		if (a > 0) {
			ProjectLogModel pLogModel = new ProjectLogModel();
			pLogModel.setProjectId(req.getProjectId());
			pLogModel.setIsDel(0);
			pLogModel.setOperater(req.getOperater());
			pLogModel.setRemark(req.getLogRemark());
			int b = projectLogDao.insert(pLogModel);
			if (b > 0) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int isShelf(ProjectStatusReq req) {
		// 1.修改项目表project上下架状态
		int a = projectDao.isShelf(req);
		// 2.projectlog添加日志记录
		if (a > 0) {
			ProjectLogModel pLogModel = new ProjectLogModel();
			pLogModel.setProjectId(req.getProjectId());
			pLogModel.setIsDel(0);
			pLogModel.setOperater(req.getOperater());
			pLogModel.setRemark(req.getLogRemark());
			int b = projectLogDao.insert(pLogModel);
			if (b > 0) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public List<DataStatistics> getDataStatistics() {
		return projectDao.getDataStatistics();
	}

	@Override
	public int updateByPrimaryKeySelective(Project record) {
	
		
		
		return projectDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ToDoDataStatistics> getToDoDataStatices() {
		return projectDao.getToDoDataStatices();
	}
	/**
	 * 定时服务 茹化肖
	 */
	@Override
	public int QuartzServie() {
		return projectDao.QuartzServie();
	}
}
