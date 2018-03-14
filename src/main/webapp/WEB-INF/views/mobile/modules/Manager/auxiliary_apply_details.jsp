<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>辅料申请详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/auxiliary_choose.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170918/style.css"/>
</head>
<script type="text/javascript">


 

	 //图片没有 就使用默认图片
	 function nofind(){

		 var img=event.srcElement;
		
		 img.src="/static/mobile/modules/Manager/css/photo/auxiliaryPhotoForNothing.png";

		 img.onerror=null; 

		 } 
	 
	
	


</script>
<body>
	<div class="g-auxiliary_choose g-auxiliary_count">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/auxiliaryApplyRecord/record?orderId=${purchaseVo.orderId}"></a>
			<h2 class="title">辅料申请详情</h2>
		</header><!-- /header -->
		<div class="show_sec switch_sec">
			<div class="switch shadow clearfix">
				<h3>${customerInfo }</h3>
				
				
				<ul class="wall_list wall clearfix">
					<li class="clearfix">
						<span>采购单编号 ：</span>
						<p>${purchaseVo.purchaseCode }</p>
					</li>
					<li class="clearfix">
						<span>采购单状态 ：</span>
						<p>${purchaseVo.status }</p>
					</li>
					<li class="clearfix">
						<span>期望进场日期 ：</span>
						<p><fmt:formatDate value="${purchaseVo.hopeForTime }" pattern="yyyy-MM-dd"/> </p>
					</li>
				</ul>
			</div>
			<ul class="buy">
			<c:forEach items="${purchaseDetail }" var="purchase">
				<li class="shadow _height-auto">
					<div class="img_container">
						<img src="${purchase.url }" onerror="nofind()">
					</div>
					<p class="brand brand-name _ellipsis3"><span>【</span>${purchase.empWorkTypeName }-${purchase.brand }<span>】</span>${purchase.name }</p>
					<p class="spec">规格：${purchase.specification } </p>
					<p class="spec">单位：${purchase.unit } </p>
					<p class="multi">
						<span>${purchase.price }</span>    *    <span>${purchase.count }</span>   =   <span>¥ <fmt:formatNumber type="number" value="${purchase.auxiliaryMoney }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></span> 
					</p>
				</li>
			</c:forEach>
			</ul>
			
		</div>
		<footer class="sub_footer auxi_footer">
			<p class="col_red">合计：<span>¥  <fmt:formatNumber type="number" value="${purchaseVo.auxiliaryAllMoney }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber> </span></p>
			<p class="goods">共使用${purchaseVo.totalCount }种商品</p>
		</footer>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/swiper-3.3.1.jquery.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	
	
</html>