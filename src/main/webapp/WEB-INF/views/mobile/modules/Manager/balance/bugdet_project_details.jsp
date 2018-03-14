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
	<title>订单工程结算</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/order_budget.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="javascript:;" onclick="history.go(-1)"></a>
			<h2 class="title">订单工程结算</h2>
		</header><!-- /header -->
		<ul class="budget_list bg_f font0 shadow mar_btm50">
			<li class="border_btm clearfix">
				<span class="left font30 col_3">中期提成</span>
				<span class="num font30 col_red"><fmt:formatNumber pattern="0.00" value="${balance.midBalanceMoney }"></fmt:formatNumber>  </span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">竣工提成</span>
				<span class="num font30 col_9"><fmt:formatNumber pattern="0.00" value="${balance.completeBalanceMoney }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">自主支配项</span>
				<span class="num font30 col_red"><fmt:formatNumber pattern="0.00" value="${balance.freePayMoney }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">标化辅料</span>
				<span class="num font30 col_red"><fmt:formatNumber pattern="0.00" value="${balance.materialsStandardAmount }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">中期质检罚款</span>
				<span class="num font30 col_red"><fmt:formatNumber pattern="0.00" value="${balance.midFineMoney }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">竣工质检罚款</span>
				<span class="num font30 col_red"><fmt:formatNumber pattern="0.00" value="${balance.comleteFineMoney }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">中期奖励</span>
				<span class="num font30 col_red"><fmt:formatNumber pattern="0.00" value="${balance.midwayRewardAmount }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">竣工奖励</span>
				<span class="num font30 col_red"><fmt:formatNumber pattern="0.00" value="${balance.completeRewardAmount }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">中期扣款</span>
				<span class="num font30 col_red"><fmt:formatNumber pattern="0.00" value="${balance.midwayPunishAmount }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">竣工扣款</span>
				<span class="num font30 col_red"><fmt:formatNumber pattern="0.00" value="${balance.completePunishAmount }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">自采材料</span>
				<span class="num font30 col_red"><fmt:formatNumber pattern="0.00" value="${balance.materialSelfbuyReimburseAmount}"></fmt:formatNumber></span>
			</li> 
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">质保金</span>
				<span class="num font30 col_red"><fmt:formatNumber pattern="0.00" value="${balance.guaranteMoney }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">中期材料结算金额</span>
				<span class="num font30 col_red"><fmt:formatNumber pattern="0.00" value="${balance.midwayAuxiliaryMaterialsDeductAmount }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">竣工材料结算金额</span>
				<span class="num font30 col_red"><fmt:formatNumber pattern="0.00" value="${balance.completeAuxiliaryMaterialsDeductAmount }"></fmt:formatNumber></span>
			</li>
		</ul>
		<footer class="bg_f">
			<span class="foot_des font28 col_6">当前合计：<em class="col_red">¥<fmt:formatNumber pattern="0.00" value="${balance.totalMoney}"></fmt:formatNumber></em></span>
		</footer>
		
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar_nodate.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>


