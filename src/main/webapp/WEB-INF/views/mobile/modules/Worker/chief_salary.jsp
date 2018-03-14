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
	<title>薪酬确认</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/done_apply.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/newCommon/common.css"/>
</head>
<body>
	<div class="g-done_apply">
		<header>
			<a class="back_btn" href="${ctx }/app/worker/toindex"></a>
			<h2 class="title">薪酬确认</h2>
		</header><!-- /header -->
		<section class="done_apply_list">
			<c:forEach var="taskPackage" items="${list }">
				<div class="">
					<a href="${ctx }/app/worker/account?id=${taskPackage.id }&settleStyle=${taskPackage.settleStyle}">
						<ul <c:if test="${taskPackage.isScrap eq '1'}">style="background: url(${ctxStatic}/mobile/modules/Manager/img/common/abandon.png) no-repeat;background-size: 1.28rem;background-position: 95% .8rem;" </c:if>>
							<li class="clearfix">
								<span>任务包名称：</span>
								<p>${taskPackage.packageName }</p>
							</li>
							<li class="clearfix">
								<span>项目经理：</span>
								<p>${taskPackage.itemCustomer }</p>
							</li>
							<li class="clearfix">
								<span>手 机 号：</span>
								<p>${taskPackage.managerPhone }</p>
							</li>
							<li class="clearfix">
								<span>施工地点：</span>
								<p>${taskPackage.customerMessage }-${taskPackage.customerName }</p>
							</li>
							<li class="clearfix">
								<span>施工时间：</span>
								<p>
								<fmt:formatDate value="${taskPackage.planStartdate }" pattern="yyyy-MM-dd" />
									至
								<fmt:formatDate value="${taskPackage.planStartdate }" pattern="yyyy-MM-dd" />
								</p>
							</li>
							<li class="clearfix">
								<span>任务包状态：</span>
								<p class="">${taskPackage.packageStatename }</p>
							</li>
						</ul>
					</a> 
					<c:if test="${taskPackage.packageStateid == '100' || taskPackage.packageStateid == '105'}">
						<a class="done_apply_btn" href="${ctx }/app/worker/confirmChief?id=${taskPackage.id }&settleStyle=${taskPackage.settleStyle}">确认薪酬</a>
					</c:if>
				</div>
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
</body>
</html>