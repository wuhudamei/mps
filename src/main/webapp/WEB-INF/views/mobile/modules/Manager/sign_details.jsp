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
	<title>签到详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/sign/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/sign/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/sign/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/sign/new1/list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/sign/new2/signList.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/signIndex" ></a>
			<c:if test="${not empty error }">
				<h2 class="title">${error }</h2>
			</c:if>
			<c:if test="${empty error }">
				<h2 class="title">签到详情</h2>
			</c:if>
		</header><!-- /header -->
		<section class="pad_top88 font0">
			<c:forEach items="${list }" var="signDetail">
				<div class="sec font0 border_top shadow mar_btm24 mar_top24 bg_f clearfix">
					<ul class="pad_top3">
						<li class="mb30 pad_left3 clearfix">
							<span class="col_grey font28 flo_left">顾客信息：</span>
							<p class="font28 col_3 pad_rgt3 flow">${signDetail.customerInfo}</p>
						</li>
						<li class="mb30 pad_left3 clearfix">
							<span class="col_grey font28 flo_left">签到日期：</span>
							<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${signDetail.signDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </p>
						</li>
						<li class="mb30 pad_left3 clearfix">
							<span class="col_grey font28 flo_left">客户地址：</span>
							<p class="font28 col_3 pad_rgt3 flow">${signDetail.customerAddress}</p>
						</li>
						<li class="mb30 pad_left3 clearfix">
							<span class="col_grey font28 flo_left">签到地址：</span>
							<p class="font28 col_3 pad_rgt3 flow">${signDetail.signAddress}</p>
						</li>
						<li class="mb30 pt30 pad_left3 bor_top_dashed clearfix">
							<i class="distanceError"></i>
							<span class="font28 col_blue">误差距离：${signDetail.signDistance}米</span>
						</li>
						<li class="mb30 pad_left3 clearfix">
							<i class="signState"></i>
							<span class="font28 col_e50e09">签到状态：
								<c:if test="${signDetail.isValid eq '0'}">不合格</c:if>
								<c:if test="${signDetail.isValid eq '1'}">合格</c:if></span>
						</li>
					</ul>
				</div>
			</c:forEach>
			<div style="padding-bottom:300px;"></div>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>
</html>