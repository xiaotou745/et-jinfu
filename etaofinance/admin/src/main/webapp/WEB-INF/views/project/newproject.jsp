<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%-- <%@page import="com.etaofinance.entity.AccountInfo"%> --%>
<%-- <%@page import="com.etaofinance.entity.RoleInfo"%> --%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.core.util.HtmlHelper"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
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
<script src="<%=basePath%>/js/newproject/newproject.js"></script>
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
<div class="wrapper wrapper-content animated fadeInRight">
	<form method="POST" action="#" class="form-horizontal" id="searchForm">
		<input type="hidden" id="pro_city" value="<%=pro_city%>" /> <input
			type="hidden" id="city_region" value="<%=city_region%>" />
		<fieldset>
			<legend>基本信息</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-5 control-label">发起者会员手机号: </label>
								<div class="col-sm-6">
									<input type="text" maxlength="11" class="form-control"
										name="memberPhone" id="memberPhone" value=""
										onkeyup="this.value = parseInt(this.value); if (this.value=='NaN') { this.value = ''}"
										maxlength="11" /> <input type="hidden" id="memberId"
										value="0" />
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="form-group">
								<label class="col-sm-5 control-label">项目名称: </label>
								<div class="col-sm-6">
									<input type="text" maxlength="15" class="form-control"
										name="projectName" id="projectName" value="" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">

						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-5 control-label">项目类型: </label>
								<div class="col-sm-6">
									<input id="rProjectType1" name="rProjectType" type="radio"
										value="1" checked="checked"> <label>稳健型</label> <input
										id="rProjectType2" name="rProjectType" type="radio" value="2">
									<label>风险共担型</label>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="form-group" id="ProjectType1">
								<label class="col-sm-5 control-label">年化收益: </label>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="projectType1A"
										value="" style="width: 50px;" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">%</div>
								<div class="col-sm-1" style="line-height: 33px;">+</div>
								<div class="col-sm-2" id="ProjectType1Check">
									<input type="text" class="form-control" id="projectType1B"
										value="" style="width: 50px;" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">%</div>
							</div>
							<div class="form-group" id="ProjectType2" style="display: none">
								<label class="col-sm-5 control-label">年化收益: </label>
								<div class="col-sm-2" style="line-height: 33px;">预计</div>
								<div class="col-sm-1">
									<input type="text" class="form-control" id="projectType2A"
										value="" style="width: 50px;" />
								</div>
								<div class="col-sm-1"
									style="line-height: 33px; padding-left: 35px">%</div>
								<div class="col-sm-1" style="line-height: 33px;">—</div>
								<div class="col-sm-1" id="ProjectType1Check">
									<input type="text" class="form-control" id="projectType2B"
										value="" style="width: 50px;" />
								</div>
								<div class="col-sm-1"
									style="line-height: 33px; padding-left: 40px">%</div>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-5 control-label">一句话简介: </label>
								<div class="col-sm-7">
									<input type="text" maxlength="20" class="form-control"
										name="projectDescription" id="projectDescription" value="" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-5 control-label">融资金额: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="projectAmount"
										id="projectAmount" value="0" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">元</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="form-group">
								<label class="col-sm-5 control-label">份数: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="projectFenShu"
										id="projectFenShu" value="0" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">份</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-5 control-label">每份金额: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="projectUnitPrice"
										id="projectUnitPrice" value="0" readonly="readonly" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">元</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-5 control-label">领投总额最高限额: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control"
										name="projectPreheatMaxFenShu" id="projectPreheatMaxFenShu"
										value="0" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">份</div>
							</div>

						</div>
						<div class="col-lg-4">
							<div class="form-group">
								<label class="col-sm-5 control-label">领投人最低限制: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control"
										name="projectLeadMinFenShu" id="projectLeadMinFenShu"
										value="0" />
								</div>
								<div class="col-sm-1" style="line-height: 33px;">份</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-5 control-label">省份: </label>
								<div class="col-sm-6">
									<%=HtmlHelper.getSelect("provinceCode", provincelist, "name", "code", null,-1, "全部省份")%>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="form-group">
								<label class="col-sm-5 control-label">城市: </label>
								<div class="col-sm-6">
									<select id="cityCode" name="cityCode" class="form-control m-b">
										<option value="-1">全部城市</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-5 control-label">区域: </label>
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
								<label class="col-sm-5 control-label">详细地址: </label>
								<div class="col-sm-7">
									<input type="text" maxlength="240" class="form-control"
										name="projectAddress" id="projectAddress" value=""  />
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
								<label class="col-sm-5 control-label">项目图: </label>
								<div class="col-sm-6">
									<!-- 文件上传部分 -->
									<div id="ProjectImg">选择图片</div>
									<!-- 文件上传部分 -->
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<div class="col-sm-7 uploader">
									<ul id="ProjectImgBox" class="filelist">
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-5 control-label">项目概况(pc): </label>
								<div class="col-sm-6">
									<!-- 文件上传部分 -->
									<div id="ProjectDescImgPc">选择图片</div>

									<!-- 文件上传部分 -->
								</div>
							</div>
						</div>
						<div class="col-lg-9">
							<div class="form-group">
								<div class="col-sm-9  uploader">
									<ul id="ProjectDescImgPcBox" class="filelist">
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-5 control-label">项目概况(Wap): </label>
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
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-5 control-label">回报说明(pc): </label>
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
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-5 control-label">回报说明(Wap): </label>
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
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-4">
							<span id="tip" style="color: red"></span>
							<button type="button" class="btn btn-w-m btn-primary"
								id="clearAll" style="margin-left: 3px; height: 30px;">重置</button>
							<button type="button" class="btn btn-w-m btn-primary"
								id="saveProject" style="margin-left: 3px; height: 30px;">保存</button>
							<button type="button" class="btn btn-w-m btn-primary"
								id="uploadallimg" style="margin-left: 3px; height: 30px;">上传所有图片</button>
						</div>
					</div>
				</div>
			</div>

		</fieldset>
	</form>
