<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/mobile/modules/home/footer.jsp" %>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>订单详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/footer.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/order_details.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/home/myOrder"></a>
			<h2 class="title">订单详情</h2>
		</header><!-- /header -->
		<section class="total font0">
			<p class="item-tit"><span class="text-tit">基本信息</span></p>
			<ul class="item pad_left3 pad_rgt3 bg_f">
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">订单编号：</span>
					<p class="font28 col_3">${order.orderNumber}</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">小区名称：</span>
					<p class="font28 col_3">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">户　　型：</span>
					<p class="font28 col_3">${fns:getDictLabel(order.houseType, 'home_type', '')}</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">套　　餐：</span>
					<p class="font28 col_3">${order.saleType } 套餐</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">建筑面积：</span>
					<p class="font28 col_3">${order.coveredArea} 平米</p>
				</li>
			</ul>
			<p class="item-tit"><span class="text-tit">人员信息</span></p>
			<ul class="item pad_left3 pad_rgt3 bg_f">
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">客服经理：</span>
					<p class="font28 col_3">${order.serviceName}</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">设 计 师：</span>
					<p class="font28 col_3">${order.designerName}</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">项目经理：</span>
					<p class="font28 col_3">${order.itemManager}</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">质 检 员：</span>
					<p class="font28 col_3">${order.orderInspector}</p>
				</li>
			</ul>
			<p class="item-tit"><span class="text-tit">工期信息</span></p>
			<ul class="item pad_left3 pad_rgt3 bg_f mar_btm300">
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">合同开工时间：</span>
					<p class="font28 col_3"><fmt:formatDate value="${order.contractStartDate}" pattern="yyyy-MM-dd"/></p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">合同竣工时间：</span>
					<p class="font28 col_3"><fmt:formatDate value="${order.contractEndDate}" pattern="yyyy-MM-dd"/></p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">合  同  工  期 ：</span>
					<p class="font28 col_3">${order.contractTime } 天</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">签 约 时 间 ：</span>
					<p class="font28 col_3"><fmt:formatDate value="${order.signContractDate}" pattern="yyyy-MM-dd"/></p>
				</li>
			</ul>
		</section>
		<!-- <footer>
			<a class="home_btn" href="javascript:void(0)">首页</a>
			<a class="mine_btn active" href="javascript:void(0)">我的</a>
		</footer> -->
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
	<!-- <script type="text/javascript">
		$(function(){
			$(document).on('click', '.nav_bar', function(){
				$('.nav_box').css({
					'z-index': 10
				});
				$('.options').removeClass('undis');
				$('.mask').removeClass('undis');
			});
			$(document).on('click', '.options a', function(){
				$('.nav_box').removeAttr("style");
				var address = $(this).text();
				console.log(address);
				$('.options').addClass('undis');
				$('.mask').addClass('undis');
				$('.nav_bar span').text(address);
			});
		});
	</script> -->
</body>
</html>