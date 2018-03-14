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
	<title>查看评价</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/comment.css"/>
	<style>
		.work-sec:last-child{margin-bottom: 300px;}
	</style>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/home/evaluate/evalWorker/list"></a>
			<h2 class="title">查看评价</h2>
		</header><!-- /header -->
		<section class="pad_top88">
			<p class="font0 locate">
				<span class="font30 col_3 location-text">${packageName}</span>
			</p>
			<div class="work-sec font0 bg_f pad_btm24 mar_btm24 border_btm">
				<p class="person font32 col_3">工人组长：${groupRealname }</p>
				<ul class="pad_top68 pad_left3">
					<c:forEach items="${list }" var="item">
						<li class="mar_btm68 clearfix a">
							<span class="col_3 font28 flo_left mar_ght52 top-small">${item.indexName }：</span>
							<p class="flow star-p pad_rgt6">
							<c:if test="${item.count>0}">
								<c:forEach begin="0" end="${item.count-1}" step="1">
									<span class="star-y"></span>
								</c:forEach>
								<c:forEach begin="1" end="${5-item.count }" step="1">
									<span class="star-grey"></span>
								</c:forEach>
							</c:if>
							<c:if test="${item.count==0 }">
								<c:forEach begin="1" end="${5-item.count }" step="1">
									<span class="star-grey"></span>
								</c:forEach>
							</c:if>
								<!-- <span class="star-y"></span>
								<span class="star-y"></span>
								<span class="star-y"></span>
								<span class="star-grey"></span>
								<span class="star-grey"></span> -->
							</p>
						</li>
					</c:forEach>
				</ul>
				<div class="advise-app font0 col_9 clearfix">
					<span class="font28 col_ff001e flo_left">反馈意见：</span>
					<p class="flow font28 col_3 advise-det">${evalFeedback}</p>
				</div>
			</div>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/history.js"></script>
	<!-- <script>
		$(function(){
			$(document).on('click','.star-p span',function(){
				$(this).prevAll().addClass('star-y');
				if($(this).hasClass('star-y')){
					$(this).nextAll().removeClass('star-y').addClass('star-grey');
				}else{
					$(this).addClass('star-y');
				}
			});
		}());
	</script> -->
</body>
</html>