</div>

<!--引入JS-->
<script>
var BasePath='<%=basePath%>';
</script>
<script type="text/javascript"
	src="<%=basePath%>/js/newproject/newproject.js"></script>
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
	//重置全部
	
	$('#clearAll').click(function(){
		if(confirm('确定重置全部页面?'))
		{
			 window.location.reload();
		}
	});
	
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
	
	
	$('#projectAmount').blur(function(){
		FenshuJisuan();
	});	
	$('#projectFenShu').blur(function(){
		FenshuJisuan();
	});
});

//校验手机号会员ID
$('#memberPhone').blur(function(){
	validatePhoneNo();
});


function validatePhoneNo(){
    var url='<%=basePath%>/member/getmemberid';
		var phone = $('#memberPhone').val();
		if (phone.length == 0) {
			//alert("请输入手机号！");
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
	    
	    
	    if(a.length!=0 && b.length!=0&&isInt(a)&&isInt(b)){
	    	$('#projectUnitPrice').val(a/b)
	    }
	}
	
	
	//保存
	$('#saveProject').click(function() {
		if (!SaveChek()) {
			return;
		}
		var proObjReq = new Object();
		proObjReq.project = CreateProj();
		proObjReq.projectStrategyList = CreateStrategylist();
		proObjReq.projectImageList = CreateImgList();
		//构建数据库信息
		var json_data = JSON.stringify(proObjReq);
		$("#tip").html("正在执行。。。");
		$("#saveProject").attr("disabled", true);
		var url = "<%=basePath%>/project/saveproject";
		$.ajax({
			type : 'POST',
			url : url,
			data : {
				"data" : json_data
			},
			success : function(result) {
				$("#tip").html("");
				$("#saveProject").attr("disabled", false);
				if (result > 0) {
					alert("发布成功");
					window.location.href = window.location.href;
				} else if (result == 0) {
					alert("发布失败");
				} else {
					alert("发布作失败:下载链接无法访问，请修改后重试");
				}
			},
			error : function(errordata) {
				$("#tip").html("");
				$("#save").attr("disabled", false);
			}
		});
	});
</script>