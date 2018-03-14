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
	<title>问题上报</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/new/list.css"/>
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/pqc/css/ques/quesUpDetails.css">
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" onclick="history.go(-1)"></a>
			<h2 class="title">问题上报</h2>
		</header><!-- /header -->
		<div class="pad_top88 mar_btm26 bor_top_ea bor_btm_ea">
			<div class="font30 col_3 pad_top24 pad_btm24 pad_left30 pad_rgt30">基础信息</div>
			<ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">
				<li class="mar_btm26 clearfix">
					<span class="font28 col_grey">顾客信息：</span>
					<p class="font28 col_grey">${map.customerInfo}</p>
				</li>
				<li class="mar_btm26 clearfix">
					<span class="font28 col_grey">约检内容：</span>
					<p class="font28 col_grey">${map.nodeName}</p>
				</li>
				<li class="mar_btm26 clearfix">
					<span class="font28 col_grey">约检日期：</span>
					<p class="font28 col_grey">${map.qcExpectCheckDate}</p>
				</li>
			</ul>
		</div>
		<div class="mar_btm26 bor_top_ea bor_btm_ea">
			<div class="font30 col_3 pad_top24 pad_btm24 pad_left30 pad_rgt30 rela">
				问题上报
				<%--<span class="font26 picNum seeBtn">（6）</span>--%>
				<%--<a class="font26 col_0780ec seeBtn" href="javascript:;">查看图片</a>--%>
			</div>
			<ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">
				<li class="mar_btm26 clearfix">
					<span class="font28 col_grey">问题类型：</span>
					<p class="font28 col_grey">${map.typeName}</p>
				</li>
				<li class="mar_btm26 clearfix">
					<span class="font28 col_grey">备注：</span>
					<p class="font28 col_grey">${map.problemDescribe}</p>


				<li class="mar_btm26 clearfix">
					<span class="font28 col_grey">延期天数：</span>
					<p class="font28 col_grey"><fmt:formatNumber value="${map.delayDays}" pattern="0"></fmt:formatNumber> 天</p>
				</li>

			</ul>
		</div>

		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/global.js"></script>
</html>