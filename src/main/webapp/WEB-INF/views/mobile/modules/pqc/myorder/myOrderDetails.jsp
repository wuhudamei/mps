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
	<title>订单详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/order_details.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx }/app/pqc/myOrder/list.php"></a>
			<h2 class="title">订单详情</h2>
		</header><!-- /header -->
		<section class="total font0">
			<p class="item-tit"><span class="text-tit">客户信息</span></p>
			<ul class="item pad_left3 pad_rgt3 bg_f">
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">订单编号：</span>
					<p class="font28 col_3">${order.orderNumber }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">客户姓名：</span>
					<p class="font28 col_3">${order.customerName}</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">客户手机：</span>
					<p class="font28 col_3">${order.customerPhone }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">订单状态：</span>
					<p class="font28 col_3">${order.orderStatus }</p>
				</li>
			</ul>
			<p class="item-tit"><span class="text-tit">房屋信息</span></p>
			<ul class="item pad_left3 pad_rgt3 bg_f">
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">施工地址：</span>
					<p class="font28 col_3">${order.customerAddress }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">小区名称：</span>
					<p class="font28 col_3">${order.communityName }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">门 牌 号：</span>
					<p class="font28 col_3">${order.buildNumber }号楼-${order.buildUnit}单元-${order.buildRoom}室</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">房屋户型：</span>
					<p class="font28 col_3">${order.houseType }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">合同面积：</span>
					<p class="font28 col_3">${order.contractArea }平米</p>
				</li>
			</ul>
			<p class="item-tit"><span class="text-tit">施工信息</span></p>
			<ul class="item pad_left3 pad_rgt3 bg_f">
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">合同开工时间：</span>
					<p class="font28 col_3">
					<fmt:formatDate value="${order.contractStartDate}" pattern="yyyy-MM-dd" /></p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">合同竣工时间：</span>
					<p class="font28 col_3">
					<fmt:formatDate value="${order.contractEndDate}" pattern="yyyy-MM-dd" />
					</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">合 同 工 期 ：</span>
					<p class="font28 col_3">${order.contractDays }天</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">实际竣工时间：</span>
					<p class="font28 col_3"><fmt:formatDate value="${order.actualEndDate}" pattern="yyyy-MM-dd" /></p>
				</li>
			</ul>
			<p class="item-tit"><span class="text-tit">人员信息</span></p>
			<ul class="item pad_left3 pad_rgt3 bg_f mar_btm300">
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">设　　计　　师：</span>
					<p class="font28 col_3">${order.designName }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">设 计 师 电 话：</span>
					<p class="font28 col_3">${order.designPhone }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">客　　　　　服：</span>
					<p class="font28 col_3">${order.serviceName }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">客　服　电　话：</span>
					<p class="font28 col_3">${order.servicePhone }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">跟　　单　　员：</span>
					<p class="font28 col_3">${order.reportName }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">跟 单 员 电 话：</span>
					<p class="font28 col_3">${order.reportPhone }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">质　　检　　员：</span>
					<p class="font28 col_3">${order.inspectorName }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">质 检 员 电 话：</span>
					<p class="font28 col_3">${order.inspectorPhone }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">项　目　经　理：</span>
					<p class="font28 col_3">${order.managerName }</p>
				</li>
				<li class="mar_btm24 clearfix">
					<span class="font28 col_grey fl_left">项目经理电话　：</span>
					<p class="font28 col_3">${order.managerPhone }</p>
				</li>
			</ul>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
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