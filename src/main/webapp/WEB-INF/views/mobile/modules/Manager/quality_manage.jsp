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
	<title>质量管理</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/info.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/progress_manage.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/manage_quality.css"/>
</head>
<body>
	<div class="g_info g_quality">
		<header>
			<a class="back_btn"  href="${ctx }/app/manager/toindex"></a>
			<h2 class="title">质量管理</h2>
		</header><!-- /header -->
		<ul class="info_ul">
			<li><a href="${ctx}/app/manager/qualityApply">申请约检</a></li>
			<li><a href="${ctx}/app/manager/recheck/recheckList">申请复检</a></li>
			<li><a href="${ctx}/app/manager/report/orderList">质检报告</a></li>
		</ul>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>
</html>