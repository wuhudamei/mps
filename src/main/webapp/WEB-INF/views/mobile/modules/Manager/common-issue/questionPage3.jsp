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
			<a class="icon_back" href="javascript:history.back(-1)"></a>
			常见问题</header> -->
		<header>
			<a class="back_btn" href="javascript:history.back(-1)"></a>
			<h2 class="title">常见问题</h2>
		</header><!-- /header -->
		<div class="qustionPage font-30 col-444 pad-top110">
			<div class="desc">
				<div class="bor-b1">
					<div class="pl30 pr30 pt30 bg-fff">
						<h3 class="font-24 col-999 ">问题描述</h3>
						<p class="col-444 font-30 lh46 pt8 mb14">在做任务包的结算确认验收时，没有准确填写实际工程量，导致提报的实际工程量与工人干的工程量对不上，此时能否修改工程量？如何修改？</p>
						<h3 class="font-24 col-999 mb14">图例：</h3>
						<div class="pb28 pl30 pr30">
							<img src="${ctxStatic}/mobile/modules/Manager/images/common-issue/3.png" alt="">
							<img src="${ctxStatic}/mobile/modules/Manager/images/common-issue/4.png" alt="">
						</div>
					</div>
				</div>
				<div class="reason pl30 pr30 pt30 bg-fff mt30 pb30">
					<h3 class="font-24 col-999 ">原因及解决办法</h3>
					<p class="col-444 font-30 lh46 pt8">在项目经理确认验收后，质检员在APP上验收合格，工程量不能再修改，填写框变为灰色；质检员确认验收不合格，此时项目经理可以修改工程量。</p>
				</div>
				<div class="bg-fff pt30 mt30 pl30 pr30 pb30">
					<h3 class="font-24 col-999 mb14">修改方法</h3>
					<p class="col-444 font-30 lh50">
						<p class="fl"></p>
						<p class="pl34 lh50">让工人或结算员驳回，项目经理在“结算单管理”里修改结算单，再次确认验收即可。</p>
				</div>

			</div>
		</div>
	</div>
</body>

</html>