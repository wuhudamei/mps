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
	<title>订单列表</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/doneList.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx }/app/pqc/indexMine"></a>
			<h2 class="title">订单列表</h2>
		</header><!-- /header -->
		<section class="pad_top11">
		<c:forEach items="${list }" var="order">
			<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
				<a href="javascript:;" class="arrow_rgt pad_left3">
					<ul class="pad_top3">
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">顾　　　　客：</span>
							<p class="font28 col_3 flow">${order.communityName }-${order.buildNumber}-${order.buildUnit}-${order.buildRoom }-${order.customerName }</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">合同开工日期：</span>
							<p class="font28 col_3 flow"><fmt:formatDate value="${order.contractStartDate}"
								pattern="yyyy-MM-dd" /></p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">订 单 状 态 ：</span>
							<p class="font28 col_blue flow">${order.orderStatus }</p>
						</li>
					</ul>
				</a>
				<a class="one_btn"  href="${ctx}/app/pqc/balanceOfOrder/balance_detail_by_order?id=${order.id}&inspectorId=${order.inspectorId}">结算详情</a>
			</div>
			</c:forEach>
		
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
</body>
</html>