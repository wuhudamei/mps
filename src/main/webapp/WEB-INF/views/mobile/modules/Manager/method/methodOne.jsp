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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/manage_method.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn"  href="${ctx}/app/${path}"></a>
			<h2 class="title">工艺工法</h2>
		</header><!-- /header -->
		<ul class="pad_top88">
			<a class="list-line mar_top26 bg_f gc_shigong" href="${ctx}/app/manager/method/methodBuild"><li class="list-text">施工工艺考核要求</li></a>
			<a class="list-line mar_top26 bg_f gc_daode" href="${ctx}/app/manager/method/methodDaode"><li class="list-text">道德品行考核要求（品行和德行是每个人内涵的外在表现）</li></a>
			<a class="list-line mar_top26 bg_f gc_kehutiyan" href="${ctx}/app/manager/method/methodKehu"><li class="list-text">客户体验篇（客户体验是企业的至高情怀）</li></a>
			<a class="list-line mar_top26 bg_f gc_zhiliang" href="${ctx}/app/manager/method/methodZhiliang"><li class="list-text">质量篇（质量是自身价值的直观体现）</li></a>
			<a class="list-line mar_top26 bg_f gc_jindu" href="${ctx}/app/manager/method/methodJindu"><li class="list-text">进度篇（进度反映出项目经理对工作安排的合理性、专业性）</li></a>
			<a class="list-line mar_top26 bg_f gc_safety" href="${ctx}/app/manager/method/methodSafety"><li class="list-text">安全篇（百年大计，安全第一）</li></a>
			<a class="list-line mar_top26 bg_f gc_chengpin mar_btm300" href="${ctx}/app/manager/method/methodChengpin"><li class="list-text">成品保护篇</li></a>
		</ul>
	</div>
</body>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</html>