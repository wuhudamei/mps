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
	<title>进度管理</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/info.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/progress_manage.css"/>
</head>
<body>
	<div class="g_info g-pro">
		<header>
			<a class="back_btn"  href="${ctx}/app/manager/toindex"></a>
			<h2 class="title">进度管理</h2>
		</header><!-- /header -->
		<ul class="info_ul">
			<li><a href="${root}/mobile/modules/manager/orderDemolitionBuild/web/orderDemolitionBuild/list">拆改交底</a></li>
			<li><a href="${ctx}/app/manager/orderDisclose">现场交底</a></li>
			<%-- <li><a href="${ctx}/app/manager/checksizeList">申请厂家复尺</a></li>
			<li><a href="${ctx}/app/manager/newChecksizeList">上报复尺</a></li> --%>
			<li><a href="${ctx}/app/manager/confirmStart">确认开工</a></li>
			<li><a href="${ctx}/app/manager/signIndex">现场签到</a></li>
			<li class="hgt_88 warn"><a class="font28 col_3 list_a" href="${ctx}/app/manager/progressWarning/list">预警监控</a></li>
			<li><a href="${ctx}/app/manager/progressBuiletin">进度通报</a></li>
			<li><a href="${ctx}/app/manager/completedList">申请竣工</a></li>
		</ul>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>
</html>