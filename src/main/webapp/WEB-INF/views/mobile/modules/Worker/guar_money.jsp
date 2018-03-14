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
	<title>我的质保金</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/guar_money.css"/>
</head>
<body>
<div class="g-done_demand">
	<header>
		<a class="back_btn" href="${ctx}/app/worker/myindex"></a>
		<h2 class="title">我的质保金</h2>
	</header><!-- /header -->
	<div class="total font0 pad_top11 pad_btm3 mar_btm2 bg_f shadow">
		<span class="font40 col_red bold">${totalAmount}</span>
		<p class="font24 col_6 mar_top2">当前质保金总额(元)</p>
	</div>
	<ul class="bg_f font0 mar_btm50 shadow">
		<c:forEach items="${list}" var="guarMoney">
			<li class="item border_btm clearfix">
				<div class="left">
					<span class="font24 col_9"><fmt:formatDate value="${guarMoney.deductTime}" pattern="yyyy-MM-dd" /></span>
					<span class="manager font26 col_6">项目经理：${guarMoney.itemCustomer}</span>
				</div>
				<div class="right">
					<span class="font30 col_3 mar_btm3"><em class="font30 col_red">${guarMoney.guaranteeMoneyAmount}</em>(质保金)</span>
					<span class="addr font26 col_6">${guarMoney.customerMessage}-${guarMoney.customerName}</span>
				</div>
			</li>
		</c:forEach>
	</ul>
</div>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
</body>
</html>