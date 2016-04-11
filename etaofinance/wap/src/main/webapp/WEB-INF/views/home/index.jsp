<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	//List
%>

	<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/home/index.css">
    <div class="g-wrap">
        <div class="g-views">
            
    <div class="swiper-container banner-1">
        <div class="swiper-wrapper">
            <div class="swiper-slide"><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_banner.jpg"></div>
            <div class="swiper-slide"><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_banner.jpg"></div>
            <div class="swiper-slide"><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_banner.jpg"></div>
            <div class="swiper-slide"><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_banner.jpg"></div>
        </div>
        <div class="swiper-pagination"></div>
    </div>
    <div class="swiper-container banner-2">
        <div class="swiper-wrapper">
            <div class="swiper-slide">
                <div class="banner-list">
                    <div class="list-new">
                        <div class="new-left"><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_5.png"></div>
                        <div class="new-right">
                            <ul>
                                <li><span>12%</span><b>年化收益</b></li>
                                <li><span>¥100</span><b>起投金额</b></li>
                                <li><span>7天</span><b>项目期限</b></li>
                            </ul>
                            <div class="progress">
                                <span class="bar" style="width:80%">
                            		<span class="triangle-bottom"></span>
                                <span class="percentage">80%</span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="banner-list">
                    <div class="list-new">
                        <div class="new-left"><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_5.png"></div>
                        <div class="new-right">
                            <ul>
                                <li><span>12%</span><b>年化收益</b></li>
                                <li><span>¥100</span><b>起投金额</b></li>
                                <li><span>7天</span><b>项目期限</b></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="banner-list">
                    <div class="list-new">
                        <div class="new-left"><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_5.png"></div>
                        <div class="new-right">
                            <ul>
                                <li><span>12%</span><b>年化收益</b></li>
                                <li><span>¥100</span><b>起投金额</b></li>
                                <li><span>7天</span><b>项目期限</b></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="swiper-pagination"></div>
    </div>
    <section class="index-container">
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
        <div class="load-more"><span>加载更多<i class="m-icon icon-arrow-r"></i></span>
            <!-- <div class="loader2"></div> -->
        </div>
    </section>
    <div class="footer top-line">
    <ul>
        <li class="on"><i class="m-icon icon-home"></i><span>首页</span></li>
        <li><i class="m-icon icon-instruction"></i><span>指南</span></li>
        <li><i class="m-icon icon-me"></i><span>我</span></li>
        <li><i class="m-icon icon-more"></i><span>更多</span></li>
    </ul>
</div>
    
        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/home/index.js"></script>

