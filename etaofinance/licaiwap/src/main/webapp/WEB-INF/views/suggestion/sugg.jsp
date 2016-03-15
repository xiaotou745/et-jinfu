<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.wap.url");
%>
<!DOCTYPE html>
<html>

<head>
    <title>易淘理财</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no, email=no" />
    <meta name="360-fullscreen" content="true" />
    <!-- QQ强制竖屏 -->
    <meta name="x5-orientation" content="portrait">
    <!-- QQ强制全屏 -->
    <meta name="x5-fullscreen" content="true">
    <!-- QQ应用模式 -->
    <meta name="x5-page-mode" content="app">
    <!-- UC强制全屏 -->
    <meta name="full-screen" content="yes">
    <!-- UC应用模式 -->
    <meta name="browsermode" content="application">
    <!-- uc强制竖屏 -->
    <meta name="screen-orientation" content="portrait">
  
    <link href="<%=basePath%>/css/common.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath%>/js/jquery-2.1.1.js"></script>

</head>

<body>
    <div id="a1" class="g-main lc">
        <div class="main">
            <img src="<%=basePath%>/img/lc_01.png" alt="">
            <img src="<%=basePath%>/img/lc_02.png" alt="">
            <img src="<%=basePath%>/img/lc_03.png" alt="">
            <img src="<%=basePath%>/img/lc_04.png" alt="">
            <img src="<%=basePath%>/img/lc_05.png" alt="">
            <form id="searchForm">
            <div class="form">
                <ul>
                    <li><input type="text" placeholder="请输入项目名称" name="projectname" id="projectname" maxlength="200"></li>
                    <li><input type="text" placeholder="请输入联系人姓名" name="username" id="username" maxlength="20"></li>
                    <li><input type="text" placeholder="请输入手机号" name="phoneno" id="phoneno" maxlength="11"></li>
                    <li><input type="text" placeholder="请输入电子邮箱" name="email" id="email" maxlength="50"></li>
                    <li><input type="text" placeholder="请输入贷款金额" name="dkamount" id="dkamount" maxlength="5"><i>万元</i></li>
                    <li><input type="text" placeholder="请输入库存金额" name="amount" id="amount" maxlength="15"><i>万元</i></li>
                    <li><input type="text" placeholder="请输入还款日期" name="repaymentdate" id="repaymentdate"></li>
                    <li><a href="javascript:void(0)" id="add">提交申请</a></li>
                </ul>
                <div id="datePlugin"></div>
            </div>
          	
            </form>
        </div>
        <!-- header-menu start -->
        <!-- $attr 用来使用当前block时，设置的参数 -->
        <div class="footer-menu">也可把资料发至我们的邮箱：xiangmu@etao.cn</div>
        <!-- header-menu end -->

    </div>
    <!-- warn: point:footer is not defined; modname islayout/c1 -->
    
    <script>
$("#add").click(function(){
	if(!check_null()) return;
	 var paramaters=$("#searchForm").serialize();
		var url = "<%=basePath%>/addsuggestion";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result>0) {
					$("#searchForm")[0].reset(); 
					$("#popup").show();
					setTimeout(function(){
						$("#popup").hide();
					}, 3000);
				} else {
					$("#popup_err").show();
					setTimeout(function(){
						$("#popup_err").hide();
					}, 3000);
				}
			},
			error:function(dt){
			}
		});
});
function check_email(){
	 var temp =$("#email");
     //对电子邮件的验证
     var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
     if(!myreg.test(temp.val()))
     {
    	 return false;
      }
     return true;
}
function check_null(){
	if($("#projectname").val()=="")
	{
		alert("请填写项目名称");
		return false;
	}
	if($("#username").val()=="")
	{
		alert("请填写联系人姓名");
		return false;
	}
	if($("#phoneno").val()=="" || $("#phoneno").val().length!=11)
	{
		alert("请正确填写手机号");
		return false;
	}
	if($("#email").val()=="" || !check_email())
	{
		alert("请正确填写电子邮箱");
		return false;
	}
	if($("#dkamount").val()=="")
	{
		alert("请填写贷款金额");
		return false;
	}
	if($("#amount").val()=="")
	{
		alert("请填写库存金额");
		return false;
	}
	if($("#repaymentdate").val()=="")
	{
		alert("请填写还款日期");
		return false;
	}
	return true;
}
function show_msg(msg){
	$("#pop_content").html(msg);
}
$(function(){
	$('#repaymentdate').date();
});
</script>
<link rel="stylesheet" href="<%=basePath%>/css/home.css" />
<script type="text/javascript" src="<%=basePath%>/js/date.js" ></script>
<script type="text/javascript" src="<%=basePath%>/js/iscroll.js" ></script>
<div class="popup" id="popup" style="display:none">
   <div class="succeed">
       <dl>
           <dt><i></i>提交成功</dt>
           <dd>我们会尽快与您联系！</dd>
       </dl>
   </div>
</div>
<div class="popup" id="popup_err" style="display:none">
    <div class="succeed">
        <dl>
            <dt><span></span>提交失败</dt>
            <dd>系统繁忙，请您稍后提交！</dd>
        </dl>
    </div>
</div>
</body>

</html>