<%@page import="com.etaofinance.entity.Member"%>
<%@page import="com.etaofinance.entity.MemberOther"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
// 	MemberOther other=(MemberOther)request.getAttribute("other");
// 	int cardSize=(int)request.getAttribute("cardsize");
%>
<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/certification-leadinvestor.css">
    <div class="g-wrap">
        <div class="g-views">
            
<form data-role="form">
<section class="container bg">
	<div class="lead">
		<span>领头人可以在项目预热期优先认购项目</span>
		<p>您是符合以下条件之一的自然人投资者：</p>
		<ul>
			<li><i class="select"></i>有餐饮领域投资经验，且单笔投资额大于10万元人民币</li>
			<li><i class="select active"></i>两年以上餐饮业创业经验</li>
			<li><i class="select"></i>三年以上企业总监级以上岗位工作经验</li>
		</ul>
		<p>证明材料：</p>
		<div class="btn-toggle">
			<button data-role="upload">上传附件</button>
		</div>	
		<em class="file-name"></em>
	</div>
</section>
<section class="container bg">
	<div class="pledge">
		<i class="m-icon icon-approval"></i>
		<p>我承诺以上登记的所有信息属实，并对虚假信息产生的一切后果负责。我同意签署<span>《用户服务协议》</span>和<span>《风险揭示书》</span></p>
	</div>
	<div class="btn-toggle">
		<button disabled data-role="submit">提交审核</button>
	</div>	
</section>
<input type="hidden" name="typeid" data-role="typeid" value="2" />
<input type="hidden" name="applyinfo" data-role="applyinfo" value="两年以上餐饮业创业经验" />
<input type="hidden" name="certifymaterialname" data-role="certifymaterialname" />
<input type="hidden" name="certifymaterialurl"  data-role="certifymaterialurl"/>
</form>
<input type="file" id="upload" name="upload" value="请点击上传文件" style="display:none;" />

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/certification-leadinvestor.js"></script>


