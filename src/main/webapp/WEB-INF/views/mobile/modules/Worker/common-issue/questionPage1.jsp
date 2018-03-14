<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1,minimum-scale=1.0,user-scalable=no">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Worker/css/common-issue/base.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Worker/css/common-issue/common.css">
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<title>问题详情</title>
</head>

<body>
	<div class="qustion-warp font0">
		<!-- <header class="qustion-header col-fff font-36 height88 bgheader tc">
			<a class="icon_back" href="javascript:history.back(-1)"></a> 常见问题
		</header> -->
		<header>
			<a class="back_btn" href="javascript:history.back(-1)"></a>
			<h2 class="title">常见问题</h2>
		</header><!-- /header -->
		<div class="qustionPage font-30 col-444 pad-top110">
			<div class="desc">
				<div class="bor-b1">
					<div class="pl30 pr30 pt30 bg-fff">
						<h3 class="font-24 col-999 ">问题描述</h3>
						<p class="col-444 font-30 lh46 pt8 mb14">“我要签到”时，地址无法显示或点击“确定”没有反应怎么办？</p>
						<h3 class="font-24 col-999 mb14">图例：</h3>
						<div class="pb28 pl30 pr30">
							<img src="${ctxStatic}/mobile/modules/Worker/images/common-issue/1.png" alt="">
						</div>
					</div>
				</div>
				<div class="bg-fff pt30 mt30 pl30 pr30 pb30">
					<h3 class="font-24 col-999 mb14">原因及解决办法</h3>
					<p class="col-444 font-30 lh50">
						<p class="fl">1、</p>
						<p class="pl34 lh50">查看手机定位功能是否打开</p>
					</p>
					<p class="col-444 font-30 lh50">
						<p class="fl">2、</p>
						<p class="pl34 lh50">将网络断开重新连接</p>
					</p>
					<p class="col-444 font-30 lh50">
						<p class="fl">3、</p>
						<p class="pl34 lh50">连接网速好的网络</p>
					</p>
				</div>

			</div>
		</div>
	</div>
</body>

</html>