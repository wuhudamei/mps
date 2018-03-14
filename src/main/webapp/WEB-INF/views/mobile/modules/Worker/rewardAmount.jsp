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
	<title>我的奖金</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/budgetDetails.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/salaryCheckDetails.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/guarMoney.css"/>
</head>
<body>
<div class="">
	<header>
		<a class="back_btn" href="${ctx}/app/worker/myindex"></a>
		<h2 class="title">我的奖金</h2>
	</header><!-- /header -->
	<section class="font0">
		<div class="tot-area-bonus">
			<p class="guar-con">当前奖金总额(元)</p>
			<p><span class="font28 sombal">￥</span><span class="num-tot" id="totalAmount">2000</span></p>
		</div>
		<ul class="rows">
			<c:forEach items="${rewardList}" var="reward" varStatus="status">
				<li class="row bg_f border_btm pad">
					<p class="pad_btm4 clearfix"><span class="col_orange font48 bold" id="rewardAmount${status.index}">${reward.rewardAmount}</span><span class="col_orange font28">元</span><span class="font30 col_3 flo_rgt">奖金</span></p>
					<p class="pad_btm2 clearfix"><span class="font28 col_3 flo_left">项目经理：${reward.itemCustomer}</span><span class="font28 col_3 flo_rgt"><fmt:formatDate value="${reward.rewardDatetime}" pattern="yyyy-MM-dd" /></span></p>
					<p class="font28 col_3 wid70">${reward.communityName}-${reward.buildNumber}-${reward.buildUnit}-${reward.buildRoom}-${reward.customerName}</p>
				</li>
			</c:forEach>
		</ul>
	</section>
</div>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
<script type="text/javascript">
	$(function(){
		var totalAmount = 0;
		var length = '${fn:length(rewardList)}';
		for(var i=0;i<length;i++){
			totalAmount = parseFloat(totalAmount) + parseFloat(($("#rewardAmount"+i).html() == null || $("#rewardAmount"+i).html() == '') ? 0 : $("#rewardAmount"+i).html());
		}

		$("#totalAmount").html(parseFloat(totalAmount).toFixed(2));
	});
</script>
</body>
</html>