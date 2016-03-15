<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%-- <%@page import="com.etaofinance.entity.AccountInfo"%> --%>
<%-- <%@page import="com.etaofinance.entity.RoleInfo"%> --%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.core.util.HtmlHelper"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
%>
<!-- 百度图片上传 start -->
<!--引入CSS-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/webuploader0.1.5/webuploader.css">

<!--引入JS-->
<script type="text/javascript" src="<%=basePath%>/js/webuploader0.1.5/webuploader.js"></script>

<!-- 百度图片上传 End -->
<div class="wrapper wrapper-content animated fadeInRight">
<form method="POST" action="#" class="form-horizontal" id="searchForm">
<fieldset>
			<legend>基本信息</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">发起者会员手机号: </label>
								<div class="col-sm-6">
									<input type="text" maxlength="11" class="form-control" name="memberPhone" id="memberPhone" value=""/>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">项目名称: </label>
								<div class="col-sm-6">
									<input type="text" maxlength="15" class="form-control" name="projectName" id="projectName" value=""/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">项目类型: </label>
								<div class="col-sm-6">
									<input id="rProjectType1" name="rProjectType" type="radio" value="1" checked="checked"> 
									<label>稳健型</label>
									<input id="rProjectType2" name="rProjectType" type="radio" value="2"> 
									<label>风险共担型</label>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group" id="ProjectType1">
								<label class="col-sm-4 control-label">年化收益: </label>
								<div class="col-sm-2">
									<input type="text" class="form-control"id="projectType1A" value="0" style="width: 50px;"/>
								</div>
								<div class="col-sm-1" style="line-height: 33px;">
	  							  %
								</div>
								<div class="col-sm-1" style="line-height: 33px;">
	  							  +
								</div>
								<div class="col-sm-2" id="ProjectType1Check">
									<input type="text" class="form-control"id="projectType1A" value="0" style="width: 50px;"/>
								</div>
								<div class="col-sm-1" style="line-height: 33px;">
	  							  %
								</div>
							</div>
							<div class="form-group" id="ProjectType2" style="display:none">
								<label class="col-sm-4 control-label">年化收益: </label>
								<div class="col-sm-1" style="line-height: 33px;">
	  							  预计
								</div>
								<div class="col-sm-2">
									<input type="text" class="form-control"id="projectType1A" value="0" style="width: 50px;"/>
								</div>
								<div class="col-sm-1" style="line-height: 33px;">
	  							  %
								</div>
								<div class="col-sm-1" style="line-height: 33px;">
	  							  —
								</div>
								<div class="col-sm-2" id="ProjectType1Check">
									<input type="text" class="form-control"id="projectType1A" value="0" style="width: 50px;"/>
								</div>
								<div class="col-sm-1" style="line-height: 33px;">
	  							  %
								</div>
							</div>
						</div>
						
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">一句话简介: </label>
								<div class="col-sm-8">
									<input type="text" maxlength="20" class="form-control" name="projectDescription" id="projectDescription" value=""/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">融资金额: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="projectAmount" id="projectAmount" value="0"/>
								</div>
								<div class="col-sm-1" style="line-height: 33px;">
	  							  元
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">份数: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="projectFenShu" id="projectFenShu" value="0"/>
								</div>
								<div class="col-sm-1" style="line-height: 33px;">
	  							  份
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">每份金额: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="projectUnitPrice" id="projectUnitPrice" value="0" readonly="readonly"/>
								</div>
								<div class="col-sm-1" style="line-height: 33px;">
	  							  元
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">领投人最低限制: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="projectLeadMinFenShu" id="projectLeadMinFenShu" value="0"/>
								</div>
								<div class="col-sm-1" style="line-height: 33px;">
	  							  份
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">领投总额最高限额: </label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="projectPreheatMaxFenShu" id="projectPreheatMaxFenShu" value="0"/>
								</div>
								<div class="col-sm-1" style="line-height: 33px;">
	  							  份
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">省份: </label>
								<div class="col-sm-6">
									 <select class="form-control m-b" id="ProvinceCode" name="ProvinceCode">  <option value="22">省份</option><option value="22">E代送版本2</option></select>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
							<label class="col-sm-4 control-label">城市: </label>
								<div class="col-sm-6">
									 <select class="form-control m-b" id="ProvinceCode" name="ProvinceCode">  <option value="22">城市</option><option value="22">E代送版本2</option></select>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
							<label class="col-sm-4 control-label">区域: </label>
								<div class="col-sm-6">
									 <select class="form-control m-b" id="ProvinceCode" name="ProvinceCode">  <option value="22">区域</option><option value="22">E代送版本2</option></select>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">详细地址: </label>
								<div class="col-sm-8">
									<input type="text" maxlength="140" class="form-control" name="projectAddress" id="projectAddress" value=""/>
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
								<div class="col-sm-8" id="projectHead">
									
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
								<div class="col-sm-9">
								<ul class="ProjectDescImgPcBox">
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
								<div class="col-sm-9">
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
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
								<div class="col-sm-9">
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
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
								<div class="col-sm-9">
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
									<img src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png" style="height: 80px;width: 100px;"/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-4">
						<button type="button" class="btn btn-w-m btn-primary" id="clearAll" style="margin-left: 3px; height: 30px;">重置全部</button>
						<button type="button" class="btn btn-w-m btn-primary" id="saveProject" onclick="savetask()" style="margin-left: 3px; height: 30px;">保存</button>
						<button type="button" class="btn btn-w-m btn-primary" id="uploadallimg"  style="margin-left: 3px; height: 30px;">上传所有图片</button>
						</div>
					</div>
				</div>
			</div>
			
		</fieldset>
</form>
</div>

<!--引入JS-->
<script type="text/javascript" src="<%=basePath%>/js/newproject/newproject.js"></script>
<script type="text/javascript">
var BasePath='<%=basePath%>';
var uploader;//项目图片上传
InitUpload(uploader,'ProjectImg','projectHead',1);
var uploader2;//项目图片上传
InitUpload(uploader2,'ProjectDescImgPc','ProjectDescImgPcBox',10);
</script>
<script>
//
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
	//上传全部文件
	$('#uploadallimg').click(function(){
		alert('开始上传文件1');
		uploader.upload();
		alert('开始上传文件2');
	});
});
</script>