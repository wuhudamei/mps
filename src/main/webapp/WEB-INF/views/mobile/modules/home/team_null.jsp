<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/mobile/modules/home/footer.jsp" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>施工团队</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/footer.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/order_details.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/order_none.css"/>
</head>
<body>
	<div class="order-none">
		<header>
			<a class="back_btn" href="${ctx}/app/home/isLogin"></a>
			<h2 class="title">施工团队</h2>
		</header><!-- /header -->
		<section class="total font0">
			<div class="img-wrapper">
				<p class="tips col_grey">这里什么都没有耶！</p>
			</div>
		</section>
		<!-- <footer>
			<a class="home_btn" href="${ctx}/app/home/index">首页</a>
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