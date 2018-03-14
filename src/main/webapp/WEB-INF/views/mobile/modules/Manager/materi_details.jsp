<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>收货</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/materi.css"/>
</head>
<body>
	<div class="g-materi">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/receivedBillList"></a>
			<h2 class="title">收货详情</h2>
		</header><!-- /header -->
		<div class="zone shadow">
			<div class="hgt64">
				<span class="flo_left mar_left3 font30 col3 hgt64">收货单编号　：</span>
				<p class="flo_left zone_rgt font30 col3">${receiveBill.purchaseReceiveCode }</p>
			</div>
			<div class="hgt64">
				<span class="flo_left mar_left3 font30 col3 hgt64">实际收货日期：</span>
				<p class="flo_left zone_rgt font30 col3">
					<fmt:formatDate value="${receiveBill.receiveDate }" pattern="yyyy-MM-dd" />
				</p>
			</div>
			<a class="goods_btn" href="javascript:void(0)">查看收货单</a>
		</div>
		<ul class="show_sec pad_top24">
			<c:forEach items = "${list }" var ="material">
				<li class="item shadow">
					<div class="img_container">
						<img src="${baseUrl }${material.picUrl}" alt="goods" onerror="nofind()"/>
					</div>
					<c:if test="${ material.brands != null}">
						<p class="brand"><em>【${material.brands}】</em>${material.name }</p>
					</c:if>
					<c:if test="${ material.brands == null}">
						<p class="brand">${material.name }</p>
					</c:if>
					<p class="format">规格：${material.specifications }</p>
					<p class="price">申请数量<em>${material.count }</em>${material.unit }</p>
					<p class="get">收货数量：<em>${material.receivedNumber }</em>${material.unit}</p>
				</li>
			</c:forEach>
		</ul>
		<!-- 照片弹出层 -->
		<div class="mask mask1 undis">
			<div class="pic_container clearfix">
				<c:forEach items ="${pictures }" var ="picture">
					<div class="pic_wraper clearfix">
						<img src="${baseUrl }${picture.picUrl}" alt="收货单">
						<!-- <a class="del_btn" href="javascript:void(0)">删除</a> -->
					</div>
				</c:forEach>
			</div>
			<a class="back" href="javascript:void(0)">返回</a>
		</div>
		<!-- 查看大图弹出层 -->
		<div class="mask mask2 undis">
			<div class="big_pic_wraper">
				<img class="big_pic" id="big_pic" alt="收货单">
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/shopcount.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script>
		$(function(){
			$(document).on('click','.goods_btn',function(){
				$('.mask1').removeClass('undis');
			});
			$(document).on('click','.pic_wraper img',function(){
				var purl = $(this).attr("src");
				$("#big_pic").attr("src",purl);
				$('.mask2').removeClass('undis');
			});
			$(document).on('click','.mask2',function(){
				$('.mask2').addClass('undis');
			});
			$(document).on('click','.back',function(){
				$('.mask1').addClass('undis');
			});
		}());
		
		function nofind(){

			 var img = event.srcElement;
			
			 img.src="/static/mobile/modules/Manager/css/photo/auxiliaryPhotoForNothing.png";

			 img.onerror=null; 

		}
	</script>
</html>