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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/quesDoneList.css"/>
</head>
<body>
	<div class="g-build_apply">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/problem/list"></a>
			<h2 class="title">问题上报</h2>
		</header><!-- /header -->
		<p class="every">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
		<p class="work_date">实际开工日期：<fmt:formatDate value="${order.actualStartDate }" pattern="yyyy-MM-dd"/></p>
		<section class="build_apply_list">
			<ul class="shadow">
				<li class="clearfix">
					<span>安装项名称</span>
	          		<span>期望进场日期</span>
					<span>操作</span>
				</li>
				<c:forEach items="${list }" var="install">
					<li class="clearfix">
						<span>${install.installItemName }</span>
		          		<span><fmt:formatDate value="${install.applyIntoDate }" pattern="yyyy-MM-dd"/></span>
		          		<span class="apply"><a class="block" href="${ctx }/app/manager/problem/reported_record_details?id=${install.id}&orderId=${install.orderId}&installItemName=${install.installItemName}">详情</a></span>
					</li>
				</c:forEach>
			</ul>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>