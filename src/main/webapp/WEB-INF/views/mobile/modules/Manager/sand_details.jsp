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
	<title>沙子水泥扣除详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/AccessoriesDeduct.css"/>
</head>
<body>
	<div class="content_right">
		<header class="header">
			<a class="back_btn" href="${ctx }/app/manager/account?id=${id}&settleStyle=1"></a>
			<h2 class="title"> 沙子水泥扣除详情</h2>
		</header><!-- /header -->
		<div class="accessories">
			<section> 
				<c:forEach items="${sands}" var="auxiliary" varStatus="status">
					<ul>
						<li>
							<span>商品名称 ：</span>
							<p>【${auxiliary.brands }】${auxiliary.auxiliaryMaterialsName }</p>
						</li>
						<li>
							<span>规格型号 ：</span>
							<p>${auxiliary.specifications }</p>
						</li>
						<li>
							<span>使用数量 ：</span>
							<p>${auxiliary.usedCount } ${fns:getDictLabel(auxiliary.measurementUnit, 'biz_material_unit', '')}</p>
						</li>
						<li>
							<span>扣除金额 ：</span>
							<p>${auxiliary.laborPrice }元 /${fns:getDictLabel(auxiliary.measurementUnit, 'biz_material_unit', '')} * ${auxiliary.usedCount } ${fns:getDictLabel(auxiliary.measurementUnit, 'biz_material_unit', '')} =  ${auxiliary.price } 元</p>
						</li>
					</ul>
				</c:forEach>
			</section>
			<section class="bottom_fixed">
				<p class="total_price lh54">
					沙子水泥扣除合计金额：<span>${sandTatolPrice}</span>
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