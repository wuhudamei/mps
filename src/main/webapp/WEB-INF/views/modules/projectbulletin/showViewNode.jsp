<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<title>项目进度</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/progress_detail.css"/>
</head>
<body>
	<div class="g-progress_detail">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/projectbulletin/projectBulletin/list">返回</a></li>
		</ul>
		<header>
			<h2 class="title">项目进度</h2>
		</header><!-- /header -->
		<%-- <p><a href="${ctx}/projectbulletin/projectBulletin/">返回</a></p> --%>
		<ul class="main">
		<h2 style="text-align:center;font-size:25px;">${projectBulletin.communityName} - ${projectBulletin.buildNumber} - 
			${projectBulletin.buildUnit} - ${projectBulletin.buildRoom} - ${projectBulletin.customerName}
		</h2>
		<p style="text-align:center;font-size:25px;">
			<fmt:formatDate type="date" value="${projectBulletin.actualStartDate }"/></p>
			<div class="lines"></div>
			<c:forEach items="${pbList }" var="list">
			<li class="clearfix">
					<c:if test="${list.isDone == 1 }"><div class="left_num blue_bg">${list.nodeIndex }</div></c:if>
					<c:if test="${list.isDone == 0 }"><div class="left_num grey_bg">${list.nodeIndex }</div></c:if>
				<ul class="right_sec blue_bar clearfix">
					<li>
						<span class="pro_name col_3">${list.nodeName }</span>
						
						<c:if test="${list.doneDate != null}">
							<span class="pro_pre col_6">开工第${list.doneDate }天</span>
						</c:if>
						
						<span class="pro_date col_6"><fmt:formatDate type="date" value="${list.nodeRealDoneDate }"/></span>
					</li>
				</ul>
			</li>
			</c:forEach>
		</ul>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function() {
		
	});
	</script>
</body>
</html>