<%@page import="java.util.stream.Collectors"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	String basePath = PropertyUtils.getProperty("java.wap.url");
	String strCity = request.getAttribute("openCity").toString();
%>
<!DOCTYPE html>
<html>
<head>
  <title>易淘众筹</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<%=basePath%>/css/home.css" />
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
<script src="<%=basePath%>/js/jquery-2.1.1.js"></script>
</head>
<body>
	<div id="a1" class="g-main ">

        <div class="main">
            <img src="<%=basePath%>/img/zc_01.png" alt="">
            <img src="<%=basePath%>/img/zc_02.png" alt="">
            <img src="<%=basePath%>/img/zc_03.png" alt="">
            <img src="<%=basePath%>/img/zc_04.png" alt="">
            <img src="<%=basePath%>/img/zc_05.png" alt="">
            <img src="<%=basePath%>/img/zc_06.png" alt="">
            <img src="<%=basePath%>/img/zc_07.png" alt="">
            <form id="searchForm">
            <div class="form" >
            	<input type="hidden" name="city" id="hidExistCitys">
                <input type="hidden" name="precity" id="hidExpectCity">
                <ul>
                    <li><input type="text" placeholder="请输入项目名称"  name="projectname" id="projectname"></li>
                    <li><input type="text" placeholder="请输入联系人姓名" name="username" id="username"></li>
                    <li><input type="text" placeholder="请输入联系方式" name="phoneno" id="phoneno" maxlength="11"></li>
                    <li><input type="text" placeholder="请输入电子邮箱" name="email" id="email"></li>
                    <li><input type="tel" placeholder="请输入现有店铺数量" name="count" id="count"><i>家</i></li>
                    
                     
                    
                    <li><input type="text" placeholder="请选择现有店铺所在地(可多选)" id="existCitys" disabled="disabled"><span></span></li>
                    <li><input type="text" placeholder="预期众筹店铺所在地" id="expectCity" disabled="disabled"><span></span></li>
                    <li><input type="text" placeholder="预期众筹金额" name="amount" id="amount"><i>元</i></li>
                    <li><a href="javascript:void(0)" id="add">提交申请</a></li>
                </ul>
            </div>
            </form>
        </div>
        <!-- header-menu start -->
        <!-- $attr 用来使用当前block时，设置的参数 -->
        <div class="footer-menu">也可把资料发至我们的邮箱：xiangmu@etao.cn</div>
        <!-- header-menu end -->

         <div class="popup hide" id="popup_existCitys">
            <div class="popup_bg">
               <%=strCity %>
            </div>
            <a href="javascript:" id="complete">完成</a>
        </div>
          <div class="popup hide" id="popup_expectCity">
            <div class="popup_bg">
               <%=strCity %>
            </div>
            <a href="javascript:" id="complete">完成</a>
        </div>
        
    </div>
    <!-- warn: point:footer is not defined; modname islayout/c1 -->
      <div class="popup" style="display:none" id="popup">
            <div class="succeed">
                <dl>
                    <dt><i></i>提交成功</dt>
                    <dd>我们会尽快与您联系！</dd>
                </dl>
            </div>
        </div>
        <div class="popup" style="display:none" id="popup_err">
            <div class="succeed">
                <dl>
                    <dt><span></span>提交失败</dt>
                    <dd>系统繁忙，请您稍后提交！</dd>
                </dl>
            </div>
        </div>
</body>
   <script type="text/javascript" src="<%=basePath%>/js/crowdfunding.js"></script>

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
// 					 existCitys = [];
// 				     expectCity = [];
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
				$("#popup_err").show();
				setTimeout(function(){
					$("#popup_err").hide();
				}, 3000);
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
	if($("#count").val()=="" || isNaN($("#count").val()))
	{
		alert("请正确填写现有店铺数量");
		return false;
	}
	if($("#expectCity").val()=="")
	{
		alert("请填写众筹店铺所在地");
		return false;
	}
	if($("#amount").val()=="")
	{
		alert("请填写众筹金额");
		return false;
	}
	return true;
}
</script>
</html>