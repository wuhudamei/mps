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
				<div class="bor-b1" style="background: #fff">
					<div class="pl30 pr30 pt30 bg-fff">
						<h3 class="font-24 col-999 ">问题描述</h3>
						<p class="col-444 font-30 lh46 pt8 mb14">开关面板申请量多时为什么不能提交？</p>
					</div>
				</div>
				<div class="reason pl30 pr30 pt30 bg-fff mt30 pb30">
					<h3 class="font-24 col-999 ">解决办法</h3>
					<p class="col-444 font-30 lh46 pt8">开关面板的标准申请量=合同面积*0.48，需要上传客户同意申请的相关签字文件照片，即可申请。</p>
				</div>
			</div>
		</div>
	</div>
</body>

</html>