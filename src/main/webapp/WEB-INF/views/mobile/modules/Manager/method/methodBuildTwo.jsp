<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>弱电工程</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/methodBuild.css"/>
</head>
<body>
	<div class="mar_btm300">
		<header>
			<a class="back_btn"  onclick="myhistory.back()" href="methodBuild"></a>
			<h2 class="title">弱电工程</h2>
		</header><!-- /header -->
		<p class="pad_top11"></p>
		<ul class="bg_f">
			<a class="two" href="${ctx}/app/manager/method/three2ruodian"><li class="list-text">交底定位放线</li></a>
			<a class="two" href="${ctx}/app/manager/method/three2dihe"><li class="list-text">底盒预埋</li></a>
			<a class="two" href="${ctx}/app/manager/method/three2xianguan"><li class="list-text">线管安装与固定</li></a>
			<a class="two" href="${ctx}/app/manager/method/three2anquan"><li class="list-text">安全保护</li></a>
			<a class="two" href="${ctx}/app/manager/method/three2yumai"><li class="list-text">线管预埋</li></a>
		</ul>
	</div>
</body>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</html>