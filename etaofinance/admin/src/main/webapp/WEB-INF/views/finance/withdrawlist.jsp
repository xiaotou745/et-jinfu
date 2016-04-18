<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.core.enums.WithdrawStatus"%>
<%@page import="com.etaofinance.core.util.EnumHelper"%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.core.util.HtmlHelper"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
%>

<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">姓名:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="请输入姓名" class="form-control" id="createName"  maxlength="10"/>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">申请时间:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" value="" name="beginDate"
								id="beginDate"
								onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:-1})||\'2030-10-01\'}'})" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">至</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" value="" name="endDate"
								id="endDate"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\',{d:1})}',maxDate:'2030-10-01'})" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">状态:</label>
						<div class="col-sm-7">
							<%=HtmlHelper.getSelect("withdrawStatus", EnumHelper.GetEnumItems(WithdrawStatus.class), "desc", "value",null,"-3","全部") %>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left: 90px;">查询</button>
				</div>
			</div>
		</div>
	</div>
</div>
 
<div id="content">
</div>

<script>

	var jss={
			search:function(currentPage){
				var data={currentPage:currentPage,
								"createName":$("#createName").val(),
								"startTime":$("#beginDate").val(),
							    "endTime":$("#endDate").val(),
								"status":$("#withdrawStatus").val()
				};
				$.post("<%=basePath%>/finance/withdrawlistdo",data,function(d){
					$("#content").html(d);
				});
			}
		}
	
	jss.search(1);
	
	$("#btnSearch").click(function(){
		jss.search(1);
	});
	</script>
