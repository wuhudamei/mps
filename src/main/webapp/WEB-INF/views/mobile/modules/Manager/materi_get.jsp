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
	<title>材料收货</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/intent.css"/>
</head>
<body>
	<div class="g-sign_list">
		<header class="header">
			<a class="back_btn" href="${ctx}/app/manager/meterialManagementList"></a>
			<h2 class="title">材料收货</h2>
		</header><!-- /header -->
		<nav>
			<a style="width: 25%;" class="nav" href="javascript:void(0)"><span class="active">辅料</span></a>
			<a style="width: 25%;" class="nav" href="javascript:void(0)"><span>墙地砖</span></a>
			<a style="width: 25%;" class="nav" href="javascript:void(0)"><span>开关面板</span></a>
			<a style="width: 25%;" class="nav" href="javascript:void(0)"><span>沙子水泥</span></a>
		</nav>
		<section class="sign_list materi_list">
			<c:forEach items ="${auxiliaryPurchases }" var="auxiliaryPurchase">
				<div class="clearfix shadow">
					<ul>
						<li class="clearfix">
							<span>顾客信息：</span>
							<p><%-- ${ auxiliaryPurchase.customerAddress}- --%>${ auxiliaryPurchase.communityName}-${ auxiliaryPurchase.buildNumber}-${ auxiliaryPurchase.buildUnit}-${ auxiliaryPurchase.buildRoom}-${auxiliaryPurchase.customerName }</p>
						</li>
						<li class="clearfix">
							<span>采购单编号 ：</span>
							<p class="">${ auxiliaryPurchase.purchaseCode}</p>
						</li>
						<li class="clearfix">
							<span>采购单状态：</span>
							<p class="">${fns:getDictLabel(auxiliaryPurchase.status, 'purchase_status', '')}</p>
						</li>
						<li class="clearfix">
							<span>采购单类型：</span>
							<p>辅料</p>
						</li>
						<li class="clearfix">
							<span>期望进场日期：</span>
							<p class="">
								<fmt:formatDate value="${ auxiliaryPurchase.applyReceiveTime}" pattern="yyyy-MM-dd"/>
							</p>
						</li>
						<li class="clearfix">
							<span>申请时间：</span>
							<p class="">
								<fmt:formatDate value="${ auxiliaryPurchase.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</p>
						</li>
					</ul>
					<a class="sign_btn" href="${ctx}/app/manager/materialList?id=${auxiliaryPurchase.id}&purchaseType=${auxiliaryPurchase.purchaseType}">收货</a>
				</div>
			</c:forEach>
		</section>
		<section class="sign_list materi_list undis">
			<c:forEach items ="${tilePurchases }" var="tilePurchase">
				<div class="clearfix shadow">
					<ul>
						<li class="clearfix">
							<span>顾客信息：</span>
							<p><%-- ${ tilePurchase.customerAddress}- --%>${ tilePurchase.communityName}-${ tilePurchase.buildNumber}-${ tilePurchase.buildUnit}-${ tilePurchase.buildRoom}-${tilePurchase.customerName }</p>
						</li>
						<li class="clearfix">
							<span>采购单编号 ：</span>
							<p class="">${ tilePurchase.purchaseCode}</p>
						</li>
						<li class="clearfix">
							<span>采购单状态：</span>
							<p class="">${fns:getDictLabel(tilePurchase.status, 'purchase_status', '')}</p>
						</li>
						<li class="clearfix">
							<span>采购单类型：</span>
							<p>墙地砖</p>
						</li>
						<li class="clearfix">
							<span>期望进场日期：</span>
							<p class="">
								<fmt:formatDate value="${ tilePurchase.applyReceiveTime}" pattern="yyyy-MM-dd"/>
							</p>
						</li>
						<li class="clearfix">
							<span>申请时间：</span>
							<p class="">
								<fmt:formatDate value="${ tilePurchase.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</p>
						</li>
					</ul>
					<a class="sign_btn" href="${ctx}/app/manager/materialList?id=${tilePurchase.id}&purchaseType=${tilePurchase.purchaseType}">收货</a>
				</div>
			</c:forEach>
		</section>
		<section class="sign_list materi_list undis">
			<c:forEach items ="${panelPurchases }" var="panelPurchase">
				<div class="clearfix shadow">
					<ul>
						<li class="clearfix">
							<span>顾客信息：</span>
							<p><%-- ${ panelPurchase.customerAddress}- --%>${ panelPurchase.communityName}-${ panelPurchase.buildNumber}-${ panelPurchase.buildUnit}-${ panelPurchase.buildRoom}-${panelPurchase.customerName}</p>
						</li>
						<li class="clearfix">
							<span>采购单编号 ：</span>
							<p class="">${ panelPurchase.purchaseCode}</p>
						</li>
						<li class="clearfix">
							<span>采购单状态：</span>
							<p class="">
							${fns:getDictLabel(panelPurchase.status, 'purchase_status', '')}</p>
						</li>
						<li class="clearfix">
							<span>采购单类型：</span>
							<p>开关面板</p>
						</li>
						<li class="clearfix">
							<span>期望进场日期：</span>
							<p class="">
								<fmt:formatDate value="${ panelPurchase.applyReceiveTime}" pattern="yyyy-MM-dd"/>
							</p>
						</li>
						<li class="clearfix">
							<span>申请时间：</span>
							<p class="">
								<fmt:formatDate value="${ panelPurchase.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</p>
						</li>
					</ul>
					<a class="sign_btn" href="${ctx}/app/manager/materialList?id=${panelPurchase.id}&purchaseType=${panelPurchase.purchaseType}">收货</a>
				</div>
			</c:forEach>
		</section>
		<section class="sign_list materi_list undis">
			<c:forEach items ="${sandPurchases }" var="sandPurchases">
				<div class="clearfix shadow">
					<ul>
						<li class="clearfix">
							<span>顾客信息：</span>
							<p>${ sandPurchases.communityName}-${ sandPurchases.buildNumber}-${ sandPurchases.buildUnit}-${ sandPurchases.buildRoom}-${sandPurchases.customerName }</p>
						</li>
						<li class="clearfix">
							<span>采购单编号 ：</span>
							<p class="">${ sandPurchases.purchaseCode}</p>
						</li>
						<li class="clearfix">
							<span>采购单状态：</span>
							<p class="">${fns:getDictLabel(sandPurchases.status, 'purchase_status', '')}</p>
						</li>
						<li class="clearfix">
							<span>采购单类型：</span>
							<p>沙子水泥</p>
						</li>
						<li class="clearfix">
							<span>期望进场日期：</span>
							<p class="">
								<fmt:formatDate value="${ sandPurchases.applyReceiveTime}" pattern="yyyy-MM-dd"/>
							</p>
						</li>
						<li class="clearfix">
							<span>申请时间：</span>
							<p class="">
								<fmt:formatDate value="${ sandPurchases.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</p>
						</li>
					</ul>
					<a class="sign_btn" href="${ctx}/app/manager/materialList?id=${sandPurchases.id}&purchaseType=${sandPurchases.purchaseType}">收货</a>
				</div>
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script>
		$(function(){
			var index0 = 0;
			$(document).on('click', '.nav', function(){
				index0 = $(this).index();
				$('.nav').find('span').removeClass('active');
				$(this).find('span').addClass('active');
				$('.materi_list').addClass('undis');
				$('.materi_list').eq(index0).removeClass('undis');
			});
		}());
	</script>
</body>
</html>