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
	<title>准产业项目经理结算</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/homePage.css"/>
</head>
<body>
	<div class="">
		<header class="header">
			<a class="back_btn" href="${ctx }/app/manager/toQueryPmGuaranteeMoneyLog"></a>
			<h2 class="title">准产业项目经理结算</h2>
		</header><!-- /header -->
		<section class="pad_top88 font0">
			<ul class="mar_top24">
				<a class="list-line bg_f managerConfirm" href="${ctx }/app/manager/projectManagerSettlement/budgetSureList"><li class="list-text">结算单金额确认</li></a>
				<a class="list-line bg_f managerDetails" href="${ctx }/app/manager/projectManagerSettlement/orderBudgetList"><li class="list-text">订单结算明细</li></a>
			</ul>
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>