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
	<title>质保金查询</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/guar_money.css"/>
</head>
<body>
<div class="g-done_demand">
	<header>
		<a class="back_btn" href="${ctx}/app/manager/toQueryPmGuaranteeMoneyLog"></a>
		<h2 class="title">质保金查询</h2>
	</header><!-- /header -->
	<div class="total font0 pad_top11 pad_btm3 mar_btm2 bg_f shadow">
		<span class="font30 col_red bold"></span>
		<p class="font24 col_6 mar_top2">合计金额</p>
	</div>
	<ul class="font0 mar_btm50">
		<c:forEach items="${list}" var="money">
			<li class="item mar_top2 bg_f clearfix">
				<div class="left"><span class="font col_blue font30">质</span></div>
				<div class="right">
					<span class="font24 col_9 mar_btm3"><em class="font30 col_red">${money.takeoffAmount}</em>元</span>
					<span class="font24 col_9 date"><fmt:formatDate value="${money.takeoffDatetime}" pattern="yyyy-MM-dd"/></span>
					<span class="font26 col_6">${money.communityName}-${money.buildNumber}-${money.buildUnit}-${money.buildRoom}-${money.customerName}</span>
				</div>
			</li>
		</c:forEach>
	</ul>
</div>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
<script type="text/javascript">
	$(function(){
		var totalMoney = 0;
		$("ul").find("em").each(function(){
			totalMoney = parseFloat(totalMoney) + parseFloat($(this).html());
		});
		$(".bold").html(parseFloat(totalMoney).toFixed(2)+"元");
	});
</script>
</body>
</html>