<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1,minimum-scale=1.0,user-scalable=no">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Worker/css/common-issue/base.css">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Worker/css/common-issue/common.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Worker/css/common-issue/questionList.css">
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<title>问题列表</title>
</head>

<body>
	<div class="qustion-warp font0">
		<header>
			<a class="back_btn" href="javascript:history.back(-1)"></a>
			<h2 class="title">常见问题</h2>
		</header><!-- /header -->
		<!-- <header class="qustion-header col-fff font-36 height88 bgheader tc">
			<a class="icon_back" href="javascript:history.back(-1)"></a>常见问题</header> -->
		<div class="qustion-list font-30 col-444 mt30">
			<ul class="lh46">

				<a href="${ctx}/app/manager/question-page1.html">
					<li class="pt15 pb15 bg-fff pl30 pr30 mb30">
						<p class="fl">1.</p>
						<p class="pl20 questTip pr20 ">“安装申请”时，安装项与实际业务对不上或者安装项少，怎么办？</p><span class="iconNext"></span></li>
				</a>
				<a href="${ctx}/app/manager/question-page2.html">
					<li class="pt15 pb15 bg-fff pl30 pr30 mb30">
						<p class="fl">2.</p>
						<p class="pl20 questTip">在给工人做任务包结算验收时，发现系统获取辅料的金额或者材料与实际送的材料对不上，怎么办？</p><span class="iconNext"></span></li>
				</a>
				<a href="${ctx}/app/manager/question-page3.html">
					<li class="pt15 pb15 bg-fff pl30 pr30 mb30">
						<p class="fl">3.</p>
						<p class="pl20 questTip">在做任务包的结算确认验收时，没有准确填写实际工程量，导致提报的实际工程量与工人干的工程量对不上，此时能否修改工程量？如何修改？</p><span class="iconNext"></span></li>
				</a>
				<a href="${ctx}/app/manager/question-page4.html">
					<li class="pt15 pb15 bg-fff pl30 pr30 mb30">
						<p class="fl">4.</p>
						<p class="pl20 questTip">开关面板申请量多时为什么不能提交？</p><span class="iconNext"></span></li>
				</a>
			</ul>
		</div>
	</div>
</body>

</html>