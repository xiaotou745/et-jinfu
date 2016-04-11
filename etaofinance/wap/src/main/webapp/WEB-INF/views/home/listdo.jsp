<%@page import="com.etaofinance.entity.domain.ProjectModel"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	List<ProjectModel> proList=(ArrayList<ProjectModel>)request.getAttribute("proList");
%>
<div class="container-one">
            <div class="one-list container">
                <h3><b>易淘餐厅华腾世纪总部公园店</b><span>融资中</span></h3>
                <p><span><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/img_1.jpg"></span><b><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_6.png"></b>
                    <div class="progress">
                        <span class="bar" style="width:75%">
                            		<span class="triangle-bottom"></span>
                        <span class="percentage">75%</span>
                        </span>
                    </div>
                </p>
                <ul class="list-ul">
                    <li><span>12%+5%</span><b>年化收益</b></li>
                    <li><span>¥26万</span><b>目标金额</b></li>
                    <li><span>￥4500</span><b>起投金额</b></li>
                </ul>
            </div>
</div>
<div class="container-one">
    <div class="one-list container">
        <h3><b>易淘餐厅华腾世纪总部公园店</b><span class="one-acess">已成功</span></h3>
        <p><span><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/img_1.jpg"></span><b><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_7.png"></b></p>
        <ul class="list-ul">
            <li><span>12%+5%</span><b>年化收益</b></li>
            <li><span>¥26万</span><b>目标金额</b></li>
            <li><span>￥4500</span><b>起投金额</b></li>
        </ul>
    </div>
</div>
<div class="container-one">
    <div class="one-list container">
        <h3><b>易淘餐厅华腾世纪总部公园店</b><span class="one-txle">预热中</span></h3>
        <p><span><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/img_1.jpg"></span><b><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_6.png"></b></p>
        <ul class="list-ul">
            <li><span>12%+5%</span><b>年化收益</b></li>
            <li><span>¥26万</span><b>目标金额</b></li>
            <li><span>￥4500</span><b>起投金额</b></li>
        </ul>
    </div>
</div>
<div class="container-one">
    <div class="one-list container">
        <h3><b>易淘餐厅华腾世纪总部公园店</b><span>融资中</span></h3>
        <p><span><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/img_1.jpg"></span><b><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_7.png"></b></p>
        <ul class="list-ul">
            <li><span>12%+5%</span><b>年化收益</b></li>
            <li><span>¥26万</span><b>目标金额</b></li>
            <li><span>￥4500</span><b>起投金额</b></li>
        </ul>
    </div>
</div>
