<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.core.enums.ProjectType"%>
<%@page import="com.etaofinance.core.util.EnumHelper"%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.core.util.HtmlHelper"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
%>
<div
	class="wrapper wrapper-content animated fadeInRight form-horizontal">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">项目ID:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="请输入项目ID" class="form-control"
								id="ProjectId" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">项目名称:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="请输入项目名称" class="form-control"
								id="ProjectName" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">审核状态:</label>
						<div class="col-sm-7">
							<select name="projectAuditStatus" class="form-control m-b"
								id="projectAuditStatus">
								<option value="-1">全部</option>
								<option value="1">待审核</option>
								<option value="3">审核拒绝</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">项目类型:</label>
						<div class="col-sm-7">
							<%=HtmlHelper.getSelect("projectType", EnumHelper.GetEnumItems(ProjectType.class), "desc", "value",null,"-1","全部") %>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">提交时间:</label>
						<div class="col-sm-8">
							<div class="input-group date">
								<span class="input-group-addon"> <i
									class="fa fa-calendar"></i>
								</span> <input type="text" class="form-control" name="beginDate"
									id="beginDate" value="" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">结束日期:</label>
						<div class="col-sm-8">
							<div class="input-group date">
								<span class="input-group-addon"> <i
									class="fa fa-calendar"></i>
								</span> <input type="text" class="form-control" name="endDate"
									id="endDate" value="" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary"
						id="btnSearch" style="margin-left: 3px;">查询</button>
					
				</div>
			</div>
		</div>
	</div>
</div>

<div id="content"></div>
<script>
var jss={
		search:function(currentPage){
			var data={currentPage:currentPage,
					"id":$("#ProjectId").val().trim()==""?"0":$("#ProjectId").val().trim(),
					"projectStatus":$("#projectStatus").val(),
					"projectName":$("#ProjectName").val(),
					"auditStatus":$("#projectAuditStatus").val(),
					"startCreateTime":$("#beginDate").val(),
					"endCreateTime":$("#endDate").val(),
					"typeId":$("#projectType").val()
			};
			$.post("<%=basePath%>/project/waitlistdo", data, function(d) {
				$("#content").html(d);
			});
		}
	}

	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	function setstatus(projectid, status) {

	}
</script>