<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1,minimum-scale=1.0,user-scalable=no">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Worker/css/common-issue/base.css">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Worker/css/common-issue/common.css">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Worker/css/common-issue/questionList.css">
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>

	<title>问题列表</title>
</head>

<body>
	<div class="qustion-warp font0">
		<header class="qustion-header col-fff font-36 height88 bgheader tc">
			<a class="icon_back" href="javascript:history.back(-1)"></a>常见问题</header>
		<div class="qustion-list font-30 col-444 mt30">
			<ul class="lh46">

				<a href="${ctx}/app/worker/questionPage1.html">
					<li class="pt15 pb15 bg-fff pl30 pr30 mb30">
						<p class="fl">1.</p>
						<p class="pl20 questTip pr20 ">"我要签到"时，地址无法显示或点击“确定”没有反应怎么办？</p><span class="iconNext"></span></li>
				</a>
				<a href="${ctx}/app/worker/questionPage2.html">
					<li class="pt15 pb15 bg-fff pl30 pr30 mb30">
						<p class="fl">2.</p>
						<p class="pl20 questTip">"申请完工"时，照片上传不上去怎么办？</p><span class="iconNext"></span></li>
				</a>
			</ul>
		</div>
	</div>
</body>

</html>