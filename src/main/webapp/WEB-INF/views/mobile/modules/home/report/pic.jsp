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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/swiper.min.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/picWatch.css"/>
</head>
<body>
	<div class="g-photo">
		<header>
			<a class="back_btn" href="javascript:void(0)" onclick="history.go(-1)"></a>
			<h2 class="title">查看照片</h2>
		</header><!-- /header -->
		<section class="swiper-container construct-state-big">
			
			<div class="swiper-wrapper">
				<c:forEach items="${picList }" var="p">
					<div class="swiper-slide">
						<img width="100%" src="${baseUrl}${p.picUrl }" />
					</div>
				</c:forEach>
			</div>
			<div class="swiper-button-prev"></div>
    		<div class="swiper-button-next"></div>
		</section>
		<div class="now-page">
				<div>
					<span class="current-page">1</span>
					<span class="pre">/</span>
					<span class="all-page">9</span>
				</div>
			</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/swiper.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
	<script>
		;(function(){
			$(document).ready(function(){
				var photoAll = $('.g-photo .swiper-slide').length;
				$('.all-page').text(photoAll);
				var mySwiper = new Swiper ('.swiper-container',{
			    	loop: false,
			    	nextButton: '.swiper-button-next',
		    		prevButton: '.swiper-button-prev',
		    		pagination : '.swiper-pagination',
					paginationType : 'fraction',
					onSlideChangeEnd: function(swiper1){
			      // console.log(swiper1.activeIndex) //切换结束时，告诉我现在是第几个slide
			      	var currentPage = swiper1.activeIndex + 1;
			    	$('.current-page').html(currentPage);
			    }
			    })
			    bigSwiper.slideTo(swiper.clickedIndex, 0, false);
			})
		}());
	</script>
</body>
</html>