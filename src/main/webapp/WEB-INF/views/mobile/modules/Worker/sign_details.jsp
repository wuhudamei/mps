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
	<title>签到详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/sign_details.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
</head>
<body>
	<div class="g-sign_details">
		<header>
			<a class="back_btn" href="#" onclick="history.go(-1)"></a>
			<h2 class="title">签到详情</h2>
		</header><!-- /header -->
		<section class="sign_details_list">
			<div class="list">
		
				<ul class="clearfix">
					<li class="clearfix">
						<span>顾客信息：</span>
						<p>${signDetail.customerInfo}</p>
					</li>
					<li class="clearfix">
						<span>签到日期：</span>
						<p><fmt:formatDate value="${signDetail.signDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </p>
					</li>
					<li class="clearfix">
						<span>签到地址：</span>
						<p>${signDetail.signAddress}</p>
					</li>
					<li class="clearfix">
						<span>误差：</span>
						<p>${signDetail.signDistance} &nbsp; 米</p>
					</li>
					<!-- <a class="photo_btn" href="#">查看签到照片</a> -->
				</ul>
			
			</div>
		</section>
	</div>
</body>
</html>