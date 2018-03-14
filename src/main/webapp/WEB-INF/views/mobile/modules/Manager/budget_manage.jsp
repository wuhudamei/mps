<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">
<title>工程结算管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/20171024/homePage.css" />
</head>
<body>
	<div class="">
		<header class="header">
			<a class="back_btn" href="${ctx}/app/manager/toindex"></a>
			<h2 class="title">结算管理</h2>
		</header>
		<!-- /header -->
		<section class="pad_top88 font0">
			<p class="item-title">基本工资</p>
			<ul class="mar_top24">
				<a class="list-line bg_f icon settlementIcon01"
					href="${ctx}/app/manager/attendSettlement">
					<li class="list-text">考勤基本工资</li>
				</a>
			</ul>
			<p class="item-title">产业项目经理结算</p>
			<ul class="mar_top24">
				<a class="list-line bg_f icon settlementIcon02"
					href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmSettlementBudgetList">
					<li class="list-text">订单结算金额预览</li>
				</a>
				<a class="list-line bg_f icon settlementIcon03"
					href="${root}/mobile/modules/manager/projectmanagersettlement/web/orderSettlementSchedule/orderSettlementScheduleList">
					<li class="list-text">订单结算进度</li>
				</a>
				<a class="list-line bg_f icon settlementIcon04"
					href="${ctx}/app/manager/balancebyorder">
					<li class="list-text">订单结算明细</li>
				</a>
				<a class="list-line bg_f icon settlementIcon05"
					href="${ctx}/app/manager/balancebymonth/queryBalanceDetailList">
					<li class="list-text">月度结算明细</li>
				</a>
			</ul>
			<p class="item-title">传统项目经理结算</p>
			<ul class="mar_top24">
				<a class="list-line bg_f icon settlementIcon06"
					href="${ctx}/app/manager/tradition-manager-settle/order-settle.php">
					<li class="list-text">传统经理结算</li>
				</a>
			</ul>
			<p class="item-title">准产业项目经理结算</p>
			<ul class="mar_top24">
				<a class="list-line bg_f icon settlementIcon07"
					href="${ctx }/app/manager/projectManagerSettlement/budgetSureList">
					<li class="list-text">结算单金额确认</li>
				</a>
				<a class="list-line bg_f icon settlementIcon08"
					href="${ctx }/app/manager/projectManagerSettlement/orderBudgetList">
					<li class="list-text">订单结算明细</li>
				</a>
			</ul>
		</section>
		<div style="padding-bottom: 300px;"></div>
	</div>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>
</html>