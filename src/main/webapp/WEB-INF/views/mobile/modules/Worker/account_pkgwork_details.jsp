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
	<title>预算/结算金额详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/account_details.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/AccessoriesDeduct.css"/>
</head>
<body>
	<div class="content_right">
		<header class="header">
			<a class="back_btn" href="${ctx }/app/worker/account?id=${id }&settleStyle=2"></a>
			<h2 class="title"> 预算/结算工量详情</h2>
		</header><!-- /header -->
		<div class="accessories">
			<section> 
				<c:forEach items="${procedures }" var = "procedure" varStatus="status">
					<ul>
						<li>
							<span>工序名称 ：</span>
							<p>${procedure.procedureName }【施工】</p>
						</li>
						<li>
							<span>预算工程量 ：</span>
							<p>${procedure.laborPrice }元/${procedure.measurementUnitLabel }*<em>${procedure.budgetNumber }</em>=${procedure.laborDudgetAmount }元</p>
						</li>
						<li>
							<span>结算工程量 ：</span>
							<p>${procedure.laborPrice }元/${procedure.measurementUnitLabel }*<em>${procedure.settlementNumber }</em>=${procedure.laborSettleAmount }元</p>
						</li>
					</ul>
				</c:forEach>
			</section>
			<section class="bottom_fixed">
				<p class="total_price lh54">
					人工费预算总金额：<span>${budgetTotalMoney }元</span>
				</p>
				<p class="total_price lh54">
					人工费结算总金额：<span>${realTotalMoney } 元</span>
				</p>
			</section>
		</div>

		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
</body>
</html>