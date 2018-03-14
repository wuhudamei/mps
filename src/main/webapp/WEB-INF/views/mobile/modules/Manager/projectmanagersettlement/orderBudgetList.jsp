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
	<title>申请订单列表</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/list.css"/>
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/toQueryPmGuaranteeMoneyLog"></a>
			<h2 class="title">订单结算列表</h2>
		</header><!-- /header -->
		<section class="pt112">
			<div class="font0 search-area">
			<form action="${ctx}/app/manager/projectManagerSettlement/orderBudgetList">
				<input class="search-box" type="text" name="customerName" placeholder=" 小区名称、客户姓名、项目经理" value="${projectManagerSettlement.customerName }">
				<!-- <a class="search-btn" href="javascript:;"></a> -->
				<input type="submit" class="search-btn" style="border:none;cursor:pointer;">
			</form>
			</div>
			<c:forEach items="${list }" var="settleBill">
				<div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">
				<ul class="pad_top3 pad_left3 bor_dashed">
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left pl4em">顾客：</span>
						<p class="font30 col_3 pad_rgt3 flow">${settleBill.communityName }-${settleBill.buildNumber }-${settleBill.buildUnit }-${settleBill.buildRoom }-${settleBill.customerName }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">合同开工日期：</span>
						<p class="font30 col_3 pad_rgt3 flow">${settleBill.contractStartSate }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">实际开工日期：</span>
						<p class="font30 col_3 pad_rgt3 flow">${settleBill.actualStartDate }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left pl2em">合同工期：</span>
						<p class="font30 col_3 pad_rgt3 flow">${settleBill.contractTime }天</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left pl2em">订单状态：</span>
						<p class="font30 col_blue pad_rgt3 flow">${settleBill.orderStatusDescription }</p>
					</li>
				</ul>
				<div class="btn_wrapper clearfix">
				<c:if test="${settleBill.countType==2 }">
					<a class="btnBlueOne" href="${ctx }/app/manager/projectManagerSettlement/detailsAccountMid?orderId=${settleBill.orderId}&settleBillType=1">中期结算明细</a>
					<a class="btnBlueTwo" href="${ctx }/app/manager/projectManagerSettlement/detailsAccountMid?orderId=${settleBill.orderId}&settleBillType=2">竣工结算明细</a>
				</c:if>
				<c:if test="${settleBill.countType==1 }">
					<a class="btnBlueOne" href="${ctx }/app/manager/projectManagerSettlement/detailsAccountMid?orderId=${settleBill.orderId}&settleBillType=1">中期结算明细</a>
				</c:if>
				</div>
			</div>
			</c:forEach>
			
			
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>
</html>