<%@page import="com.etaofinance.core.enums.ProjectAuditStatus"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.entity.ProjectStrategy"%>
<%@page import="com.etaofinance.entity.ProjectImage"%>
<%@page import="com.etaofinance.entity.Project"%>
<%@page import="com.etaofinance.core.enums.ProjectImageTypeEnum"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.etaofinance.core.util.Config"%>
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
	} else if(project.getAuditstatus()==ProjectAuditStatus.AuditPass.value()) {%>
		======已经<%=ProjectAuditStatus.getEnum(project.getAuditstatus()).desc() %>=====
		<a class="btn btn-w-m btn-white" href="<%=basePath%>/project/waitlist">返回</a>		 
	<% }else {
		List<ProjectStrategy> proStrList = (List<ProjectStrategy>) request.getAttribute("proStrList");
		List<ProjectImage> proImgList = (List<ProjectImage>) request.getAttribute("proImgList");
		Map<Long,String> cityMap = (Map<Long,String>) request.getAttribute("cityMap");
%>
<div
	class="wrapper wrapper-content animated fadeInRight form-horizontal">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="control-group">
						<label>审核操作：</label><input type="radio" id="rAuditPass"
							name="rAuditOpt" value="2" checked="checked" />通过 <input
							type="radio" id="rAuditNotPass" name="rAuditOpt" value="3" />不通过<input
							type="hidden" value="<%=projectId%>" id="projectId" />
					</div>
				</div>
			</div>
			<div class="row" id="someDateDiv">
				<div class="col-lg-3">
					<div class="control-group">
						<label>上线预热时间：</label><input type="text" style="width: 200px;"
							class="form-control" value="" name="onlinePreheatDate"
							id="onlinePreheatDate"
							onFocus="WdatePicker({minDate:'%y-%M-%d'})" />
					</div>
				</div>
				<div class="col-lg-3">
					<div class="control-group">
						<label>开放融资时间：</label><input type="text" style="width: 200px;"
							class="form-control" value="" name="openFinancingDate"
							id="openFinancingDate"
							onFocus="WdatePicker({minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endFinancingDate\',{d:-1})||\'2030-10-01\'}'})" />
					</div>
				</div>
				<div class="col-lg-3">
					<div class="control-group">
						<label>融资结束时间：</label><input type="text" style="width: 200px;"
							class="form-control" value="" name="endFinancingDate"
							id="endFinancingDate"
							onFocus="WdatePicker({minDate:'#F{$dp.$D(\'openFinancingDate\',{d:1})}',maxDate:'2030-10-01'})" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3">
					<div class="control-group">
						<label>备注：</label>
						<textarea name="txtRemark" id="txtRemark"
							style="width: 300px; height: 60px; max-width: 300px; max-height: 60px;"></textarea>
					</div>
				</div>
			</div>
			<div class="row">				
				<div class="col-lg-3">
					<button id="btnConfirm" class="btn btn-w-m btn-primary"
						type="button">确定</button>
				</div>
				<div class="col-lg-3"><a class="btn btn-w-m btn-white" href="<%=basePath%>/project/waitlist">返回</a></div>
			</div>
		</div>
	</div>
</div>
<table class="table table-condensed table-bordered">
	<tbody>
		<tr>
			<td>项目名</td>
			<td colspan="2"><%=project.getProjectname()%></td>
			<td>项目类型</td>
			<td colspan="2"><%=project.getTypeIdString()%></td>
		</tr>
		<tr>
			<td>一句话简介</td>
			<td colspan="5"><%=project.getDescription()%></td>
		</tr>
		<tr>
			<td>融资金额</td>
			<td><%=project.getAmount()%></td>
			<td>份数</td>
			<td><%=project.getFenshu()%></td>
			<td>每份金额</td>
			<td><%=project.getUnitprice()%></td>
		</tr>
		<tr>
			<td>地区</td>
			<td colspan="5"><%=cityMap.get(project.getProvincecode())%><%=cityMap.get(project.getCitycode())%><%=cityMap.get(project.getAreacode())%></td>
		</tr>
		<tr>
			<td>详细地址</td>
			<td colspan="5"><%=project.getAddress()%></td>
		</tr>
		<tr>
			<td><font color="red">年化收益率</font></td>
			
			<%if(proStrList.get(1).getValue()>0){ %>
				<td colspan="5"><%=proStrList.get(0).getValue()+"-"+proStrList.get(1).getValue() %></td>			
			<%} else{%>
				<td colspan="5"><%=proStrList.get(0).getValue()%></td>
			<%} %>
		</tr>	
		<tr>
			<td>项目图</td>
			<td colspan="5"><img src='<%=Config.ImgShowUrl+"/"+project.getProjectimage() %>' alt="项目图"></td>
		</tr>
		<tr>
			<td>项目概况</td>
			<td colspan="5">
			<% for(int i=0;i<proImgList.size();i++){ if(proImgList.get(i).getTypeid()==ProjectImageTypeEnum.ProjectBasicDesWap.value()){ %>
				<img src='<%=Config.ImgShowUrl+"/"+proImgList.get(i).getUrl() %>' alt="项目概况Wap图">
			<%} }%>
			</td>
		</tr>
		<tr>
			<td>回报说明</td>
			<td colspan="5">
			<% for(int i=0;i<proImgList.size();i++){ if(proImgList.get(i).getTypeid()==ProjectImageTypeEnum.ProjectRtnDesWap.value()){ %>
				<img src='<%=Config.ImgShowUrl+"/"+proImgList.get(i).getUrl() %>' alt="项目回报说明Wap图">
			<%} }%>
			</td>
		</tr>
	</tbody>
</table>
<%
	}
%>
<script>
/*审核 弹出框 确认操作 */
$("#btnConfirm").click(function(){
	var url="<%=basePath%>/project/audit";
		var auditStaus = $('input[name="rAuditOpt"]:checked').val();
		var remark = $("#txtRemark").val();
		var projectId = $("#projectId").val();
		if (remark.trim().length == 0) {
			alert("请输入备注信息！")
			return;
		}		
		var onlinePreheatDate=$("#onlinePreheatDate").val();
		var openFinancingDate=$("#openFinancingDate").val();
		var endFinancingDate=$("#endFinancingDate").val();
		if(auditStaus==2){
			if(onlinePreheatDate=="" ||openFinancingDate=="" ||endFinancingDate==""){
				alert("时间不能为空！");
				return;
			}
		}
		var paramaters = {
			"projectId" : projectId,
			"auditStatus" : auditStaus,
			"remark" : remark,
			"onlinePreheatDate" : onlinePreheatDate,
			"openFinancingDate" : openFinancingDate,
			"endFinancingDate" : endFinancingDate
		};
		$.ajax({
			type : "POST",
			url : url,
			data : paramaters,
			success : function(result) {
				alert("审核成功！");
				history.go(-1);
			},
			error : function(e) {
				alert("审核失败！");
			}
		});
	});
	/* 单选框 切换 */
	$('input:radio[name="rAuditOpt"]').change(function() {
		if ($(this).val() == 2) {
			$("#someDateDiv").show();
		} else {
			$("#someDateDiv").hide();
		}
	});
</script>