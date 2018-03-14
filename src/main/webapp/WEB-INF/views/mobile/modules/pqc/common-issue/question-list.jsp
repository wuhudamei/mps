<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1,minimum-scale=1.0,user-scalable=no">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/pqc/css/common-issue/base.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/pqc/css/common-issue/common.css">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/pqc/css/common-issue/questionList.css">

	<title>问题列表</title>
</head>

<body>
	<div class="qustion-warp font0">
		<header>
			<a class="back_btn" onclick="back()"></a>
			<h2 class="title">常见问题</h2>
		</header><!-- /header -->
		<!-- <header class="qustion-header col-fff font-36 height88 bgheader tc" onclick="back()"><span class="icon_back"></span>常见问题</header> -->
		<div class="qustion-list font-30 col-444 mt30">
			<ul class="lh46">
				<a href="${ctx}/app/pqc/question-page1.html">
					<li class="pt15 pb15 bg-fff pl30 pr30 mb30">
						<p class="fl">1.</p>
						<p class="pl20 questTip pr20 ">为什么会出现复检？</p><span class="iconNext"></span></li>
				</a>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript">
function	back(){
	    window.location.href="${ctx}/app/pqc/indexMine";

	}

</script>
</html>