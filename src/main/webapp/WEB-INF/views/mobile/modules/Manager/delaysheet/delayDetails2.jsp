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
	<title>延期单详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/applyDoneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/delay/delayList.css"/>
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="javascript:;"></a>
			<h2 class="title">延期单详情</h2>
		</header><!-- /header -->
		<section class="pt112 shadow mb12">
			<p class="pl30 pr30 pt30 pb28 font30 col_3 bor_top_ddd bor_dashed bold bg_f">鹿港嘉苑-10-3-2001-张三</p>
			<div class="sec pl30 pr30 font0 border_btm bg_f clearfix">
				<ul class="pad_top3">
					<li class="mb30 clearfix">
						<span class="col_6 font28 flo_left">延期阶段：</span>
						<p class="font28 col_3 pad_rgt3 flow">新房交底</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_6 font28 flo_left">延期类别：</span>
						<p class="font28 col_3 pad_rgt3 flow">工程原因</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_6 font28 flo_left">延期原因：</span>
						<p class="font28 col_3 pad_rgt3 flow">换工长</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_6 font28 flo_left">延期开始：</span>
						<p class="font28 col_3 pad_rgt3 flow">2016-08-28</p>
					</li>
					<li class="mb30 rele clearfix">
						<span class="col_6 font28 flo_left">延期结束：</span>
						<p class="font28 col_3 pad_rgt3 flow">2016-08-28</p>
					</li>
					<li class="mb30 rele clearfix">
						<span class="col_6 font28 flo_left">延期天数：</span>
						<p class="font28 col_3 pad_rgt3 flow">5天</p>
					</li>
					<li class="mb30 rele clearfix">
						<span class="col_6 font28 flo_left">延期说明：</span>
						<p class="font28 col_3 pad_rgt3 flow">因为工长问题导致项目更换工长，从而影响项目进度，客户已经同意。</p>
					</li>
				</ul>
			</div>
		</section>
		<section class="pt42">
			<p class="pt32 pl30 bor_top_ddd bg_f">
				<span class="font30 col_3 bold">证据照片</span>
			</p>
			<div class="pics font0 shadow clearfix">
				<div class="pic">
					<img src="../img/eg.png" alt="">
				</div>
				<div class="pic">
					<img src="../img/eg.png" alt="">
				</div>
				<div class="pic">
					<img src="../img/eg.png" alt="">
				</div>
				<div class="pic">
					<img src="../img/eg.png" alt="">
				</div>
				<div class="pic">
					<img src="../img/eg.png" alt="">
				</div>
			</div>
		</section>
		<div class="tips">
			<span class="tipTit">拒绝原因：</span>
			<span class="tipContent">信息填写不完整，证据不充分，无用户签字照片上传，请完善后在申请。</span>
		</div>
		<div class="pt80">
			<a class="subBtn" href="javascript:;">返回</a>
		</div>
		<div style="padding-bottom:300px;"></div>
		<div class="mask undis">
			<div class="bigImgWrap undis">
				<img src="../img/eg.png" alt="">
			</div>
			<div class="bigImgWrap undis">
				<img src="../img/eg.png" alt="">
			</div>
			<div class="bigImgWrap undis">
				<img src="../img/eg.png" alt="">
			</div>
			<div class="bigImgWrap undis">
				<img src="../img/eg.png" alt="">
			</div>
			<div class="bigImgWrap undis">
				<img src="../img/eg.png" alt="">
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script>
		$(document).on('click', '.pic img', function(){
			var i = $(this).parent().index();
			$('.mask').removeClass('undis');
			$('.bigImgWrap').addClass('undis');
			$('.bigImgWrap').eq(i).removeClass('undis');
		});
		$(document).on('click', '.mask', function(){
			$(this).addClass('undis');
		});
	</script>
</body>
</html>