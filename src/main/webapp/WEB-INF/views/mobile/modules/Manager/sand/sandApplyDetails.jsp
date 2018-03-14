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
	<title>申请详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/metri/metriGet.css"/>
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/applySand/sandRecord?orderId=${order.id}"></a>
			<h2 class="title">申请详情</h2>
		</header><!-- /header -->
		<div class="pad_top88">
			<p class="font30 tit-pos">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
			<ul class="pad_left3">
				<li class="mar_btm24 clearfix">
					<span class="col_grey font28 flo_left left-label">采购单编号：</span>
					<p class="font28 col_3 pad_rgt3 flow">${purchase.purchaseCode }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="col_grey font28 flo_left left-label">采购单状态：</span>
					<p class="font28 col_3 pad_rgt3 flow">${purchase.statusName }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="col_grey font28 flo_left left-label">期望进场日期：</span>
					<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${purchase.applyReceiveTime }" pattern="yyyy-MM-dd"/></p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="col_grey font28 flo_left left-label">供应商：</span>
					<p class="font28 col_3 pad_rgt3 flow">${purchase.supplierName }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="col_grey font28 flo_left left-label">供应商电话：</span>
					<p class="font28 col_3 pad_rgt3 flow">${purchase.supplierContactsName}-${purchase.supplierPhone }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="col_grey font28 flo_left left-label">是否有电梯：</span>
					<p class="font28 col_3 pad_rgt3 flow">
						<c:if test="${purchase.isElevator eq 0 }">
							否
						</c:if>
						<c:if test="${purchase.isElevator eq 1 }">
							是
						</c:if>
					</p>
				</li>
				<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left left-label">楼层：</span>
						<p class="font28 col_3 pad_rgt3 flow">${purchase.upstairsFloor }层</p>
					</li>
				<%-- <c:if test="${purchase.isUpstairs eq 1 }">
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left left-label">楼层：</span>
						<p class="font28 col_3 pad_rgt3 flow">${purchase.upstairsFloor }层</p>
					</li>
				</c:if> --%>
			</ul>
		</div>
		<ul>
			<c:forEach items="${goodsList }" var="goods">
				<li class="pad_top24 pad_btm24 pad_left30 pad_rgt34 bg_f bor_top bor_btm mar_btm24 mar_top28 clearfix">
					<div class="img_container">
						<c:if test="${empty goods.pic }">
							<img src="${ctxStatic}/mobile/modules/Manager/img/metri/eg.png" alt="">
						</c:if>
						<c:if test="${not empty goods.pic }">
							<img src="${baseUrl }${goods.pic }" alt="">
						</c:if>
					</div>
					<p class="font28 col_4e4f50 mar_left237"><span class="col_302f2f">【${goods.brand }】</span>${goods.name }</p>
					<p class="font26 col_4e4f50 pad_top2 mar_left237">规格：<span class="font24 col_5b5a5a">${goods.specifications }</span></p>
					<p class="font26 col_4e4f50 pad_top2 mar_left237 pad_btm2">单 价： ￥${goods.price }元/${goods.unit }</p>
					<p class="font26 col_0780ec mar_left237 pad_top14">申请数量：${goods.auxiMateCount }</p>
				</li>
			</c:forEach>
			<div style="padding-bottom:300px;"></div>
		</ul>
		<p class="sand-total">合计：<span class="sand-account">￥${purchase.totalPrice }元</span></p>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
</html>