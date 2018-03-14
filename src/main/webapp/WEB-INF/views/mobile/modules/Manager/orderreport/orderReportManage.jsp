<%-- <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">
<title>返单管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/homePage.css" />
</head>
<body>
	<div class="">
		<header class="header">
			<a class="back_btn" href="${ctx}/app/manager/toindex"></a>
			<h2 class="title">返单管理</h2>
		</header>
		<!-- /header -->
		
		<section class="pad_top88 font0">
			<ul class="mar_top24">
				<li><a class="list-line bg_f mar_btm26 return_brod" href="javascript:void(0)"><li class="list-text">上报返单</li></a>
				<li><a class="list-line bg_f return_reco" href="javascript:void(0)"><li class="list-text">返单记录</li></a>
			</ul>
		</section>
		<div style="padding-bottom: 300px;"></div>
	</div>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html> --%>
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
	<title>介绍客户</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/info.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/progress_manage.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/manage_budget.css"/>
</head>
<body>
<div class="g_info">
	<header>
		<a class="back_btn" href="${ctx}/app/manager/toindex"></a>
		<h2 class="title">介绍客户</h2>
	</header><!-- /header -->
	<ul class="info_ul">
		<li><a href="${ctx}/app/manager/orderReport/orderreport">介绍客户给公司</a></li>
		<li><a href="${ctx}/app/manager/orderReport/toOrderReportRecord">介绍记录</a></li>
	</ul>
</div>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>
</html>