<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.enums.ProjectImageTypeEnum"%>
<%@page import="com.etaofinance.core.enums.ProjectType"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.core.util.HtmlHelper"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity"%>
<%@page import="com.etaofinance.entity.Project"%>
<%@page import="com.etaofinance.entity.Member"%>
<%@page import="com.etaofinance.entity.ProjectStrategy"%>
<%@page import="com.etaofinance.entity.ProjectImage"%>
<%@page import="com.etaofinance.core.util.Config"%>
<%@page import="com.etaofinance.core.enums.ProjectAuditStatus"%>

<%@page import="java.util.Map"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
	long projectId = (long) request.getAttribute("projectId");
	Project project = (Project) request.getAttribute("project");
%>
<%
	if (project == null) {
%>
======暂无数据 无此项目信息=====
<%
	} else if(project.getAuditstatus()==ProjectAuditStatus.AuditPass.value() || project.getAuditstatus()==ProjectAuditStatus.AuditRefuse.value()) {%>
		======已经<%=ProjectAuditStatus.getEnum(project.getAuditstatus()).desc() %> 暂时不能修改=====
		<a class="btn btn-w-m btn-white" href="<%=basePath%>/project/waitlist">返回</a>		 
	<% }else {
		List<ProjectStrategy> proStrList = (List<ProjectStrategy>) request.getAttribute("proStrList");
		List<ProjectImage> proImgList = (List<ProjectImage>) request.getAttribute("proImgList");
		Member member=(Member)request.getAttribute("member");
		List<PublicProvinceCity> provincelist = (List<PublicProvinceCity>) request.getAttribute("provincelist");
		String pro_city = (String) request.getAttribute("pro_city");
		String city_region = (String) request.getAttribute("city_region");
%>
<!-- 百度图片上传 start -->
<!--引入CSS-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/js/webuploader0.1.5/webuploader.css">

<!--引入JS-->
<script type="text/javascript"
	src="<%=basePath%>/js/webuploader0.1.5/webuploader.js"></script>
<!-- 省市区联动JS -->
<script type="text/javascript" src="<%=basePath%>/js/pro_city_region.js"></script>
<script src="<%=basePath%>/js/newproject/modifyproject.js"></script>
<!-- 百度图片上传 End -->
<style>
.uploader .placeholder .webuploader-pick-hover {
	background: #00a2d4;
}

.uploader .placeholder .flashTip {
	color: #666666;
	font-size: 12px;
	position: absolute;
	width: 100%;
	text-align: center;
	bottom: 20px;
}

.uploader .placeholder .flashTip a {
	color: #0785d1;
	text-decoration: none;
}

.uploader .placeholder .flashTip a:hover {
	text-decoration: underline;
}

.uploader .filelist {
	list-style: none;
	margin: 0;
	padding: 0;
}

.uploader .filelist:after {
	content: '';
	display: block;
	width: 0;
	height: 0;
	overflow: hidden;
	clear: both;
}

.uploader .filelist li {
	width: 110px;
	height: 110px;
	background: url(../images/bg.png) no-repeat;
	text-align: center;
	margin: 0 8px 20px 0;
	position: relative;
	display: inline;
	float: left;
	overflow: hidden;
	font-size: 12px;
}

.uploader .filelist li p.log {
	position: relative;
	top: -45px;
}

.uploader .filelist li p.title {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	top: 5px;
	text-indent: 5px;
	text-align: left;
}

.uploader .filelist li p.progress {
	position: absolute;
	width: 100%;
	bottom: 0;
	left: 0;
	height: 8px;
	overflow: hidden;
	z-index: 50;
	margin: 0;
	border-radius: 0;
	background: none;
	-webkit-box-shadow: 0 0 0;
}

.uploader .filelist li p.progress span {
	display: none;
	overflow: hidden;
	width: 0;
	height: 100%;
	background: #1483d8 url(../images/progress.png) repeat-x;
	-webit-transition: width 200ms linear;
	-moz-transition: width 200ms linear;
	-o-transition: width 200ms linear;
	-ms-transition: width 200ms linear;
	transition: width 200ms linear;
	-webkit-animation: progressmove 2s linear infinite;
	-moz-animation: progressmove 2s linear infinite;
	-o-animation: progressmove 2s linear infinite;
	-ms-animation: progressmove 2s linear infinite;
	animation: progressmove 2s linear infinite;
	-webkit-transform: translateZ(0);
}
</style>
<input type="hidden" id="pro_city" value="<%=pro_city%>" />
<input type="hidden" id="city_region" value="<%=city_region%>" />
<div class="wrapper wrapper-content animated fadeInRight">
	<form method="POST" action="#" class="form-horizontal" id="searchForm">
		<input type="hidden" id="pro_city" value="<%=pro_city%>" /> <input
			type="hidden" id="city_region" value="<%=city_region%>" />
			<input type="hidden" id="projectId" value="<%=projectId%>"/>
		<fieldset>
			<legend>基本信息</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">发起者会员手机号: </label>
								<div class="col-sm-6">
									<input type="text" maxlength="11" class="form-control"
										name="memberPhone" id="memberPhone" value="<%=member.getPhoneno()%>"
										onkeyup="this.value = parseInt(this.value); if (this.value=='NaN') { this.value = ''}"
										maxlength="11" /> <input type="hidden" id="memberId"
										value="0" />
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">项目名称: </label>
								<div class="col-sm-6">
									<input type="text" maxlength="15" class="form-control"
										name="projectName" id="projectName" value="<%=project.getProjectname()%>" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">项目类型: </label>
								<div class="col-sm-6">
<input id="rProjectType1" name="rProjectType" type="radio" value="1"/>
<label>稳健型</label>
<input id="rProjectType2" name="rProjectType" type="radio" value="2"/>
<label>风险共担型</label>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group" id="ProjectType1">
								<label class="col-sm-4 control-label">年化收益: </label>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="projectType1A"
										value="<%=proStrList.get(0).getValue()%>" style="width: 50px;" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">%</div>
								<div class="col-sm-1" style="line-height: 33px;">+</div>
								<div class="col-sm-2" id="ProjectType1Check">
									<input type="text" class="form-control" id="projectType1B"
										value="<%=proStrList.get(1).getValue()%>" style="width: 50px;" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">%</div>
							</div>
							<div class="form-group" id="ProjectType2" style="display: none">
								<label class="col-sm-4 control-label">年化收益: </label>
								<div class="col-sm-2" style="line-height: 33px;">预计</div>
								<div class="col-sm-1">
									<input type="text" class="form-control" id="projectType2A"
										value="<%=proStrList.get(0).getValue()%>" style="width: 50px;" />
								</div>
								<div class="col-sm-1"
									style="line-height: 33px; padding-left: 35px">%</div>
								<div class="col-sm-1" style="line-height: 33px;">—</div>
								<div class="col-sm-1" id="ProjectType1Check">
									<input type="text" class="form-control" id="projectType2B"
										value="<%=proStrList.get(1).getValue()%>" style="width: 50px;" />
								</div>
								<div class="col-sm-1"
									style="line-height: 33px; padding-left: 35px">%</div>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">一句话简介: </label>
								<div class="col-sm-8">
									<input type="text" maxlength="20" class="form-control"
										name="projectDescription" id="projectDescription" value="<%=project.getDescription()%>" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">融资金额: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="projectAmount"
										id="projectAmount" value="<%=project.getAmount()%>" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">元</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">份数: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="projectFenShu"
										id="projectFenShu" value="<%=project.getFenshu()%>" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">份</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">每份金额: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="projectUnitPrice"
										id="projectUnitPrice" value="<%=project.getUnitprice()%>" readonly="readonly" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">元</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">领投总额最高限额: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control"
										name="projectPreheatMaxFenShu" id="projectPreheatMaxFenShu"
										value="<%=project.getPreheatmaxfenshu()%>" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">份</div>
							</div>

						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">领投人最低限制: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control"
										name="projectLeadMinFenShu" id="projectLeadMinFenShu"
										value="<%=project.getLeadminfenshu()%>" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">份</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">省份: </label>
								<div class="col-sm-6">
									<%=HtmlHelper.getSelect("provinceCode", provincelist, "name", "code", null,-1, "全部省份")%>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">城市: </label>
								<div class="col-sm-6">
									<select id="cityCode" name="cityCode" class="form-control m-b">
										<option value="-1">全部城市</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">区域: </label>
								<div class="col-sm-6">
									<select id="regionCode" name="cityCode"
										class="form-control m-b">
										<option value="-1">全部区县</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">详细地址: </label>
								<div class="col-sm-8">
									<input type="text" maxlength="140" class="form-control"
										name="projectAddress" id="projectAddress" value="<%=project.getAddress()%>" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend>项目图片</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">项目图: </label>
								<div class="col-sm-6">
									<!-- 文件上传部分 -->
									<div id="ProjectImg">选择图片</div>
									<!-- 文件上传部分 -->
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<div class="col-sm-8 uploader">
									<ul id="ProjectImgBox" class="filelist">
										<li>
											<p class="imgWrap">
												<img class="modifyProImg" style="height: 80px; width: 100px;" src="<%=Config.ImgShowUrl + "/" + project.getProjectimage()%>">
											</p>
											<div id="<%=project.getId()%>" class="file-panel">
												<span class="cancel" onclick="delOriginalImg(this)">删除</span>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">项目概况(pc): </label>
								<div class="col-sm-6">
									<!-- 文件上传部分 -->
									<div id="ProjectDescImgPc">选择图片</div>
									<!-- 文件上传部分 -->
								</div>
							</div>
						</div>
						<div class="col-lg-9">
							<div class="form-group">
								<div class="col-sm-9 uploader">
									<ul id="ProjectDescImgPcBox" class="filelist">
									<%
										for (int i = 0; i < proImgList.size(); i++) { if((int)proImgList.get(i).getTypeid()== ProjectImageTypeEnum.ProjectBasicDesPC.value()){
									%>
									<li>
											<p class="imgWrap">
												<img class="modifyProImg" style="height: 80px; width: 100px;"
													src="<%=Config.ImgShowUrl+"/"+proImgList.get(i).getUrl()%>">
											</p>
											<div id="<%=proImgList.get(i).getId()%>" class="file-panel">
												<span class="cancel" onclick="delOriginalImg(this)">删除</span>
											</div>
										</li>
									<%
										}}
									%>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">项目概况(Wap): </label>
								<div class="col-sm-6">
									<!-- 文件上传部分 -->
									<div id="ProjectDescImgWap">选择图片</div>
									<!-- 文件上传部分 -->
								</div>
							</div>
						</div>
						<div class="col-lg-9">
							<div class="form-group">
								<div class="col-sm-9  uploader">
									<ul id="ProjectDescImgWapBox" class="filelist">
									<%
										for (int i = 0; i < proImgList.size(); i++) { if((int)proImgList.get(i).getTypeid()== ProjectImageTypeEnum.ProjectBasicDesWap.value()){
									%>
									<li>
											<p class="imgWrap">
												<img class="modifyProImg" style="height: 80px; width: 100px;"
													src="<%=Config.ImgShowUrl+"/"+proImgList.get(i).getUrl()%>">
											</p>
											<div id="<%=proImgList.get(i).getId()%>" class="file-panel">
												<span class="cancel" onclick="delOriginalImg(this)">删除</span>
											</div>
										</li>
									<%
										}}
									%>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">回报说明(pc): </label>
								<div class="col-sm-6">
									<!-- 文件上传部分 -->
									<div id="ProjectHuibaoImgPc">选择图片</div>
									<!-- 文件上传部分 -->
								</div>
							</div>
						</div>
						<div class="col-lg-9">
							<div class="form-group">
								<div class="col-sm-9  uploader">
									<ul id="ProjectHuibaoImgPcBox" class="filelist">
									<%
										for (int i = 0; i < proImgList.size(); i++) { if((int)proImgList.get(i).getTypeid()== ProjectImageTypeEnum.ProjectRtnDesPC.value()){
									%>
									<li>
											<p class="imgWrap">
												<img class="modifyProImg" style="height: 80px; width: 100px;"
													src="<%=Config.ImgShowUrl+"/"+proImgList.get(i).getUrl()%>">
											</p>
											<div id="<%=proImgList.get(i).getId()%>" class="file-panel">
												<span class="cancel" onclick="delOriginalImg(this)">删除</span>
											</div>
										</li>
									<%
										}}
									%>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">回报说明(Wap): </label>
								<div class="col-sm-6">
									<!-- 文件上传部分 -->
									<div id="ProjectHuibaoImgWap">选择图片</div>
									<!-- 文件上传部分 -->
								</div>
							</div>
						</div>
						<div class="col-lg-9">
							<div class="form-group">
								<div class="col-sm-9  uploader">
									<ul id="ProjectHuibaoImgWapBox" class="filelist">
									<%
										for (int i = 0; i < proImgList.size(); i++) { if((int)proImgList.get(i).getTypeid()== ProjectImageTypeEnum.ProjectRtnDesWap.value()){
									%>
									<li>
											<p class="imgWrap">
												<img class="modifyProImg" style="height: 80px; width: 100px;"
													src="<%=Config.ImgShowUrl+"/"+proImgList.get(i).getUrl()%>">
											</p>
											<div id="<%=proImgList.get(i).getId()%>" class="file-panel">
												<span class="cancel" onclick="delOriginalImg(this)">删除</span>
											</div>
										</li>
									<%
										}}
									%>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-4">
							<span id="tip" style="color: red"></span>
							<button type="button" class="btn btn-w-m btn-primary"
								id="modifyProject" style="margin-left: 3px; height: 30px;">修改并保存</button>
							<button type="button" class="btn btn-w-m btn-primary"
								id="uploadallimg" style="margin-left: 3px; height: 30px;">上传所有图片</button>
						</div>
					</div>
				</div>
			</div>

		</fieldset>
	</form>
</div>
<%} %>
<!--引入JS-->
<script>
var BasePath='<%=basePath%>';
</script>
<script type="text/javascript">
var uploader;//项目图片上传
uploader=InitUpload(uploader,'ProjectImg','ProjectImgBox',1);
var uploader2;//项目概况PC
uploader2=InitUpload(uploader2,'ProjectDescImgPc','ProjectDescImgPcBox',10);
var uploader3;//项目概况Wap
uploader3=InitUpload(uploader3,'ProjectDescImgWap','ProjectDescImgWapBox',10);
var uploader4;//项目回报说明PC
uploader4=InitUpload(uploader4,'ProjectHuibaoImgPc','ProjectHuibaoImgPcBox',10);
var uploader5;//项目回报说明Wap
uploader5=InitUpload(uploader5,'ProjectHuibaoImgWap','ProjectHuibaoImgWapBox',10);
//上传全部文件
$('#uploadallimg').click(function(){
	uploader.upload();
	uploader2.upload();
	uploader3.upload();
	uploader4.upload();
	uploader5.upload();
});
</script>
<script>
$(function(){
	//项目类型切换事件
	$('input:radio[name="rProjectType"]').change(function() {
		var id = $('input[name="rProjectType"]:checked').val();
		if (id == 1) {
			$('#ProjectType2').hide();
			$('#ProjectType1').show();
		} else {
			$('#ProjectType2').show();
			$('#ProjectType1').hide();
		}
	});
	InitProvince();
	InitProjectType();
	//InitProjectImg();
	validatePhoneNo();
	//计算份额
	$('#projectAmount').blur(function(){
		 FenshuJisuan();
	});
	$('#projectFenShu').blur(function(){
		 FenshuJisuan();
	});
});
/*初始化选中项目投资类型*/
function InitProjectType(){	
	var proType=<%=project.getTypeid()%>;
	if(proType==1){		 
		$("#rProjectType1").prop("checked",true);
		$("#rProjectType2").removeAttr("checked");
		$('#ProjectType2').hide();
		$('#ProjectType1').show();
	}
	if(proType==2){		 
		$("#rProjectType2").prop("checked",true);
		$("#rProjectType1").removeAttr("checked");
		$('#ProjectType2').show();
		$('#ProjectType1').hide();
	}
}
/*初始化项目所在省市区*/
function InitProvince()
{
	initProvinceCityChange();
	var pCode=<%=project.getProvincecode()%>;
	var cCode=<%=project.getCitycode()%>;
	var rCode=<%=project.getAreacode()%>;
	if(pCode!=''){			
		$("#provinceCode").val(pCode);
		$('#provinceCode').change();
	}
	if(cCode!=''){
		$("#cityCode").val(cCode);
		$("#cityCode").change();
	}
	if(rCode!=''){
		$("#regionCode").val(rCode);
	}
}
/*初始化项目图片 */
function InitProjectImg(){
	$("#ProjectImg").hide();
}
//校验手机号会员ID
$('#memberPhone').blur(function(){
	validatePhoneNo();
});
function validatePhoneNo(){
    var url='<%=basePath%>/member/getmemberid';
		var phone = $('#memberPhone').val();
		if (phone.length == 0) {
			alert("请输入手机号！");
			return false;
		}
		$.post(url, {
			'phoneno' : phone
		}, function(d) {
			if (d == 0)//会员不存在
			{
				alert('该会员不存在,请重新输入手机号!');
				$('#memberPhone').focus();//设置焦点
				return false;
			} else {
				$('#memberId').val(d);
				return true;
			}
		});
	}
	//份额计算
	function FenshuJisuan() {
		var a = $('#projectAmount').val();
		var b = $('#projectFenShu').val();
		if (a.length == 0 || b.length == 0) {
			return;
		}
		if (!isInt(a)) {
			alert('请输入正确的融资金额');
			$('#projectAmount').focus();
			return;
		}
		if (!isInt(b)) {
			alert('请输入正确的份数');
			$('#projectFenShu').focus();
			return;
		}
		var c = a / b;
		if (!isInt(c) || c == 0) {
			alert('每份金额必须为整数，请调整份数或者融资金额!');
			$('#projectUnitPrice').val(0);
		} else {
			$('#projectUnitPrice').val(c);
		}
	}
	//保存
	$('#modifyProject').click(function() {
		if (!SaveChek()) {
			return;
		}
		var proObjReq = new Object();
		proObjReq.project = CreateProj();
		proObjReq.projectStrategyList = CreateStrategylist();
		proObjReq.projectImageList = CreateImgList();
		proObjReq.modifyProjectImgList = imgModifyList;
		//构建数据库信息
		var json_data =JSON.stringify(proObjReq);
		$("#tip").html("正在执行。。。");
		$("#modifyProject").attr("disabled",true);
		var url = "<%=basePath%>/project/modifyproject";
		$.ajax({
			type : 'POST',
			url : url,
			data : {
				"data" : json_data
			},
			success : function(result) {
				$("#tip").html("");
				$("#modifyProject").attr("disabled", false);
				if (result > 0) {
					alert("修改成功");
					window.location.href = "<%=Config.adminUrl%>+/project/waitlist";
				} else if (result == 0) {
					alert("修改失败");
				} else {
					alert("修改失败:下载链接无法访问，请修改后重试");
				}
			},
			error : function(errordata) {
				$("#tip").html("");
				$("#save").attr("disabled", false);
			}
		});
	});
	var imgModifyList = new Array();
	//删除图片
	function delOriginalImg(obj){
		var str=$(obj).parent().parent().parent().attr("id");
		if(str =="ProjectImgBox"){
// 			var tmpProObj = new Object();
// 			tmpProObj.modifyId = $(obj).parent().attr("id");
// 			tmpProObj.modifyType = 1;
// 	        imgModifyList.push(tmpProObj);   
		   $("#ProjectImg").show();
		}else{
			var tmpObj = new Object();
			tmpObj.modifyId = $(obj).parent().attr("id");
			tmpObj.modifyType = 2;
	        imgModifyList.push(tmpObj);
		}
		$(obj).parent().parent().remove();
		   
	}
</script>