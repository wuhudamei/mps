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
	<title>安装申请计划</title>
	<link rel="stylesheet" type="text/css" href="/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="/mobile/modules/Manager/css/build_apply.css"/>
</head>
<body>
	<div class="g-build_apply">
		<header class="build_apply_header">
			<a class="back_btn" onclick="myhistory.back()" href="javascript:void(0)"></a>
			<h2 class="build_apply_title">安装申请计划</h2>
		</header><!-- /header -->
		<p class="every">鹿港嘉苑-10-3-2001-张三</p>
		<p class="work_date">实际开工日期：2016-07-30</p>
		<section class="build_apply_list">
			<ul>
				<li class="clearfix">
					<span>安装项名称</span>
	          		<span>计划申请日期</span>
					<span>操作</span>
				</li>
				<li class="clearfix">
					<span>厨、卫吊顶</span>
	          		<span>2016-08-01</span>
	          		<span class="over">已申请</span>
				</li>
				<li class="clearfix">
					<span>木门</span>
	          		<span>2016-08-01</span>
	          		<span class="over">已申请</span>
				</li>
				<li class="clearfix">
					<span>橱柜、台面</span>
	          		<span>2016-08-03</span>
	          		<span class="over">已申请</span>
				</li>
				<li class="clearfix">
					<span>洁具、面板、灯具</span>
	          		<span>2016-08-04</span>
	          		<span class="apply">申请安装</span>
				</li>
				<li class="clearfix">
					<span>壁纸</span>
	          		<span>2016-08-05</span>
	          		<span class="apply">申请安装</span>
				</li>
				<li class="clearfix">
					<span>木地板</span>
	          		<span>2016-08-06</span>
	          		<span class="apply">申请安装</span>
				</li>
				<li class="clearfix">
					<span>家具</span>
	         	 	<span>2016-08-07</span>
	          		<span class="apply">申请安装</span>
				</li>
				<li class="clearfix">
					<span>沙发</span>
	          		<span>2016-08-08</span>
	          		<span class="apply">申请安装</span>
				</li>
				<li class="clearfix">
					<span>电器</span>
			        <span>2016-08-09</span>
	          		<span class="apply">申请安装</span>
				</li>
				<li class="clearfix">
					<span>窗帘</span>
	          		<span>2016-08-11</span>
	          		<span class="apply">申请安装</span>
				</li>
			</ul>
		</section>
	</div>
	<script type="text/javascript" src="/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="/mobile/modules/Manager/js/utils/history.js"></script>
	<script>
		$('span.over').click(function(){
			console.log(1111);
			window.location.href = '';
		});
		$('span.apply').click(function(){
			window.location.href = '';
		});
	</script>
</body>
</html>