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
	<title>问题上报</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/new/list.css"/>
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/pqc/issueReport/toIssueReportIndex"></a>
			<h2 class="title">问题上报</h2>
		</header><!-- /header -->
		<section class="pad_top11">

			<c:forEach items="${mapList}" var="map">


			<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
				<ul class="pad_top3 pad_left3 bor_dotted">
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">顾客信息：</span>
						<p class="font28 col_3 pad_rgt3 flow">${map.customerInfo}</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">项目经理：</span>
						<p class="font28 col_3 pad_rgt3 flow">${map.managerName}</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">约检内容：</span>
						<p class="font28 col_3 pad_rgt3 flow">${map.nodeName}</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">约检日期：</span>
						<p class="font28 col_3 pad_rgt3 flow">${map.qcExpectCheckDate}</p>
					</li>
				</ul>
				<div class="clearfix">
					<a class="one_btn" href="${ctx}/app/pqc/issueReport/issueReportDetail?issueId=${map.issueId}">查看详情</a>
				</div>
			</div>
			</c:forEach>

		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
</body>
</html>