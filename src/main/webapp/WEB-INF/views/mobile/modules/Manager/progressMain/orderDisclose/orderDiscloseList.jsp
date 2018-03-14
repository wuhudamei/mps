<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>现场交底</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/intent.css"/>
</head>

<body>
	<div class="g-sign_list">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/progressList"></a>
			<h2 class="title">现场交底</h2>
		</header>
		<section class="sign_list">
		<c:forEach items="${orderList}" var="p" varStatus="index">
			<div class="clearfix">
			<ul>
				<li class="clearfix">
					<span>顾客：</span>
					<p>${p.communityName }-${p.buildNumber }-${p.buildUnit }-${p.buildRoom }-${p.customerName }</p>
				</li>
				<li class="clearfix">
					<span>合同开工日期：</span>
					<p><fmt:formatDate value="${p.contractStartDate }" type="date"/></p>
				</li>
				<li class="clearfix">
					<span>合同竣工日期：</span>
					<p><fmt:formatDate value="${p.contractEndDate }" type="date"/></p>
				</li>
				<li class="clearfix">
					<span>合同工期：</span>
					<p>${p.contractTime }天</p>
				</li>
				<li class="clearfix">
					<span>订单状态：</span>
					<p class="">${fns:getDictLabel(p.orderStatusNumber, 'order_status', '')}</p>
				</li>
				<a href="${ctx}/app/manager/orderDiscloseDetail?id=${p.id}" class="sign_btn">交底</a>
			</ul>
			</div>
		</c:forEach>
		</section>
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>