<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1,minimum-scale=1.0,user-scalable=no">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/pqc/css/common-issue/base.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/pqc/css/common-issue/common.css">
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<title>问题详情</title>
</head>

<body>
	<div class="qustion-warp font0">
		<header>
			<a class="back_btn" onclick="history.go(-1)"></a>
			<h2 class="title">常见问题</h2>
		</header><!-- /header -->
		<!-- <header class="qustion-header col-fff font-36 height88 bgheader tc" onclick="history.go(-1)">
			<span class="icon_back"></span> 常见问题
		</header> -->
		<div class="qustionPage font-30 col-444 pad-top110">
			<div class="desc">
				<div class="bor-b1">
					<div class="pl30 pr30 pt30 bg-fff">
						<h3 class="font-24 col-999 ">问题描述</h3>
						<p class="col-444 font-30 lh46 pt8 mb14">为什么会出现复检？</p>
						<h3 class="font-24 col-999 mb14">图例：</h3>
						<div class="pb28 pr30 pl30">
							<img src="${ctxStatic}/mobile/modules/pqc/images/common-issue/1.png" alt="">
						</div>
					</div>
				</div>
				<div class="reason pl30 pr30 pt30 bg-fff mt30 pb30">
					<h3 class="font-24 col-999 ">原因及解决办法</h3>
					<p class="col-444 font-30 lh46 pt8">在约检和抽检时对不合格项，做线上限期整改，需要再次进行检查，会在复检中体现。</p>
				</div>
			</div>
		</div>
	</div>
</body>

</html>