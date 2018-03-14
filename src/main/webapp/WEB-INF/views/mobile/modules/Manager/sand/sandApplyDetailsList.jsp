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
	<title>申请记录</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css"/>
	<!-- <style>
		.bg_bo{background-color: #fff;background-image: url(${ctxStatic}/mobile/modules/Manager/img/metri/bg_bo.png);
	background-repeat: repeat-x;background-position: left bottom;}
	.tit-pos{line-height: .98rem;text-align: center;margin-bottom: .24rem;color: #0780ec;}
	</style> -->
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/applySand/orderList" ></a>
			<h2 class="title">申请记录</h2>
		</header><!-- /header -->
		<section class="pad_top88">
			<div class="bg_bo">
				<p class="font30 tit-pos">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
			</div>
			<c:forEach items="${purchaseList }" var="purchase">
				<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
					<ul class="pad_top3 pad_left3 bor_dotted">
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">采 购 单 号 ：</span>
							<p class="font28 col_3 pad_rgt3 flow">${purchase.purchaseCode }</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">采购单状态　：</span>
							<p class="font28 col_3 pad_rgt3 flow">${purchase.statusName }</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">期望到场日期：</span>
							<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${purchase.applyReceiveTime }" pattern="yyyy-MM-dd"/></p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">申 请 日 期 ：</span>
							<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${purchase.applyTime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">供　应　商　：</span>
							<p class="font28 col_3 pad_rgt3 flow">${purchase.supplierName }</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">供应商电话　：</span>
							<p class="font28 col_blue pad_rgt3 flow">${purchase.supplierContactsName}-${purchase.supplierPhone }</p>
						</li>
					</ul>
					<div class="clearfix">
						<a class="one_btn" href="${ctx}/app/manager/applySand/sandRecordDetails?purchaseId=${purchase.purchaseId}&orderId=${order.id}">详情</a>
					</div>
				</div>
			</c:forEach>
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>