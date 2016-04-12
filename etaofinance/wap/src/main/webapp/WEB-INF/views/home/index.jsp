<%@page import="com.etaofinance.entity.domain.ProjectModel"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%@page import="com.etaofinance.entity.ADVert" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	List<ADVert> adlist=(ArrayList<ADVert>)request.getAttribute("ADLIST");
	List<ProjectModel> proList=(ArrayList<ProjectModel>)request.getAttribute("proList");
%>

 <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/home/index.css">
    <div class="g-wrap">
        <div class="g-views">
            
    <div class="swiper-container banner-1">
        <div class="swiper-wrapper">
        	<%if(adlist==null||adlist.size()==0)
        	{
        		%>
        		<div class="swiper-slide"><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_banner.jpg"></div>
        		<% 
        	}else{
        		for(int i=0;i<adlist.size();i++)
        		{
        			%>	            
            		<div class="swiper-slide"><img src="<%=adlist.get(i).getImageurl()%>"></div>
        			<% 
        		}
        	}
        	%>
        </div>
        <div class="swiper-pagination"></div>
    </div>
    <div class="swiper-container banner-2">
        <div class="swiper-wrapper">
        	<!-- ↓↓↓↓↓新手专享滚动部分 -->
        	
        	<%if(proList==null||proList.size()==0)
        	{
        		%>
        		<div class="swiper-slide"></div>
        		<% 
        	}else{
        		for(int i=0;i<proList.size();i++)
        		{
        			%>	            
            		<div class="swiper-slide">
                	<div class="banner-list">
                    <div class="list-new">
                        <div class="new-left"><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_5.png"></div>
                        <div class="new-right">
                            <ul>
                                <li><span><%=proList.get(i).getInComeStr()%></span><b>年化收益</b></li>
                                <li><span><%=proList.get(i).getUnitpriceStr()%></span><b>起投金额</b></li>
                                <li><span><%=proList.get(i).getAmount()%></span><b>目标金额</b></li>
                            </ul>
                            <div class="progress">
                                <span class="bar" style="width:<%=proList.get(i).getSchedule()%>%">
                            		<span class="triangle-bottom"></span>
                                <span class="percentage"><%=proList.get(i).getSchedule()%>%</span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        			<% 
        		}
        	}
        	%>
           
		<!-- ↑↑↑↑↑↑↑↑↑↑↑↑↑↑ 新手专享滚动部分 -->
        </div>
        <div class="swiper-pagination"></div>
    </div>
    <section class="index-container">
    <!--这里放置列表  -->
    	
    
    <!-- 这里放置列表 -->
    <div class="load-more"><span>加载更多<i class="m-icon icon-arrow-r"></i></span>
    
    </div>
    </section>
    <div class="footer top-line">
    <ul>
        <li class="on"><a href="#"><i class="m-icon icon-home"></i><span>首页</span></a></li>
        <li><a href="#"><i class="m-icon icon-instruction"></i><span>指南</span></a></li>
        <li><a href="#"><i class="m-icon icon-me"></i><span>我</span></a></li>
        <li><a href="#"><i class="m-icon icon-more"></i><span>更多</span></a></li>
    </ul>
</div>
    
        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/home/index.js"></script>



