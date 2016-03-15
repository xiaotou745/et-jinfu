<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.etaofinance.core.util.HtmlHelper"%>
<%@ page import="com.etaofinance.core.util.EnumHelper"%>
<%@ page import="com.etaofinance.core.enums.LeadInvestStatusEnum"%>

<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">ID:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="txtId" id="txtId" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">用户名:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="txtMemberName"
								id="txtMemberName" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">手机号:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="txtPhoneNo"
								id="txtPhoneNo" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">邮箱:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="txtMail"
								id="txtMail" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">申请时间:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value=""
								name="applyStartDate" id="applyStartDate"
								onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyEndDate\',{d:-1})||\'2030-10-01\'}'})" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">到</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value=""
								name="applyEndDate" id="applyEndDate"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'applyStartDate\',{d:1})}',maxDate:'2030-10-01'})" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">审核状态:</label>
						<div class="col-sm-8">
							<%=HtmlHelper.getSelect("auditStatus",
					EnumHelper.GetEnumItems(LeadInvestStatusEnum.class),
					"desc", "value", null, "-1", "全部")%>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<div class="col-sm-8">
							<button type="button" class="btn btn-w-m btn-primary"
								id="btnSearch" style="margin-left: 3px; height: 30px;">搜索</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="ibox-content" id="content"></div>
	</div>
</div>
