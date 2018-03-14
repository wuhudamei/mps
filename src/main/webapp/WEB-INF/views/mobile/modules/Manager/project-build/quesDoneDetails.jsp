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
	<title>问题上报详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/quesDone.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/problem/reported_record?orderId=${order.id}"></a>
			<h2 class="title">问题上报详情</h2>
		</header><!-- /header -->
		<p class="every">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
		<p class="xiang">${installItemName }</p>
		<section class="">
			<c:forEach items="${list }" var="problem">
				<ul class="item_lists bg_f font0 mar_btm3">
					<div class="tit_div">
						<span class="font28 col_blue"><fmt:formatDate value="${problem.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						<a class="see font28 col_blue" href="${ctx }/app/manager/problem/reported_record_details_picture?id=${problem.id}">查看照片</a>
					</div>
					<li class="mar_btm30">
						<span class="font28 col_6">问 题 分 类 ：</span>
						<span class="font28 col_3">${problem.typeName }</span>
					</li>
					<li class="mar_btm30 clearfix">
						<span class="font28 col_6">是否影响工期：</span>
						<span class="font28 col_3">
							<c:if test="${problem.isDelay==1 }">是</c:if>
							<c:if test="${problem.isDelay==0 }">否</c:if>
						</span>
					</li>
					<li class="mar_btm30 clearfix">
						<span class="font28 col_6">影响工期天数：</span>
						<span class="font28 col_3">
							<c:if test="${problem.isDelay==1 }">${problem.delayDays }天</c:if>
							<c:if test="${problem.isDelay==0 }">0天</c:if>
						</span>
					</li>
					<li class="mar_btm30 clearfix">
						<span class="font28 col_6 flo_left">描　　　　述：</span>
						<p class="font28 col_3 flow pad_rgt3">${problem.problemDescribe }</p>
					</li>
					<c:forEach items="${problem.logList }" var="log">
						<%-- <c:if test="${log.problemSolveRole==2 }">
							<li class="mar_btm30 clearfix">
								<span class="font28 col_6 flo_left">工程部反馈 ：</span>
								<p class="font28 col_3 flow pad_rgt3"><fmt:formatDate value="${log.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/><br />${log.statusName}<br />${log.problemSolveNotes }</p>
							</li>
						</c:if> --%>
						<c:if test="${log.problemSolveRole==3 }">
							<li class="mar_btm30 clearfix">
								<span class="font28 col_6 flo_left">材料部反馈 ：</span>
								<p class="font28 col_3 flow pad_rgt3"><fmt:formatDate value="${log.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/><br />${log.problemSolveNotes }</p>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>