<%@page import="com.etaofinance.core.util.Config"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
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
							<label class="col-sm-4 control-label">SQL:</label>
							<div class="col-sm-8">
							   <textarea rows="5" cols="50" id="sql"></textarea>
							</div>
						</div>
					</div>
				</div>
			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left:3px;">查询</button>
						</div>
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnExec" style="margin-left:3px;">执行</button>
						</div>
						<span id="tip" style="color:red"></span>
				</div>
		</div>
	</div>
</div>
<div id="content" style="overflow:scroll;height:580px;width:1300px;">
</div>
<script>
function execSQL(type){

	if(type==1){
		$("#tip").html("正在查询。。。");
	}else{
		$("#tip").html("正在执行。。。");
	}
	$("#btnSearch").attr("disabled",true);
	$("#btnExec").attr("disabled",true);
	var url='<%=basePath%>/admintools/execsql';
	var par={
			"sql":$('#sql').val(),
			"type":type
	};
	$.post(url,par,function(data){
		$("#tip").html("");
		$("#btnSearch").attr("disabled",false);
		$("#btnExec").attr("disabled",false);
		$('#content').html(data);
	});
}
$('#btnSearch').click(function(){
	execSQL(1);
});
$('#btnExec').click(function(){
	execSQL(2);
});
</script>