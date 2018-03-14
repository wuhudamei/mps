<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>辅料申请记录</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/budget.css"/>
</head>
<body>
	<div class="g-budget g-switch_record">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/auxiliary/order"></a>
			<h2 class="title">辅料申请记录</h2>
			<h2 class="title">${error}</h2>
		</header><!-- /header -->
		
		<c:if test="${ not empty purchaseList }">
		<section class="budget_section">
			<h3>${customerInfo}</h3>
			<c:forEach items="${purchaseList }" var="purchase" varStatus="status">
			<ul class="shadow">
				<li class="clearfix">
					<span>采购单编号 ：</span>
					<p>${purchase.purchaseCode }</p>
				</li>
				<li class="clearfix">
					<span>采购单状态 ：</span>
					<p>${purchase.status }</p>
				</li>
				<%-- <li class="clearfix">
					<span>第几次申请：</span>
					<p>第${status.index +1 }次申请</p>
				</li> --%>
				<li class="clearfix">
					<span>期望进场日期：</span>
					<p> <fmt:formatDate value="${purchase.hopeForTime }" pattern="yyyy-MM-dd"/>  </p>
				</li>
				<li class="clearfix">
					<span>申请时间：</span>
					<p><fmt:formatDate value="${purchase.applyTime }" pattern="yyyy-MM-dd HH:mm:ss"/>   </p>
				</li>
				<!-- 根据采购单id查询详情 -->
				<a class="details_btn" href="${ctx}/app/manager/auxiliaryApplyRecord/auxiliaryDetails?purchaseId=${purchase.purchaseId}&applyTime=<fmt:formatDate value="${purchase.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">查看详情</a>
			</ul>
			</c:forEach>
			
			</c:if>
			
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>