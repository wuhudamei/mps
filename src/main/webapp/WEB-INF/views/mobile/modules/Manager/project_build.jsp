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
	<title>工程安装</title>
	<link rel="stylesheet" type="text/css" href="/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="/mobile/modules/Manager/css/project_build.css"/>
</head>
<body>
	<div class="g-project_build">
		<header class="project_build_header">
			<a class="back_btn"  onclick="myhistory.back()" href="javascript:void(0)"></a>
			<h2 class="project_build_title">工程安装</h2>
		</header><!-- /header -->
		<section class="project_build_list">
			<ul>
				<li class="clearfix">
					<span>顾客信息：</span>
					<p>鹿港嘉苑-10-3-2001-张三</p>
				</li>
				<li class="clearfix">
					<span>合同开工：</span>
					<p>2016-07-30</p>
				</li>
				<li class="clearfix">
					<span>合同竣工：</span>
					<p>2016-11-30</p>
				</li>
				<li class="clearfix">
					<span>合同工期：</span>
					<p>60天</p>
				</li>
				<li class="clearfix">
					<span>订单状态：</span>
					<p class="col_blue">施工中</p>
				</li>
				<div class="btns clearfix">
					<a href="#" class="btn">安装申请</a>
					<a href="#" class="btn">验收安装</a>
				</div>
			</ul>
		</section>
	</div>
	<script type="text/javascript" src="/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>