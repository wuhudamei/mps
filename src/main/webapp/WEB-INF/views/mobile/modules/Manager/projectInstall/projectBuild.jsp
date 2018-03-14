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
	<title>工程安装</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/list-line.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn"  href="${ctx }/app/manager/toindex"></a>
			<h2 class="title">工程安装</h2>
		</header><!-- /header -->
		<ul class="pad_top11">
			<a class="list-line bg_f gc_again" href="${ctx}/app/manager/checksize/checksizeList"><li class="list-text">申请厂家复尺</li></a> 
			<a class="list-line bg_f gc_apply" href="${ctx }/app/manager/newEnginInstallController/installListNew?index=0&apply=1"><li class="list-text">主材安装申请</li></a>
			<a class="list-line bg_f gc_question" href="${ctx}/app/manager/problem/list?installProblem=1"><li class="list-text">主材安装问题上报</li></a>
			<a class="list-line bg_f gc_yanshou" href="${ctx }/app/manager/orderInstallPlan/enginInstall"><li class="list-text">主材安装验收</li></a>
		</ul>
	</div>
</body>
</html>