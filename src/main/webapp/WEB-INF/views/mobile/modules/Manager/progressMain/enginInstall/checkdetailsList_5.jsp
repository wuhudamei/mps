<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>安装验收</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/applyDoneList.css"/>
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/orderInstallPlan/enginInstall"></a>
			<h2 class="title">安装验收</h2>
		</header><!-- /header -->
		<section class="pad_top88">
			<p class="font30 col_0780ec pt25 pb18 align_center">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom}-${order.customerName }</p>
			<p class="font30 col_0780ec pb25 align_center">实际开工日期：<fmt:formatDate type="date" value="${order.actualStartDate }"/></p>

			<c:forEach items="${installPlanList }" var="installAcceptance">

				<div class="sec font0 border_top border_btm mb24 bg_f clearfix">
					<ul class="pl30 pr30 pad_top3 bor_dashed">
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left pl1em">安装项名称：</span>
							<p class="font28 col_3 pad_rgt3 flow">${installAcceptance.installItemName }</p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left pl2em">安装模式：</span>
							<p class="font28 col_3 pad_rgt3 flow"> ${installAcceptance.installModeName} </p>
						</li>
						<li class="mb30 rele clearfix">
							<span class="col_grey font28 flo_left">计划安装日期：</span>
							<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate type="date" value="${installAcceptance.planIntoDate }"/></p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left pl2em">申请时间：</span>
							<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate type="date" value="${installAcceptance.applyIntoDate }"/></p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left pl1em">安装项状态：</span>
							<p class="font28 col_3 pad_rgt3 flow">${installAcceptance.statusName}</p>
						</li>
					</ul>
					<div class="btn_wrapper clearfix">
						<a class="btnBlueBg" href="${ctx}/app/manager/orderInstallPlan/acceptancePMdetail?id=${installAcceptance.id }&orderId=${order.id}&installMode=${installAcceptance.installMode} ">查看验收详情</a>
					</div>
				</div>
			</c:forEach>
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/css/installcheck/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/css/installcheck/js/utils/calcRootFontSize.js"></script>
</body>
</html>