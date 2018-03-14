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
	<title>提交墙地砖（空白页面）</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/message_details.css"/>
	<style type="text/css">
		.indent p{width:100%;}
		img{width:100%!important;}
	</style>
</head>
<body>
	<div class="g-message">
		<header class="">
			<a class="back_btn" href="${ctx}/app/manager/materialManagement/wallAndFloorNew/list"></a>
			<h2 class="title">提交墙地砖</h2>
		</header><!-- /header -->
		<section class="under font0 mar_btm5 pad_left3 pad_rgt3">
			<p class="font30 col_blue mar_top3 mar_btm4">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
			<p class="font28 col_3 mar_top3 mar_btm5 line_hgt_em indent">
				尊敬的项目经理：
			</p>
			<p class="font28 col_3 mar_top3 mar_btm5 line_hgt_em indent">
				工程部文员还未添加墙地砖数据，请联系工程部文员或大区经理。
			</p>
			<p class="font28 col_6 align_rgt mar_btm3">美得你家装互联网公司</p>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
	<script>
		;(function($){
		    $(function(){
		        var $divWidth = $('img').width();
		        $('img').css({'height':$divWidth});
		    })
		})(jQuery)
	</script>
</body>
</html>