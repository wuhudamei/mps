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
	<title>质检报告</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/recheck.css"/>
</head>
<body>
	<div class="g-done_apply">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/report/orderList" ></a>
			<h2 class="title">质检报告</h2>
		</header><!-- /header -->
		<section class="done_apply_list">
			<c:forEach items="${list }" var="reportcheck">
				<div class="shadow">
						<ul>
							<li class="clearfix">
								<span>顾 客 信 息 ：</span>
								<p>${reportcheck.communityName }-${reportcheck.buildNumber }-${reportcheck.buildUnit }-${reportcheck.buildRoom }-${reportcheck.customerName }</p>
							</li>
							<li class="clearfix">
								<span>客户手机：</span>
								<p>${reportcheck.customerPhone }</p>
							</li>
							<li class="clearfix">
								<span>项目经理：</span>
								<p>${reportcheck.managerRealName }</p>
							</li>
							<li class="clearfix">
								<span>检查内容：</span>
								<c:if test="${reportcheck.qcBillType=='1' }">
									<p>【约检】${reportcheck.qcCheckNodeName }</p>
								</c:if>
								<c:if test="${reportcheck.qcBillType=='2' }">
									<p>【抽检】</p>
								</c:if>
								<c:if test="${reportcheck.isRecheck=='1' }">
									<p>【复检】</p>
								</c:if>
							</li>
							<li class="clearfix">
								<span>检查日期：</span>
								<p class=""><fmt:formatDate value="${reportcheck.checkDatetime }" pattern="yyyy-MM-dd HH:mm:ss" /></p>
							</li>
						</ul>
					<a class="done_apply_btn" href="${ctx}/app/manager/report/reportDetails?id=${reportcheck.id}">查看报告</a>
				</div>
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
</body>
</html>