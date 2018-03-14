<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>工艺工法</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/method.css"/>
</head>
<body>
	<div class="g-method">
		<header>
            <a class="back_btn" onclick="history.go(-1)"></a>
            <h2 class="title">工艺工法</h2>
        </header><!-- /header -->
		<section class="container clearfix">
			<a class="method_sec" href="${ctx}/app/worker/waterPower">
				<div class="item">
	                <img src="${ctxStatic}/mobile/modules/Worker/images/meth1.png" alt="" />
	                <h3>水电工程</h3>
	            </div>
			</a>
			<a class="method_sec" href="${ctx}/app/worker/mud">
				<div class="item">
	                <img src="${ctxStatic}/mobile/modules/Worker/images/meth2.png" alt="" />
	                <h3>泥瓦工程</h3>
	            </div>
			</a>
			<a class="method_sec" href="${ctx}/app/worker/wood">
				<div class="item">
	                <img src="${ctxStatic}/mobile/modules/Worker/images/meth3.png" alt="" />
	                <h3>木土工程</h3>
	            </div>
			</a>
			<a class="method_sec" href="${ctx}/app/worker/paint">
				<div class="item">
	                <img src="${ctxStatic}/mobile/modules/Worker/images/meth4.png" alt="" />
	                <h3>油工工程</h3>
	            </div>
			</a>
			<a class="method_sec" href="${ctx}/app/worker/funiture">
				<div class="item">
	                <img src="${ctxStatic}/mobile/modules/Worker/images/meth5.png" alt="" />
	                <h3>木作安装</h3>
	            </div>
			</a>
			<a class="method_sec" href="${ctx}/app/worker/service">
				<div class="item">
	                <img src="${ctxStatic}/mobile/modules/Worker/images/meth6.png" alt="" />
	                <h3>服务规范</h3>
	            </div>
			</a>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
</body>
</html>