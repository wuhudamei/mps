<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<title>质检员验收详情</title>
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
			<li class="active"><a href="javascript:vodi(0)">质检员验收详情</a></li>
		</ul>
		<header>
			<!-- <h2 class="title"></h2> -->
			<a class="back" href="javascript:vodi(0)" onclick="history.go(-1)">返回</a>
		</header>
		<section class="swiper-container">
		<div class="breadcrumb ">
			<form:form class="breadcrumb form-search">
			<table style="width:100%" align="center" valign="center">
				<tr>
					<td style="text-align:center" >
						<font><h3>${qcBill.communityName }-${qcBill.buildNumber }-${qcBill.buildUnit }-${qcBill.buildRoom }-${qcBill.customerName }</h3></font>
					</td>
				</tr>
				<tr>
					<td style="text-align:center">
						<label>约检内容：</label>
						${qcBill.qcCheckNodeName }
					</td>
				</tr>
				<tr>
					<td style="text-align:center">
						<label>质检验收日期：</label>
						<fmt:formatDate value="${qcBill.acceptCheckDatetime }" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td style="text-align:center"> 
						<label>验收人：</label>
						${qcBill.checkPeople }
					</td>
				</tr>
			</table>
			</form:form>
		</div>
			<div class="swiper-wrapper">
			<c:forEach items="${picList }" var="picture">
				<div class="swiper-slide">
					<img src="${baseUrl }${picture.picUrl}" alt="图片加载中...">
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
</html>