<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%-- <html>
<head>
	<meta name="decorator" content="default"/>
	<title>安装项照片</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<style>
		.mask {width: 100%;height: 100%;overflow: auto;background: rgba(0,0,0,.2);position: fixed;top: 0;left: 0;}
		.mask1 {z-index: 10;}
		.mask2 {z-index: 11;background: rgba(0,0,0,.7);}
		.pic_container {margin-top: 50px;background: #eee;padding: 20px 5px;}
		.pic_wraper {width: 32%;float: left;padding: 0;position: relative;overflow: hidden;margin-right: 2%;margin-bottom: 20px; font-size: 0;}
		.pic_wraper img {width: 100%;height: 150px;}
		.pic_wraper:nth-of-type(3n) {margin-right: 0;}
		.big_pic_wraper {width: 80%;height: 80%;margin: 10% auto;}
		.big_pic_wraper img {width: 100%;}
		.back{font-size: 14px;padding: 2px 5px;color: #555;border: 1px solid #aaa;border-radius: 2px;position: absolute;left: 10px;top: 10px;}
	</style>
</head> --%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<title>设计交底照片</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/swiper-3.3.1.min.css">
	<style>
		.back{font-size: 14px;margin-left:10px;padding: 5px;color: #666;border: 1px solid #333;border-radius: 2px;}
		.swiper-container{margin-top: 10px;}
		.swiper-slide{text-align: center;background: #EEE;}
		.swiper-slide img{padding-top:20px;}
	</style>
</head>
<body>
	<div class="g-photo">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/bizorderdisclose/bizOrderDisclose/list">设计交底图片</a></li>
		</ul>
		<header>
			<a href="javascript:void(0)" class="back" onclick="history.go(-1)">返回</a>
		</header>
		<section class="swiper-container">
		<div class="swiper-wrapper">
			<c:forEach items="${orderDisclosePic }" var="p">
				<div class="swiper-slide">
					<img src="${picPrefixName }${p.picUrl}" alt="设计交底照片">
				</div>
			</c:forEach>
			</div>
			<div class="swiper-button-prev" id="prev_btn"></div>
    		<div class="swiper-button-next" id="next_btn"></div>
    		<div class="swiper-pagination"></div>
    	</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/swiper-3.3.1.jquery.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/swiper.js"></script>
	<script>
		;(function(){
			$(document).ready(function(){
				var photoAll = $('.g-photo .swiper-slide').length;
			   	$('.g-photo h2.title').text(1 + '/' + photoAll);
				var mySwiper = new Swiper ('.swiper-container',{
			    	loop: false,
			    	nextButton: '.swiper-button-next',
		    		prevButton: '.swiper-button-prev',
		    		pagination : '.swiper-pagination',
					paginationType : 'fraction',
			    })
			})
		}());
	</script>
</body>
</body>
</html>