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
	<title>工艺工法</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/methodBuild.css"/>
</head>
<body>
	<div class="mar_btm300">
		<header>
			<a class="back_btn"  onclick="myhistory.back()" href="${ctx}/app/manager/method/methodOne"></a>
			<h2 class="title">工艺工法</h2>
		</header><!-- /header -->
		<p class="pad_top88 bg_f font30 col_blue pad_left3 hgt76 shadow">施工工艺考核要求</p>
		<p class="tit-p">一、水电工程</p>
		<ul class="bg_f">
			<a class="two" href="${ctx}/app/manager/method/methodBuildOne"><li class="list-text">强电工程</li></a>
			<a class="two" href="${ctx}/app/manager/method/methodBuildTwo"><li class="list-text">弱电工程</li></a>
			<a class="two" href="${ctx}/app/manager/method/methodBuildThree"><li class="list-text">给水工程</li></a>
			<a class="two" href="${ctx}/app/manager/method/methodBuildFour"><li class="list-text">排水工程</li></a>
		</ul>
		<p class="tit-p">二、泥瓦工程</p>
		<ul class="bg_f">
			<a class="two" href="${ctx}/app/manager/method/methodNiwa"><li class="list-text">泥瓦工程</li></a>
		</ul>
		<p class="tit-p">三、木工工程</p>
		<ul class="bg_f">
			<a class="two" href="${ctx}/app/manager/method/methodWood"><li class="list-text">木工工程</li></a>
		</ul>
		<p class="tit-p">四、油工工程</p>
		<ul class="bg_f">
			<a class="two" href="${ctx}/app/manager/method/methodPaint"><li class="list-text">油工工程</li></a>
		</ul>
	</div>
</body>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</html>