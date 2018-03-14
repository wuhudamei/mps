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
	<title>订单工程结算单</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/accountList.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" onclick="history.go(-1)"></a>
			<h2 class="title">订单工程结算单</h2>
		</header><!-- /header -->
		<section class="pad_top11">
			<ul>
				<li class="bg_f border_top border_btm font0 list"><span class="font30 col_3 descript">中期提成</span><span class="font30 col_3 numbers"><fmt:formatNumber pattern="0.00" value="${balance.midBalanceMoney }"></fmt:formatNumber>  </span></li>
				<li class="bg_f border_top border_btm font0 list zhong"><span class="font30 col_3 descript">中期远程费提成</span><span class="font30 col_3 numbers"><fmt:formatNumber pattern="0.00" value="${balance.midDistanceFee}"></fmt:formatNumber></span></li>
				<li class="bg_f border_top border_btm font0 list finish"><span class="font30 col_3 descript">竣工提成</span><span class="font30 col_3 numbers"><fmt:formatNumber pattern="0.00" value="${balance.completeBalanceMoney }"></fmt:formatNumber></span></li>
				<li class="bg_f border_top border_btm font0 list jun"><span class="font30 col_3 descript">竣工远程费提成</span><span class="font30 col_3 numbers"><fmt:formatNumber pattern="0.00" value="${balance.completeDistanceFee }"></fmt:formatNumber></span></li>
			</ul>
			<div class="font0 tot-area">
				<span class="font26 col_3">当前合计</span>
				<span class="tot-money"><fmt:formatNumber pattern="0.00" value="${balance.totalMoney }"></fmt:formatNumber></span>
				<span class="font30 yuan">元</span>
			</div>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
</body>
</html>