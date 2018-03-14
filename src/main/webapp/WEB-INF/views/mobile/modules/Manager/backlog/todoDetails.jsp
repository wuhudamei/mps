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
	<title>待办事项</title>
	
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/todo.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/todoDetails.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/mask.css"/>
</head>
<body>
	<div class="font0">
		<input id="itemId" name="itemId" type="hidden" value="${backlog.id}"/>
		<header>
			<a class="back_btn" href="javascript:window.location.href = document.referrer;"></a>
			<h2 class="title">待办详情</h2>
		</header><!-- /header -->
		<section class="pt112">
			<div class="bg_f pl30 pr30">
				<p class="contentTit">${backlog.managerName }您好：</p>
				<div class="font30 col_6 lineHgt18 pt20 pb55">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span class="col_3">您的订单【${backlog.communityName }-${backlog.buildNumber }-${backlog.buildUnit }-${backlog.buildRoom }-${backlog.customerName } 】在“${backlog.remindTitle}”环节，质检员验收结果为合格，请及时提醒用户进行二期款缴纳，谢谢！</span></div>
			</div>
			<div class="tips">点击下方【立即提醒】系统会以短信的形式告知客户和设计师，请知晓！</div>
		</section>
		<footer>
			<a class="nowBtn col_f" href="javascript:;"><span class="warnBtnSpan font28">立即提醒</span></a>
			<a class="warnBtn col_7abcf8 font28" href="javascript:void(0);">已经提醒</a>
		</footer>
	</div>
	<div style="background:rgba(0,0,0,.4);z-index:9;display:none;" class="g-mask" id="alertMask" >
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div style="text-align:center;" class="font28 col_6 maskContent">已成功提醒，请知晓！</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f" href="javascript:;">确 定</a>
				</div>
			</div>
		</div>
	</div>
	<div style="padding-top:300px;"></div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript">
	$(document).on('click', '.warnBtnSpan', function(){
		var id = $("#itemId").val();
		$.ajax({
			url : "${ctx}/app/manager/backlog/sendMessage?id="+id,
			type : "get",
			success : function(data) {
				$("#alertMask").show();
			}

		});	
	});
	$(document).on('click', '.alertMask .maskBtns  .maskBtn', function(){
		$("#alertMask").hide();
		window.location.href='${ctx}/app/manager/backlog/list';
	});
	$(document).on('click', '.col_7abcf8', function(){
		var id = $("#itemId").val();
		window.location.href='${ctx}/app/manager/backlog/updateItemAndUrgeStatus?id='+id;
		//$("#alertMask").hide();
	});
	</script>
</body>
</html>