<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">
<title>查看项目经理评价</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/comment.css" />
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/pqc/apply-check-detail/detail?orderId=${bizEvalManagerDetail.orderId}"></a>
			<h2 class="title">查看项目经理评价</h2>
		</header>
		<!-- /header -->
		<section class="pad_top11">
			<div
				class="sec font0 border_btm mar_btm24 bg_f arrow_rgt pad_left3 clearfix">
				<ul class="pad_top3">
					<li class="mar_btm24 clearfix"><span
						class="col_grey font28 flo_left spanRgt30">顾客：</span>
						<p class="font28 pad_rgt3 col_3 flow">${bizEvalManagerDetail.communityName}-${bizEvalManagerDetail.buildNumber}-${bizEvalManagerDetail.buildUnit}-${bizEvalManagerDetail.buildRoom}-${bizEvalManagerDetail.customerName}</p>
					</li>
					<li class="mar_btm24 clearfix"><span
						class="col_grey font28 flo_left spanRgt30">客户电话：</span>
						<p class="font28 pad_rgt3 col_3 flow">${bizEvalManagerDetail.customerPhone}</p>
					</li>
					<li class="mar_btm24 clearfix"><span
						class="col_grey font28 flo_left spanRgt30">项目经理：</span>
						<p class="font28 pad_rgt3 col_3 flow">${bizEvalManagerDetail.itemManager}-${bizEvalManagerDetail.itemPhone}</p>
					</li>
					<li class="mar_btm24 clearfix"><span
						class="col_grey font28 flo_left spanRgt30">约检内容：</span>
						<p class="font28 pad_rgt3 col_3 flow">${bizEvalManagerDetail.checkNodeName}</p>
					</li>
				</ul>
			</div>
			<div class="work-sec font0 pad_btm24 mar_btm24">
				<div class="font30 col_6 pad_top24 pad_btm24 pad_left3 pad_rgt3">评价项目经理</div>
				<ul class="pt46 pb50 pad_left3 bg_f">
					<c:forEach items="${evalStoreList}" var="evalStore" varStatus="status">
						<li class="mar_btm46 clearfix a"><input type="hidden"
							id="selectCount${status.index}" value="${evalStore.selectCount}" />
							<span class="col_3 font28 flo_left mar_ght52 top-small">${evalStore.indexName}：</span>
							<p class="flow star-p" id="starP${status.index}">
								<span class="star1"></span> <span class="star1"></span> <span
									class="star1"></span> <span class="star1"></span> <span
									class="star1"></span>
							</p></li>
					</c:forEach>
					<!-- <li class="mar_btm46 clearfix a">
						<span class="col_3 font28 flo_left mar_ght52 top-small">质评价项目经理量：</span>
						<p class="flow star-p">
							<span class="star2"></span>
							<span class="star2"></span>
							<span class="star2"></span>
							<span class="star2"></span>
							<span class="star1"></span>
						</p>
					</li>
					<li class="mar_btm46 clearfix">
						<span class="col_3 font28 flo_left mar_ght52 top-small">质评价项目经理量：</span>
						<p class="flow star-p">
							<span class="star2"></span>
							<span class="star2"></span>
							<span class="star2"></span>
							<span class="star1"></span>
							<span class="star1"></span>
						</p>
					</li>
					<li class="mar_btm46 clearfix">
						<span class="col_3 font28 flo_left mar_ght52 top-small">质评价项目经理量：</span>
						<p class="flow star-p">
							<span class="star2"></span>
							<span class="star2"></span>
							<span class="star1"></span>
							<span class="star1"></span>
							<span class="star1"></span>
						</p>
					</li>
					<li class="mar_btm46 clearfix">
						<span class="col_3 font28 flo_left mar_ght52 top-small">安全文明施工：</span>
						<p class="flow star-p">
							<span class="star2"></span>
							<span class="star2"></span>
							<span class="star1"></span>
							<span class="star1"></span>
							<span class="star1"></span>
						</p>
					</li> -->
				</ul>
			</div>
		</section>
	</div>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
<script>
$(function(){
	showStore();
});

function showStore(){
	var length = '${fn:length(evalStoreList)}';
	for(var i=0;i<length;i++){
		var selectCount = $("#selectCount"+i).val();
		$("#starP"+i).find(".star1:lt("+selectCount+")").addClass("star2");
	}
}
</script>
</body>
</html>