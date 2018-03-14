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
	<title>订单详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/details.css"/>
</head>
<body>
	<div class="g-details">
		<header>
			<a class="back_btn"  onclick="history.go(-1)"></a>
			<h2 class="title">订单详情</h2>
		</header><!-- /header -->
		<section class="order_list">
		
		
		<form:form id="inputForm" modelAttribute="appOrder" method="post" class="form-horizontal">
			<form:hidden path="id" />
			<sys:message content="${message}" />
			<h3>客户信息</h3>
			<ul>
				<li class="clearfix">
					<span>合同编号：</span>
					<p>${appOrder.contractNumber }</p>
				</li>
				<li class="clearfix">
					<span>客户姓名：</span>
					<p>${appOrder.customerName }</p>
				</li>
				<li class="clearfix">
					<span>客户手机：</span>
					<p>${appOrder.customerPhone }</p>
				</li>
				<li class="clearfix">
					<span>订单状态：</span>
					<p class="col_blue">${appOrder.orderStatusDescription }</p>
				</li>
			</ul>
			<h3>房屋信息</h3>
			<ul>
				<li class="clearfix">
					<span>施工地址：</span>
					<p>${appOrder.customerAddress }</p>
				</li>
				<li class="clearfix">
					<span>小区名称：</span>
					<p>${appOrder.communityName }</p>
				</li>
				<li class="clearfix">
					<span>门 牌 号：</span>
					<p>${appOrder.buildNumber }号楼-${appOrder.buildUnit }单元-${appOrder.buildRoom }室</p>
				</li>
				<li class="clearfix">
					<span>房屋户型：</span>
					<p>${appOrder.house }</p>
				</li>
				<li class="clearfix">
					<span>合同面积：</span>
					<p>${appOrder.contractArea }平米</p>
				</li>
			</ul>
			<h3>施工信息</h3>
			<ul class="mb20">
				<li class="clearfix">
					<span>合同开工日期：</span>
					<p><fmt:formatDate value="${appOrder.contractStartDate }" pattern="yyyy-MM-dd"/></p>
				</li>
				<li class="clearfix">
					<span>合同竣工日期：</span>
					<p><fmt:formatDate value="${appOrder.contractEndDate }" pattern="yyyy-MM-dd"/></p>
				</li>
				<li class="clearfix">
					<span>合 同 工 期 ：</span>
					<p>${appOrder.contractTime }天</p>
				</li>
				<li class="clearfix">
					<span>实际开工日期：</span>
					<p><fmt:formatDate value="${appOrder.actualStartDate }" pattern="yyyy-MM-dd"/></p>
				</li>
				<li class="clearfix">
					<span>实际竣工日期：</span>
					<p><fmt:formatDate value="${appOrder.actualEndDate }" pattern="yyyy-MM-dd"/></p>
				</li>
			</ul>
			<h3>人员信息</h3>
			<ul class="mb20">
				<li class="clearfix">
					<span>设计师：</span>
					<p>${appOrder.designerName }</p>
				</li>
				<li class="clearfix">
					<span>设计师电话：</span>
					<p>${appOrder.designerPhone }</p>
				</li>
				<li class="clearfix">
					<span>客服 ：</span>
					<p>${appOrder.serviceName }</p>
				</li>
				<li class="clearfix">
					<span>客户电话：</span>
					<p>${appOrder.servicePhone }</p>
				</li>
				<li class="clearfix">
					<span>跟单员：</span>
					<p>${appOrder.orderReporterName }</p>
				</li>
				<li class="clearfix">
					<span>跟单员电话：</span>
					<p>${appOrder.orderReporterPhone }</p>
				</li>
				<li class="clearfix">
					<span>质检员：</span>
					<p>${appOrder.orderInspector }</p>
				</li>
				<li class="clearfix">
					<span>质检员电话：</span>
					<p>${appOrder.orderInspectorPhone }</p>
				</li>
			</ul>
			
			
			</form:form>
			
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>