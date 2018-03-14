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
<title>项目经理使用保金详情</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/guarMoney.css" />
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/queryPmGuaranteeMoneyLog"></a>
			<h2 class="title">质保金使用详情</h2>
		</header>
		<!-- /header -->
		<section class="pad_top88">
			<div class="bg_bo">
				<p class="font30 tit-pos">${bizGuaranteeMoneyPaidUsed.communityName}-${bizGuaranteeMoneyPaidUsed.buildNumber}-${bizGuaranteeMoneyPaidUsed.buildUnit}-${bizGuaranteeMoneyPaidUsed.buildRoom}-${bizGuaranteeMoneyPaidUsed.customerName}</p>
			</div>
			<ul class="pad_top3 pad_left3 pad_btm200 bor_btm bg_f">
				<li class="mar_btm24 clearfix"><span
					class="col_grey font28 flo_left spanRgt">使用金额：</span>
					<p class="font28 col_3 pad_rgt3 flow">-${bizGuaranteeMoneyPaidUsed.guaranteeMoneyAmount}</p></li>
				<li class="mar_btm24 clearfix"><span
					class="col_grey font28 flo_left spanRgt">使用时间：</span>
					<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${bizGuaranteeMoneyPaidUsed.guaranteeMoneyDateTime}" pattern="yyyy-MM-dd"/></p></li>
				<li class="mar_btm24 clearfix"><span
					class="col_grey font28 flo_left spanRgt">使用类型：</span>
					<p class="font28 col_3 pad_rgt3 flow">${fns:getDictLabel(bizGuaranteeMoneyPaidUsed.guaranteeMoneyFor,'use_guarantee_type', '')}</p></li>
				<li class="mar_btm24 clearfix"><span
					class="col_grey font28 flo_left spanRgt">原 因：</span>
					<p class="font28 col_3 pad_rgt3 flow">${bizGuaranteeMoneyPaidUsed.guaranteeMoneyReason}</p>
				</li>
			</ul>
		</section>
	</div>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>