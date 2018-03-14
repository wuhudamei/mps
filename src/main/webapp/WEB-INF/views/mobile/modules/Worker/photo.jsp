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
	<title>查看照片</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/lib/swiper-3.3.1.min.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/photo.css"/>
</head>
<body>
	<div class="g-photo">
		<header>
			<a class="back_btn" href="${ctx }/app/worker/account?id=${id }&settleStyle=${settleStyle}"></a>
			<h2 class="title"></h2>
		</header><!-- /header -->
		<section class="swiper-container">
			<div class="swiper-wrapper">
				<c:forEach items = "${pictures }" var = "picture">
					<div class="swiper-slide">
						<img src="${baseUrl }${picture.picturePath }" height="1235" width="750" alt="">
					</div>
				</c:forEach>
			<!-- 	<div class="swiper-slide">
					<img src="../images/photo.png" height="1235" width="750" alt="">
				</div>
				<div class="swiper-slide">
					<img src="../images/photo.png" height="1235" width="750" alt="">
				</div>
				<div class="swiper-slide">
					<img src="../images/photo.png" height="1235" width="750" alt="">
				</div>
				<div class="swiper-slide">
					<img src="../images/photo.png" height="1235" width="750" alt="">
				</div>
				<div class="swiper-slide">
					<img src="../images/photo.png" height="1235" width="750" alt="">
				</div>
				<div class="swiper-slide">
					<img src="../images/photo.png" height="1235" width="750" alt="">
				</div> -->
			</div>
			<div class="swiper-button-prev" id="prev_btn"></div>
    		<div class="swiper-button-next" id="next_btn"></div>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/swiper-3.3.1.jquery.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/home/swiper.js"></script>
</body>
</html>