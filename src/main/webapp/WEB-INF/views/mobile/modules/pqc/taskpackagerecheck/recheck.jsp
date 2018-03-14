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
	<title>任务包复核</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/recheck.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/newCommon/common.css"/>
	<style type="text/css">
		.abandon {
		    background-position: 95% .1rem;
		}
	</style>
</head>
<body>
	<div class="g-done_apply">
		<header>
			<a class="back_btn" href="${ctx}/app/pqc/pqcIndex"></a>
			<h2 class="title">任务包复核</h2>
			<!-- <a class="search_btn" href="javascript:void(0)" onclick=""></a> -->
		</header><!-- /header -->
		<section class="done_apply_list">
			<c:forEach items="${list}" var="task">
				<div class="shadow ">
					<ul <c:if test="${task.isScrap eq '1'}"> class="abandon" </c:if>>
						<li class="clearfix">
							<span>任务包编号 ：</span>
							<p>${task.orderTaskPackageCode}</p>
						</li>
						<li class="clearfix">
							<span>任务包名称 ：</span>
							<p>${task.packageName}</p>
						</li>
						<li class="clearfix">
							<span>顾 客 信 息 ：</span>
							<p>${task.customerMessage}-${task.customerName}</p>
						</li>
					</ul>
					<a class="done_apply_btn" href="${ctx}/app/pqc/taskpackagerecheck/toTaskpackageRecheck?id=${task.id}">复核</a>
				</div>
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
</body>
</html>