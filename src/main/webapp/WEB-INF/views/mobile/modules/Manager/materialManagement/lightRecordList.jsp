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
	<title>灯具和五金申请记录</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/doneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/search.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/applyLightMaterial/lightMaterialList" onclick="myhistory.back()"></a>
			<h2 class="title">灯具和五金申请记录</h2>
		</header><!-- /header -->
		<section class="pad_top11">
		
			<p style="text-align:center;color:#123456;font-size:20px;">${customerInfo.communityName }-${customerInfo.buildNumber }-${customerInfo.buildUnit }-${customerInfo.buildRoom }-${customerInfo.customerName }</p>
			<c:forEach items="${MaterialsStandardReceive}" var="list">
			<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
				<a href="javascript:;" class="arrow_rgt pad_left3">
					<ul class="pad_top3">
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">申 请 单 号：</span>
							<p class="font28 col_3 flow">${list.materialsStandardReceiveBillCode }</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">申请单状态 ：</span>
							<p class="font28 col_3 flow">
							<c:if test="${list.status==10 }">
								项目经理已申请
							</c:if>
							<c:if test="${list.status==20 }">
								已领取
							</c:if>
							<c:if test="${list.status==30 }">
								已废弃
							</c:if>
							
							</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">领 取 日 期：</span>
							<p class="font28 col_3 flow">
							<fmt:formatDate value="${list.receiveDatetime }" pattern="yyyy-MM-dd"/>
							</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">申 请 时 间：</span>
							<p class="font28 col_3 flow">
								<fmt:formatDate value="${list.applyDatetime }" type="date" dateStyle="medium"/> 
							</p>
						</li>
					</ul>
				</a>
				<a class="one_btn" href="${ctx}/app/manager/applyLightMaterial/detail?id=${list.id}&orderId=${list.orderId}">详情</a>
			</div>
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>