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
	<script src="<%=basePath%>/js/financing.js"></script>
</head>

<body>
    <div id="a1" class="g-main ytlc">
        <div class="main">
             <img src="<%=basePath%>/img/yl_01.png" alt="">
            <img src="<%=basePath%>/img/yl_02.png" alt="">
            <form id="searchForm">
            <div class="form">
                <ul>
                	<li><input type="text" placeholder="您的姓名" name="username" id="username" maxlength="20"></li>
                    <li><input type="text" placeholder="您的联系方式" name="phoneno" id="phoneno" maxlength="11"></li>
                    <li><input type="text" placeholder="您预期的理财收入" name="amount" id="amount" maxlength="15"><i>元</i></li>
                    <li><input type="text" placeholder="您的抗风险能力 低/中/高" disabled id="riskResistanceCapacity"></li>
                   
                    <li><input type="text" placeholder="您的年收入" id="yearamount" name="yearamount"><i>元</i></li>
                    <li><a href="javascript:void(0)" id="add" class="lc_btn">提交申请</a></li>
                </ul>
            </div>
          	 <input type="hidden" id="resistance" name="resistance"/>
            </form>
        </div>
        <!-- header-menu start -->
        <!-- $attr 用来使用当前block时，设置的参数 -->
        <div class="footer-menu">
            <ul>
                <li>项目咨询电话：010-52427371</li>
                <li>邮箱：xiangmu@etao.cn</li>
            </ul>
        </div>
        <!-- header-menu end -->
 		<div class="popup-mask hide"></div>
        <div class="popup-risk hide">
            <div class="close"><i></i></div>
            <div class="risk_con">
                <p>抗风险能力</p>
                <ul>
                    <li class="on">低</li>
                    <li>中</li>
                    <li>高</li>
                </ul>
            </div>
        </div>


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
// 					$("#searchForm")[0].reset(); 
					$("#popup").show();
					setTimeout(function(){
// 						$("#popup").hide();
						window.location.reload();
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
function check_null(){
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
	if($("#amount").val()=="")
	{
		alert("请填写预期的理财收入");
		return false;
	}
	if($("#resistance").val()=="")
	{
		alert("请填写抗风险能力");
		return false;
	}
	if($("#yearamount").val()=="")
	{
		alert("请填写您的年收入");
		return false;
	}
	return true;
}
function show_msg(msg){
	$("#pop_content").html(msg);
}

</script>
<link rel="stylesheet" href="<%=basePath%>/css/home.css" />
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