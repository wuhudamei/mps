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
	<title>收款账目</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/account_coll.css"/>
</head>
<body>
	<div class="g-sign_list">
		<header class="header">
			<a class="back_btn" href="javascript:void(0)" onclick="history.go(-1)"></a>
			<h2 class="title">收款账目</h2>
		</header><!-- /header -->
		<nav>
			<a class="nav" href="javascript:void(0)"><span class="active">已付款</span></a>
			<a class="nav" href="javascript:void(0)"><span>未付款</span></a>
		</nav>
		<section class="container mar_btm5">
			<div class="total clearfix">
				<p class="font30 col_red">${payedMoney }</p>
				<p class="font24 col_6">付款金额</p>
			</div>
			<c:forEach items ="${payed }" var ="paymentDetail">
				<c:if test="${paymentDetail.paymentType =='0' }">
					<div class="details bg_blue clearfix">
						<p class="font24 col_9 pad_top20"><em class="font30 col_red">${paymentDetail.amount }</em>元</p>
						<p class="font24 col_9 mar_top20 mar_btm20"><em>付款日期：</em>
							<fmt:formatDate value="${paymentDetail.payDateTime }" pattern="yyyy-MM-dd" />
						</p>
						<p class="font26 col_6">${paymentDetail.customerMessage }-${paymentDetail.customerName }</p>
					</div>
				</c:if>
				<c:if test="${paymentDetail.paymentType =='1' }">
					<div class="details bg_orange clearfix">
						<p class="font24 col_9 pad_top20"><em class="font30 col_red">${paymentDetail.amount }</em>元</p>
						<p class="font24 col_9 mar_top20 mar_btm20"><em>付款日期：</em>
							<fmt:formatDate value="${paymentDetail.payDateTime }" pattern="yyyy-MM-dd" />
						</p>
						<p class="font26 col_6">${paymentDetail.customerMessage }-${paymentDetail.customerName }</p>
					</div>
				</c:if>
			</c:forEach>
		</section>
		<section class="container mar_btm5 undis">
			<div class="total clearfix">
				<p class="font30 col_red">${payingMoney }</p>
				<p class="font24 col_6">未付款金额</p>
			</div>
			<c:forEach items = "${paying }" var = "paymentDetail">
				<c:if test="${paymentDetail.paymentType == '0' }">
					<div class="details bg_blue clearfix">
						<p class="font24 col_9 pad_top20"><em class="font30 col_red">${paymentDetail.amount }</em>元</p>
						<p class="font24 col_9 mar_top20 mar_btm20"><em>结算日期：</em>
							<fmt:formatDate value="${paymentDetail.statusDate }" pattern="yyyy-MM-dd" />
						</p>
						<p class="font26 col_6">${paymentDetail.customerMessage }-${paymentDetail.customerName }</p>
					</div>
				</c:if>
				<c:if test="${paymentDetail.paymentType =='1' }">
					<div class="details bg_orange clearfix">
						<p class="font24 col_9 pad_top20"><em class="font30 col_red">${paymentDetail.amount }</em>元</p>
						<p class="font24 col_9 mar_top20 mar_btm20"><em>结算日期：</em>
							<fmt:formatDate value="${paymentDetail.statusDate }" pattern="yyyy-MM-dd" />
						</p>
						<p class="font26 col_6">${paymentDetail.customerMessage }-${paymentDetail.customerName }</p>
					</div>
				</c:if>
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
	<script>
		$(function(){
			var index0 = 0;
			$(document).on('click', '.nav', function(){
				index0 = $(this).index();
				$('.nav').find('span').removeClass('active');
				$(this).find('span').addClass('active');
				$('.container').addClass('undis');
				$('.container').eq(index0).removeClass('undis');
			});
		}());
	</script>
</body>
</html>