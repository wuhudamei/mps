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
	<title>验收日志</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/allBtn.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/apply.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/common.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/layout.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/reset.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/header.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/public/style.css" />

</head>

<body>
<div class="wrap">
	<header>
		<a class="back_btn" href="${ctx}/app/manager/orderInstallPlan/installAcceptance?orderId=${orderId}"></a>
		<h2 class="title">验收日志</h2>
	</header>
	<!-- /header -->
	<section class="pt112 bor_btm_e5 ">
		<c:forEach items="${list}" var="orderInstallPlanAcceptLog">

			<div class="sec shadow sec-log mb30 clearfix">
				<p class="pb20 pad_top3 bg_f">
					<span class="pl30 font30 col_blue">
						<fmt:formatDate type="both" value="${orderInstallPlanAcceptLog.createDate}"/>
					</span>
				</p>
				<ul class="sec pad_top3 item mar_btm0 clearfix">
					<li class="mb30 rele clearfix">
						<p class="font30 col_3 flow">
							<span class="item-name">验收合格状态：</span>
							<c:if test="${orderInstallPlanAcceptLog.acceptType eq 1}">合格</c:if>
							<c:if test="${orderInstallPlanAcceptLog.acceptType eq 2}">不合格</c:if>
						</p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_3 flow clearfix">
							<span class="item-name pull-left">验收不合格原因：</span>
							<span class="pull-left item-txt">${orderInstallPlanAcceptLog.unqualifiedReasonConfigure}</span>
						</p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_3 flow">
							<span class="item-name pull-left">备    注 ：</span>
							<span class="pull-left item-txt">${orderInstallPlanAcceptLog.acceptRemarks}</span>
						</p>
					</li>
					<li class="rele clearfix">
						<p class="item-view border-top-dashed">
							<a href="${ctx}/app/manager/orderInstallPlan/unqualified_pic?id=${orderInstallPlanAcceptLog.id}" class="flex flex-between">
								<span><i class="icon icon-img">icon</i>查看图片</span>
								<span><i class="icon icon-arrow-right">icon</i></span>
							</a>
						</p>
					</li>
				</ul>
			</div>
		</c:forEach>
	</section>
	<div style="padding-bottom:300px;"></div>
</div>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
</body>

</html>