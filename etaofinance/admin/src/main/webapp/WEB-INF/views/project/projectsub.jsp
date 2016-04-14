<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.etaofinance.core.util.HtmlHelper"%>
<%@ page import="com.etaofinance.core.util.EnumHelper"%>
<%@ page import="com.etaofinance.core.enums.MemberTypeEnum"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">会员ID:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="会员ID" class="form-control" id="memberid" maxlength="10"/>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">姓名:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="用户姓名" class="form-control" id="truename" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">手机号:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="手机号" class="form-control" id="phoneno" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
					<label class="col-sm-4 control-label">邮箱:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="邮箱" class="form-control" id="email" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">是否支付:</label>
						<div class="col-sm-5">
							<select name="refundid" id="refundid" class="form-control m-b"> 
								<option value="">全部</option> 
								<option value="0">未退款</option> 
								<option value="1">已退款</option>
							</select>
						</div>
					</div>
				</div>
			<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left: 665px;">查询</button>
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
<script>
var GetQueryString = function(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    var context = "";
    if (r != null)
        context = r[2];

    reg = null;
    r = null;

    return context == null || context == "" || context == "undefined" ? "" : context;
}
	var jss={
			search:function(currentPage){
				 var projectid= GetQueryString("id");
                 var memberid = $("#memberid").val();          
                 var truename=$("#truename").val();
                 var phoneno=$("#phoneno").val();
                 var email=$("#email").val();
                 var refundstatus=$("#refundid").val();
				 var paramaters = { 
						 "memberid":memberid,
						 "name": truename,
						 "phoneno": phoneno,
						 "email":email,
						 "projectid":projectid,
						 "refundstatus":refundstatus,
						 "currentPage":currentPage
						 };        
			        var url = "../project/projectsubdo";
			        $.ajax({
			            type: 'POST',
			            url: url,
			            data: paramaters,
			            success: function (result) {   			            
			            	$("#content").html(result);               
			            }
			        });
			}
		}	
	jss.search(1);
	$("#btnSearch").click(function(){
		jss.search(1);
	});
</script>