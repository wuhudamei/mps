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
	<title>施工变更</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/change_details.css"/>
</head>
<body>
	<div class="g-review">
		<header>
			<a class="back_btn" onclick="history.go(-1)"></a>
			<h2 class="title">施工变更</h2>
		</header>
		<section class="pad_top10 mar_btm50 font0">
			<div class="mar_left3 mar_rgt3 mar_top1">
				<span class="font28 co_3">变更原因：</span>
				<textarea class="reason_area" name="" readonly>${bill.changeReason }</textarea>
			</div>
			<section class="after">
				<c:forEach items="${itemList }" var="item">
					<ul class="pad_left3 pad_rgt3 pad_top3 pad_btm3 mar_top3 bg_f shadow">
						<li class="mar_btm5 clearfix">
							<span class="font28 col_6">施工项　：</span>
							<input class="font24 col_6 wid_476 grey_border pad_top1 pad_btm1 mar_rgt2 bg_grey" value="${item.itemName }" readonly="readonly" type="text" />
						</li>
						<li class="mar_btm5 clearfix">
							<span class="font28 col_6">套餐类型：</span>
							
							<c:if test="${item.groupType == '1' }">	<span class="font24 col_3">套餐内</span></c:if>
							<c:if test="${item.groupType == '2' }">	<span class="font24 col_3">套餐外</span></c:if>
					
						</li>
						<li class="mar_btm5 clearfix">
							<span class="font28 col_6">单　　位：</span>
							<span class="font24 col_3">${item.itemUnit }</span>
						</li>
						<li class="mar_btm5 clearfix">
							<span class="font28 col_6">单　　价：</span>
							<span class="font24 col_3">${item.itemPrice }</span>
						</li>
						<li class="mar_btm5 clearfix">
							<span class="font28 col_6">数　　量：</span>
							<input class="font24 col_6 wid_200 grey_border pad_top1 pad_btm1 mar_rgt2  bg_grey" type="text" value="${item.itemCount }" readonly="readonly">
						</li>
						<li class="mar_btm5 clearfix">
							<span class="font28 col_6">金　　额：</span>
							<input class="font24 col_6 wid_200 grey_border pad_top1 pad_btm1 mar_rgt2 bg_grey" type="text" readonly value="${item.itemPrice*item.itemCount }">
						</li>
						<li class="clearfix">
							<span class="font28 col_6">说　　明：</span>
							<input class="font24 col_6 wid_476 grey_border pad_top1 pad_btm1 mar_rgt2  bg_grey" type="text" value="${item.itemDescription }" readonly="readonly">
						</li>
						<c:if test="${item.itemWay == '1' }">
							<div class="blue">增</div>
						</c:if>
						<c:if test="${item.itemWay == '2' }">
							<div class="blue">减</div>
						</c:if>
					</ul>
				</c:forEach>
			</section>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
</html>