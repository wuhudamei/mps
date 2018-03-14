<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>复尺记录</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/review_record.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/newChecksizeList"></a>
			<h2 class="title">复尺记录</h2>
		</header>
		<ul class="font0 pad_top10">
			<c:forEach items="${scaleBillList}" var="scaleList" varStatus="status">
			<c:if test="${scaleList.type == '4' }">
			<li class="bg_f pad_left_p4">
				<a class="list bg_rgt_arr border_btm" href="${ctx}/app/manager/flatopenDetail?recheckID=${scaleList.id}&orderID=${orderID}">
					<span class="font28 col_3">平开门</span>
					<span class="font24 col_6 flo_rgt mar_rgt_p6">复尺日期：<fmt:formatDate type="date" value="${scaleList.planInstallDate }"/></span>
				</a>
			</li>
			</c:if>
			<c:if test="${scaleList.type == '3' }">
			<li class="bg_f pad_left_p4">
				<a class="list bg_rgt_arr border_btm" href="${ctx}/app/manager/pushpullDetail?recheckID=${scaleList.id}&orderID=${orderID}">
					<span class="font28 col_3">推拉门</span>
					<span class="font24 col_6 flo_rgt mar_rgt_p6">复尺日期：<fmt:formatDate type="date" value="${scaleList.planInstallDate }"/></span>
				</a>
			</li>
			</c:if>
			<c:if test="${scaleList.type == '1' }">
			<li class="bg_f pad_left_p4">
				<a class="list bg_rgt_arr" href="${ctx}/app/manager/taokouDetail?recheckID=${scaleList.id}&orderID=${orderID}">
					<span class="font28 col_3">套口</span>
					<span class="font24 col_6 flo_rgt mar_rgt_p6">复尺日期：<fmt:formatDate type="date" value="${scaleList.planInstallDate }"/></span>
				</a>
			</li>
			</c:if>
			<c:if test="${scaleList.type == '2' }">
			<li class="bg_f pad_left_p4 mar_top2 mar_btm2">
				<a class="list bg_rgt_arr" href="${ctx}/app/manager/curtainDetail?recheckID=${scaleList.id}&orderID=${orderID}">
					<span class="font28 col_3">窗帘</span>
					<span class="font24 col_6 flo_rgt mar_rgt_p6">复尺日期：<fmt:formatDate type="date" value="${scaleList.planInstallDate }"/></span>
				</a>
			</li>
			</c:if>
			<c:if test="${scaleList.type == '5' }">
			<li class="bg_f pad_left_p4">
				<a class="list bg_rgt_arr border_btm" href="${ctx}/app/manager/toiletDetail?recheckID=${scaleList.id}&orderID=${orderID}">
					<span class="font28 col_3">马桶</span>
					<span class="font24 col_6 flo_rgt mar_rgt_p6">复尺日期：<fmt:formatDate type="date" value="${scaleList.planInstallDate }"/></span>
				</a>
			</li>
			</c:if>
			<c:if test="${scaleList.type == '6' }">
			<li class="bg_f pad_left_p4">
				<a class="list bg_rgt_arr" href="${ctx}/app/manager/roomDetail?recheckID=${scaleList.id}&orderID=${orderID}">
					<span class="font28 col_3">浴室柜</span>
					<span class="font24 col_6 flo_rgt mar_rgt_p6">复尺日期：<fmt:formatDate type="date" value="${scaleList.planInstallDate }"/></span>
				</a>
			</li>
			</c:if>
			</c:forEach>
		</ul>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>