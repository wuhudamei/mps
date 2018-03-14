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
	<title>给水工程</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/methodBuild.css"/>
</head>
<body>
	<div class="mar_btm300">
		<header>
			<a class="back_btn"  onclick="myhistory.back()" href="methodBuild.html"></a>
			<h2 class="title">给水工程</h2>
		</header><!-- /header -->
		<p class="pad_top11"></p>
		<ul class="bg_f">
			<a class="two" href="${ctx}/app/manager/method/three3jiaodi"><li class="list-text">交底定位放线</li></a>
			<a class="two" href="${ctx}/app/manager/method/three3ticao"><li class="list-text">剔槽</li></a>
			<a class="two" href="${ctx}/app/manager/method/three3guandao"><li class="list-text">管道安装</li></a>
			<a class="two" href="${ctx}/app/manager/method/three3yali"><li class="list-text">压力试验</li></a>
			<a class="two" href="${ctx}/app/manager/method/three3yumai"><li class="list-text">管道预埋</li></a>
			<a class="two" href="${ctx}/app/manager/method/three3famen"><li class="list-text">阀门，水表安装</li></a>
			<a class="two" href="${ctx}/app/manager/method/three3weiyu"><li class="list-text">卫浴龙头安装</li></a>
			<a class="two" href="${ctx}/app/manager/method/three3matong"><li class="list-text">马桶安装</li></a>
			<a class="two" href="${ctx}/app/manager/method/three3wujin"><li class="list-text">五金挂件安装</li></a>
		</ul>
	</div>
</body>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</html>