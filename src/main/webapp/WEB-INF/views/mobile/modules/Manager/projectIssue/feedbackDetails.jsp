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
	<title>反馈报告</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/complaint/complainDetails.css"/>
</head>
<body>
	<div class="">
		<header class="header">
			<a class="back_btn" onclick="history.go(-1)"></a>
			<h2 class="title">反馈报告</h2>
		</header><!-- /header -->
		<section class="pad_top88 font0 bg_f bor_btm_ddd">
			<p class="font30 col_3 pt34 pb34 pl30 bor_dash">${mapInfo.customerInfo}</p>
			<ul class="ml30 mr30 pt25 pb35 bor_dash">
				<li class="mb30 clearfix">
					<span class="col_grey font28 flo_left">问题创建时间：</span>
					<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${mapInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></p>
				</li>
				<li class="mb30 clearfix">
					<span class="col_grey font28 flo_left indent2">问题事项：</span>
					<p class="font28 col_3 pad_rgt3 flow">${mapInfo.itemName}</p>
				</li>
				<li class="mb30 clearfix">
					<span class="col_grey font28 flo_left indent2">问题描述：</span>
					<p class="font28 col_3 pad_rgt3 flow">${mapInfo.problemDescribe}</p>
				</li>
			</ul>

			<c:forEach items="${workerList}" var="worker">
				<ul class="ml30 mr30 pt25 pb35 bor_dash">
					<li class="mb30 clearfix">
						<span class="col_grey font28 flo_left" style="text-indent: 1em;">反馈人：</span>
						<p class="font28 col_3 pad_rgt3 flow">工人-${worker.workerName}</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font28 flo_left">反馈时间：</span>
						<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${worker.statusDateTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </p>
					</li>
				</ul>

			</c:forEach>



			<%--<ul class="ml30 mr30 pt25 pb35">--%>
				<%--<li class="mb30 clearfix">--%>
					<%--<span class="col_grey font28 flo_left">反馈人：</span>--%>
					<%--<p class="font28 col_3 pad_rgt3 flow">工人-王二二</p>--%>
				<%--</li>--%>
				<%--<li class="mb30 clearfix">--%>
					<%--<span class="col_grey font28 flo_left indent2">反馈时间：</span>--%>
					<%--<p class="font28 col_3 pad_rgt3 flow">2017-06-29  15:36:23</p>--%>
				<%--</li>--%>
			<%--</ul>--%>
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>
</html>