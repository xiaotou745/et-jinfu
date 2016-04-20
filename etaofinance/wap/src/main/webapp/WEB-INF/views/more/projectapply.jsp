<%@page import="com.etaofinance.entity.Member"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	Member member=(Member)request.getAttribute("member");
	String name=member.getTruename()==null?"":member.getTruename();
	String email=member.getEmail()==null?"":member.getEmail();
	String phone=member.getPhoneno()==null?"":member.getPhoneno();
	
%>

<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/more/project-apply.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<form action="" data-role="form">
		<section class="container bg top-gap">
			<div class="label-input">
				<label>项目名称</label>
				<input type="text" placeholder="请填写项目标题" name="title" data-role="title" maxlength="15"/>
			</div>
			<div class="label-select" data-role='industryType'>
				<label>项目所在行业</label>
				<span>餐饮</span>
			</div>
		</section>
		<section class="container bg">
			<div class="widget-upload">
				<div class="title">商业计划书</div>
				<div class="content">
					<button data-role="upload">上传附件</button>
					<div class="file-name"></div>
				</div>			
			</div>
		</section>
		<section class="container">
			<div>
				或者直接将您的融资计划书发送至：xiangmu@etao.cn，我们审核通过后，会及时与您联系。
			</div>
		</section>
		<section class="container bg">
			<div class="label-input">
				<label>联系人</label>
				<input type="text" name="contacts" data-role="contacts" value="<%=name%>" maxlength="10"/>
			</div>
		</section>
		<section class="container bg">
			<div class="label-input">
				<label>联系电话</label>
				<input type="text" name="phoneno" data-role="phoneno" value="<%=phone%>"maxlength="12"/>
			</div>
		</section>
		<section class="container bg">
			<div class="label-input">
				<label>联系邮箱</label>
				<input type="text" name="email" data-role="email" value="<%=email%>"maxlength="30"/>
			</div>
		</section>
		<section class="container top-gap">
			<div class="btn-toggle">
				<button data-role="submit" disabled>提交</button>
			</div>
		</section>
		    <div class="popup hide">
    	<div class="popup-title">
    		<div class="close"></div>
    	</div>
    	<div class="popup-content">
    		<div class="content-title">选择行业</div>
    		<div class="item active"><span>餐饮</span></div>
    		<div class="item"><span>娱乐</span></div>
    		<div class="item"><span>美食</span></div>
    		<div class="item"><span>其它</span></div>
    	</div>
    </div>
	<input type="hidden" name="ownedindustry" data-role="ownedindustry" value="餐饮"/>
		<input type="hidden" name="businessplanname" data-role="businessplanname" value=""/>
			<input type="hidden" name="businessplanurl" data-role="businessplanurl" value=""/>
	</form>
	<input type="file" id="upload" name="upload" value="请点击上传文件" style="display:none;" />
        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/more/project-apply.js"></script>

