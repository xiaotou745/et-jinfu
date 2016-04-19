<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@ page import="java.io.*"%>
<%
String basePath = PropertyUtils.getProperty("java.wap.url");
response.setStatus(HttpServletResponse.SC_OK);
//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
%>

<!DOCTYPE html>
<html>
<head>
    <title>404页面</title>
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta charset="UTF-8">
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
    <script src="<%=staticResPath%>/etao-crowdfunding/js/flexible/flexible_css.debug.js"></script>
    <script src="<%=staticResPath%>/etao-crowdfunding/js/flexible/flexible.debug.js"></script>
    <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/other/404.css">
    <script src="<%=staticResPath%>/etao-crowdfunding/js/mo.1.0.0.js"></script>
</head>

<body>
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container">
		<div class="error-image"></div>
		<div class="error-text"></div>
	</section>
	<seciton class="container">
		<div class="tips">服务器去泡妞了！</div>
	</seciton>
	<section class="container">
		<div class="btn-toggle">
			<a href="<%=basePath%>/home/index"><button>返回首页</button></a>
		</div>
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/other/404.js"></script>
    <div id="errorMessageDiv" style="display:none;">
	<pre>
                <%
                	try {
                		//全部内容先写到内存，然后分别从两个输出流再输出到页面
                		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                		PrintStream printStream = new PrintStream(byteArrayOutputStream);

                		printStream.println();

                		printStream.println("访问的路径: "+ request.getAttribute("javax.servlet.forward.request_uri"));
                		printStream.println();

                		Enumeration<String> e = request.getParameterNames();
                		if (e.hasMoreElements()) {
                			printStream.println("请求中的Parameter包括：");
                			while (e.hasMoreElements()) {
                				String key = e.nextElement();
                				printStream.println(key + "="+ request.getParameter(key));
                			}
                			printStream.println();
                		}
                		printStream.println("异常信息:");
                		Object msg = request.getAttribute("exception");
                		if (msg != null) {
                			printStream.println(msg);
                		} else {
                			printStream.println(exception.getClass() + " : "+ exception.getMessage());
                		}
                		printStream.println();
                		printStream.println("堆栈信息");
                		Object stackTrace = request.getAttribute("stackTrace");
                		if (stackTrace != null) {
                			printStream.println(stackTrace);
                		} else {
                			exception.printStackTrace(printStream);
                		}
                		printStream.println();
                		out.print(byteArrayOutputStream); //输出到网页
                	} catch (Exception ex) {
                		ex.printStackTrace();
                	}
                %>
            </pre>
</div>
</body>

</html>




