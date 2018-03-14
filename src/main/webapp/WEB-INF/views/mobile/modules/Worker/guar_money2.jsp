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
<title>质保金查询</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/guarMoney.css" />
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/worker/myindex"></a>
			<h2 class="title">质保金查询</h2>
		</header>
		<!-- /header -->
		<section class="font0">
			<div class="tot-area">
				<p class="pad_top52">
					<span class="font25 sombal">￥</span><span class="num-tot">${bizGuaranteeMoneyBalance.guaranteeMoneyBalance}=${bizGuaranteeMoneyBalance.guaranteeMoneyAmountPaidSettle}+${bizGuaranteeMoneyBalance.guaranteMoneyAmountPaidOffline}-${bizGuaranteeMoneyBalance.guaranteeMoneyAmountPaidUsed}</span>
				</p>
				<div class="guar-con">
					<span class="guar-left">质保金余额&nbsp;=&nbsp;</span>
					<p class="guar-rgt">结算上缴合计 + 线下上缴合计 - 使用合计</p>
				</div>
			</div>
			<nav class="guar-nav bg_f clearfix">
				<a class="guar-a" href="javascript:;"> <span
					class="guar-span font28 col_6e6e6e active">结算上缴质保金</span> <span
					class="tabbar"></span>
				</a> <a class="guar-a" href="javascript:;"> <span
					class="guar-span font28 col_6e6e6e">线下上缴质保金</span> <span
					class="tabbar"></span>
				</a> <a class="guar-a" href="javascript:;"> <span
					class="guar-span font28 col_6e6e6e">使用质保金</span>
				</a>
			</nav>
			<!-- 工人结算上缴纸质保金信息 -->
			<ul class="rows">
			     <c:forEach items="${list}" var="money">
			        <li class="row bg_f border_btm pad"><img class="left-png"
					src="${ctxStatic}/mobile/modules/Manager/images/jiao.png" alt="">
					<p class="font30 col_3 pad_btm26">${money.customerMessage}-${money.customerName}</p>
					<p class="font28 col_6"><fmt:formatDate value="${money.deductTime}" pattern="yyyy-MM-dd"/></p> <span
					class="money font48 col_ff9800">+${money.guaranteeMoneyAmount}</span></li>
			     </c:forEach>
				<div style="padding-top: 300px;"></div> 
			</ul>
			<!-- 工人线下上缴 -->
			<ul class="rows bg_f undis">
			    <c:forEach items="${PaidOffineList}" var="money">
			      <a class="row bg_f border_btm pad rgtArrow" href="${ctx}/app/worker/queryWorkerPaidMoneyDetail?id=${money.id}">
					<img class="left-png" src="${ctxStatic}/mobile/modules/Manager/images/jiao.png" alt="">
					<p class="font30 col_3 pad_btm26">${money.communityName}-${money.buildNumber}-${money.buildUnit}-${money.buildRoom}-${money.customerName}</p>
					<p class="font28 col_6"><fmt:formatDate value="${money.guaranteeMoneyDateTime}" pattern="yyyy-MM-dd"/></p> <span
					class="reduce-money font48 col_ff9800">+${money.guaranteeMoneyAmount}</span>
				</a>
			    </c:forEach>
				<div style="padding-top: 300px;"></div>
			</ul>
			<!-- 工人使用 -->
			<ul class="rows bg_f undis">
			<c:forEach items="${usedList}" var="money">
			     <a class="row bg_f border_btm pad rgtArrow" href="${ctx}/app/worker/queryWorkerUsedMoneyDetail?id=${money.id}">
					<img class="left-png" src="${ctxStatic}/mobile/modules/Manager/images/yong.png" alt="">
					<p class="font30 col_3 pad_btm26">${money.communityName}-${money.buildNumber}-${money.buildUnit}-${money.buildRoom}-${money.customerName}</p>
					<p class="font28 col_6"><fmt:formatDate value="${money.guaranteeMoneyDateTime}" pattern="yyyy-MM-dd"/></p> <span
					class="reduce-money font48 col_ff9800">-${money.guaranteeMoneyAmount}</span>
				</a>
			</c:forEach>
				<div style="padding-top: 300px;"></div>
			</ul>
		</section>
	</div>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script>
		$(function() {
			$('.guar-a').each(function(index, val) {
				$(val).click(function() {
					$('.guar-a').find('.guar-span').removeClass('active');
					$(this).find('.guar-span').addClass('active');
					$('.rows').addClass('undis');
					$('.rows').eq(index).removeClass('undis');
				});
			});
		});
	</script>
</body>
</html>