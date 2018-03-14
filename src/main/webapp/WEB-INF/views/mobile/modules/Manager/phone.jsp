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
	<title>确认验收</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/swiper-3.3.1.min.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/photo.css"/>
	
	<script type="text/javascript" charset="utf-8">
      	//图片没有 就使用默认图片
   	    function nofind(){

   		 var img=event.srcElement;
   		
   		 img.src="/mdn/static/mobile/modules/Manager/css/photo/auxiliaryPhotoForNothing.png";

   		 img.onerror=null;

   	   } 
    </script>
</head>
<body>
	<div class="g-photo">
		<header>
			<a class="back_btn" onclick="history.go(-1)"></a>
			<h2 class="title"></h2>
		</header><!-- /header -->
		<section class="swiper-container">
			<div class="swiper-wrapper">
				<c:forEach items="${orderTaskpackagePicList}" var="pic">
					<div class="swiper-slide">
						<img src="${root}${pic.picturePath}" height="1235" width="750" alt="" onerror="nofind()">
					</div>
				</c:forEach>
			</div>
			<div class="swiper-button-prev" id="prev_btn"></div>
    		<div class="swiper-button-next" id="next_btn"></div>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/swiper-3.3.1.jquery.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/swiper.js"></script>
</body>
</html>