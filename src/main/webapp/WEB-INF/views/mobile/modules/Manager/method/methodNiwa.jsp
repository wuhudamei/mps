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
	<title>泥瓦工程</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/methodBuild.css"/>
</head>
<body>
	<div class="mar_btm300">
		<header>
			<a class="back_btn"  onclick="myhistory.back()" href="${ctx}/app/manager/method/methodBuild"></a>
			<h2 class="title">泥瓦工程</h2>
		</header><!-- /header -->
		<p class="pad_top11"></p>
		<ul class="bg_f">
			<a class="two" href="${ctx}/app/manager/method/niwaXinlao"><li class="list-text">新老墙砌筑搭接与抹灰挂网</li></a>
			<a class="two" href="${ctx}/app/manager/method/niwaMendong"><li class="list-text">门洞过梁</li></a>
			<a class="two" href="${ctx}/app/manager/method/niwaBao"><li class="list-text">包排水管道</li></a>
			<a class="two" href="${ctx}/app/manager/method/niwaShuini"><li class="list-text">水泥砂浆地面找平</li></a>
			<a class="two" href="${ctx}/app/manager/method/niwaShigao"><li class="list-text">石膏基地面找平</li></a>
			<a class="two" href="${ctx}/app/manager/method/niwaDimian"><li class="list-text">地面砖铺贴</li></a>
			<a class="two" href="${ctx}/app/manager/method/niwaDilou"><li class="list-text">地漏安装</li></a>
			<a class="two" href="${ctx}/app/manager/method/niwaQiangmian"><li class="list-text">墙面砖铺贴</li></a>
			<a class="two" href="${ctx}/app/manager/method/niwaTijiao"><li class="list-text">踢脚线铺贴</li></a>
			<a class="two" href="${ctx}/app/manager/method/niwaGuomen"><li class="list-text">过门石铺贴</li></a>
			<a class="two" href="${ctx}/app/manager/method/niwaFangshui"><li class="list-text">防水工程</li></a>
			<a class="two" href="${ctx}/app/manager/method/niwaChenxiang"><li class="list-text">沉箱回填</li></a>
		</ul>
	</div>
</body>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</html>