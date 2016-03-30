<%@page import="org.apache.velocity.runtime.directive.Foreach"%>
<%@page import="com.etaofinance.entity.MemberApply"%>
<%@page import="com.etaofinance.entity.Member"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.HtmlHelper"%>
<%@page import="com.etaofinance.core.util.EnumHelper"%>
<%@page import="com.etaofinance.core.enums.MemberIncomeRecordEnum"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.BankCard"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity"%>
<%@page import="java.util.List"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
	Member member = (Member)request.getAttribute("member");
%>
<%
	if (member == null) {
%>
======暂无数据=====
<%
	} else {
		MemberApply followMemberApply=(MemberApply)request.getAttribute("followMemberApply");
		MemberApply leadMemberApply=(MemberApply)request.getAttribute("leadMemberApply");
		List<BankCard> bankCards=(List<BankCard>)request.getAttribute("bankCards");
		List<PublicProvinceCity> provincelist = (List<PublicProvinceCity>) request.getAttribute("provincelist");
		String pro_city = (String) request.getAttribute("pro_city");
		String city_region = (String) request.getAttribute("city_region");
%>
<!-- 省市区联动JS -->
<script type="text/javascript" src="<%=basePath%>/js/pro_city_region.js"></script>
<input type="hidden" id="pro_city" value="<%=pro_city%>" />
<input type="hidden" id="city_region" value="<%=city_region%>" />
<ul class="nav nav-tabs" id="myTab">
	<li class="active"><a href="#tabjbxx">基本信息</a></li>
	<li><a href="#tabszjl">收支记录</a></li>
	<li><a href="#tabsmrzxx">实名认证信息</a></li>
	<li><a href="#tabgtrxx">跟投人信息</a></li>
	<li><a href="#tabltrxx">领投人信息</a></li>
	<li><a href="#tabyhkxx">银行卡信息</a></li>
</ul>

<div class="tab-content">
	<input type="hidden" id="memberId" value="<%=member.getId()%>" />
	<!-- 基本信息开始 -->
	<div class="tab-pane active" id="tabjbxx">
		<form id="formjbxx">
			<div class="table-responsive" style="margin-top: 10px">
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td>用户名：</td>
							<td><%=member.getUsername()%></td>
						</tr>
						<tr>
							<td>手机号：</td>
							<td><%=member.getPhoneno()%></td>
						</tr>
						<tr>
							<td>邮箱：</td>
							<td><%=member.getEmail()%></td>
						</tr>
						<tr>
							<td>密码：</td>
							<td><input name="lblPassword" id="lblPassword" type="text"
								value="" /></td>
						</tr>
						<tr>
							<td>确认密码：</td>
							<td><input name="lblCfPassword" id="lblCfPassword"
								type="text" value="" /></td>
						</tr>
						<tr>
							<td>所属地区：</td>
							<td><div class="col-lg-3 form-group"><%=HtmlHelper.getSelect("provinceCode", provincelist, "name", "code", null,-1, "全部省份","width:120px")%></div>
								<div class="col-lg-3 form-group">
									<select id="cityCode" name="cityCode" style="width: 120px"
										class="form-control m-b">
										<option value="-1">全部城市</option>
									</select>
								</div>
								<div class="col-lg-3 form-group">
									<select id="regionCode" name="cityCode" style="width: 120px"
										class="form-control m-b">
										<option value="-1">全部区县</option>
									</select>
								</div></td>
						</tr>
						<tr>
							<td>性别：</td>
							<td><input type="radio" id="rSex1" name="rSexOpt" value="1"
								checked='<%=member.getSex() == 1 ? "checked" : ""%>' />男<input
								type="radio" id="rSxe0" name="rSexOpt" value="2"
								checked='<%=member.getSex() == 2 ? "checked" : ""%>' />女<input
								type="radio" id="rSxe3" name="rSexOpt" value="0"
								checked='<%=member.getSex() == 0 ? "checked" : ""%>'
								checked="checked" />未知</td>
						</tr>
						<tr>
							<td>简介：</td>
							<td><textarea name="txtDescription" id="txtDescription"
									style="width: 300px; height: 60px; max-width: 300px; max-height: 60px;"><%=member.getRemark()%></textarea></td>
						</tr>
						<tr>
							<td>状态：</td>
							<td><input type="radio" id="rStatus1" name="rStatusOpt"
								value="1"
								checked='<%=member.getIslock() != true ? "checked" : ""%>' />有效
								<input type="radio" id="rStatus0" name="rStatusOpt" value="0"
								<%=member.getIslock() != false ? "checked" : ""%> />无效</td>
						</tr>
					</tbody>
				</table>
				<div class="control-group">
					<button type="button" class="btn btn-w-m btn-primary"
						id="btnModifyCommit" style="margin-left: 3px; height: 30px;">保存</button>
				</div>
			</div>
		</form>
	</div>
	<!-- 基本信息结束 -->
	<!-- 收支记录开始 -->
	<div class="tab-pane" id="tabszjl">
		<form action="" id="formszjl">
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="row">
							<div class="col-lg-3">
								<div class="form-group">
									<label class="col-sm-4 control-label">ID:</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" name="txtId"
											id="txtId" />
									</div>
								</div>
							</div>
							<div class="col-lg-3">
								<div class="form-group">
									<label class="col-sm-4 control-label">时间:</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" value=""
											name="createStartDate" id="createStartDate"
											onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'createEndDate\',{d:-1})||\'2030-10-01\'}'})" />
									</div>
								</div>
							</div>
							<div class="col-lg-3">
								<div class="form-group">
									<label class="col-sm-4 control-label">到</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" value=""
											name="createEndDate" id="createEndDate"
											onFocus="WdatePicker({minDate:'#F{$dp.$D(\'createStartDate\',{d:1})}',maxDate:'2030-10-01'})" />
									</div>
								</div>
							</div>
							<div class="col-lg-3">
								<div class="form-group">
									<label class="col-sm-4 control-label">类型:</label>
									<div class="col-sm-8">
										<%=HtmlHelper.getSelect("incomeRecordType",
						EnumHelper.GetEnumItems(MemberIncomeRecordEnum.class),
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
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox-content" id="content"></div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- 收支记录结束 -->
	<!-- 实名认证信息开始 -->
	<div class="tab-pane" id="tabsmrzxx">
		<div class="table-responsive" style="margin-top: 10px">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td>真实姓名：</td>
						<td><%=member.getTruename()%></td>
					</tr>
					<tr>
						<td>身份证号：</td>
						<td><%=member.getIdcard()%></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 实名认证信息结束-->
	<!-- 跟投人信息开始 -->
	<div class="tab-pane" id="tabgtrxx">
		<div class="table-responsive" style="margin-top: 10px">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td>符合以下条件之一：</td>
						<td><%=followMemberApply.getApplyinfo()%></td>
					</tr>
					<tr>
						<td>所在公司：</td>
						<td><%=followMemberApply.getCompanyname()%></td>
					</tr>
					<tr>
						<td>职位/头衔：</td>
						<td><%=followMemberApply.getCompanytitle()%></td>
					</tr>
					<tr>
						<td>状态：</td>
						<td><%=followMemberApply.getStatusString()%></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 跟投人信息结束 -->
	<!-- 领投人信息开始 -->
	<div class="tab-pane" id="tabltrxx">
		<div class="table-responsive" style="margin-top: 10px">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td>符合以下条件之一：</td>
						<td><%=leadMemberApply.getApplyinfo()%></td>
					</tr>
					<tr>
						<td>证明材料：</td>
						<td><%=leadMemberApply.getCertifyMaterialName()%><a
							href="<%=leadMemberApply.getCertifyMaterialUrl()%>">下载</a></td>
					</tr>
					<tr>
						<td>状态：</td>
						<td><%=leadMemberApply.getStatusString()%></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!--  领投人信息结束-->
	<!-- 银行卡信息开始 -->
	<div class="tab-pane" id="tabyhkxx">
		<div class="table-responsive" style="margin-top: 10px">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>所属银行</th>
						<th>卡号</th>
						<th>类型</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (int i = 0; i < bankCards.size(); i++) {
					%>
					<tr>
						<td><%=bankCards.get(i).getBankname()%></td>
						<td><%=bankCards.get(i).getCardno()%></td>
						<td></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 银行卡信息结束 -->
</div>
<%
	}
%>
<script type="text/javascript">
	$(document).ready(function() {
		//初始化显示哪个tab
		$('#myTab a:first').tab('show');
		$('#myTab a').click(function(e) {
			e.preventDefault();//阻止a链接的跳转行为
			$(this).tab('show');//显示当前选中的链接及关联的content
		});
		//$('select').unbind('change');
		//初始化默认选择的省市区
		InitProvince();
	});	
	function InitProvince()
	{
		initProvinceCityChange();
		var pCode=<%=member.getProvincecode()%>;
		var cCode=<%=member.getCitycode()%>;
		var rCode=<%=member.getAreacode()%>;
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
	//基本信息保存
	$("#btnModifyCommit").click(function() {
		var reg = /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,50}$/;
		var pw=$.trim($("#lblPassword").val());
		var cfpw=$.trim($("#lblConfirmPassword").val());
		if (pw.length < 6) {
            alert('新密码长度必须大于等于6位！');
            return;
        }
		if(pw!=cfpw){
			alert("两次输入的密码不一致，请重新输入！");
			return;
		}
		if (!reg.test(pw)) {
            alert('新密码不符合密码策略，字母数字组成！');
            return;
        }
		var rSexOpt=$('input[name="rSexOpt"]:checked').val();
		var rStatusOpt=$('input[name="rStatusOpt"]:checked').val();
		var remark=$.trim($("#txtDescription").val());
// 		if(remark.length<2 || remark.length>20){
// 			alert("简介不能为空，2到20位！");
// 			return;
// 		}
		var paramaters={
				"loginpwd":$.trim(pw),
				"sex":rSexOpt,
				"islock":rStatusOpt,
				"provincecode":$("#provinceCode").val(),
				"citycode":$("#cityCode").val(),
				"areacode":$("#regionCode").val(),
				"remark":remark,
				"id":$("#memberId").val()
		};
		$.ajax({
			type:"POST",
			url:"<%=basePath%>/member/modifymember",
			data:paramaters,
			success:function(result){
				if(result==1){
					alert("成功！");
				}else{
					alert("修改失败！");
				}
			},
			error:function(e){
				alert("失败！");
			}
		});		
	});
	var jssMember={
			search:function(currentPage){	
				var id=$("#txtId").val();
				var startTime=$("#createStartDate").val();
				var endTime=$("#createEndDate").val();
				var incomeRecordType=$("#incomeRecordType").val();
				 var paramaters = { 
							 "currentPage":currentPage,
	 						 "id":id,
	 						 "createStartTime": startTime,
	 						 "createEndTime": endTime,
							 "typeId": incomeRecordType
						 };   
			        var url = "<%=basePath%>/member/incomerecordlistdo";
			$.ajax({
				type : 'POST',
				url : url,
				data : paramaters,
				success : function(result) {
					$("#content").html(result);
				},
				error : function(e) {
					alert(e);
				}
			});
		}
	};
	//会员记录搜索
	$("#btnSearch").click(function() {
		jssMember.search(1);
	});
</script>