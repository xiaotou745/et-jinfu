<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.etaofinance.core.util.HtmlHelper"%>
<%@ page import="com.etaofinance.core.util.EnumHelper"%>
<%@ page import="com.etaofinance.core.enums.MemberTypeEnum"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
%>

<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">评论人ID:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="评论人ID" class="form-control" id="memberid" maxlength="10"/>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">评论时间:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value=""
								name="applyStartDate" id="createStartDate"
								onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'createEndDate\',{d:-1})||\'2030-10-01\'}'})" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-2 control-label">到</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value=""
								name="applyEndDate" id="createEndDate"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'createStartDate\',{d:1})}',maxDate:'2030-10-01'})" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			<div class="col-lg-2">
					<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left: 62px;">查询</button>
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
                 var begindate=$("#createStartDate").val();
                 var enddate=$("#createEndDate").val();
				 var paramaters = { 
						 "memberid":memberid,
						 "beginCreatetime": begindate,
						 "endCreatetime":enddate,
						 "projectid":projectid,
						 "currentPage":currentPage
						 };        
			        var url = "../project/commentdo";
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
	
	function delComment(commentId){
		var data = {
				"id":commentId
		};
		$.post('<%=basePath%>/project/delcomment/',data,function(res){
			if(0 < res){
				alert('删除成功！');
				jss.search(1);
			
			}else{
				alert('删除失败！');
			}
		})
		
	}
</script>