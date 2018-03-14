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
	<title>消息详情</title>
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
			<a class="back_btn" onclick="history.go(-1)"></a>
			<h2 class="title">消息详情</h2>
		</header><!-- /header -->
		<section class="under font0 mar_btm5 pad_left3 pad_rgt3">
			<p class="font30 col_blue mar_top3 mar_btm4">${message.msgTitle }</p>
			<p class="font28 col_3 mar_top3 mar_btm5 line_hgt_em indent">
				${message.msgContent }
			</p>
			<p class="font28 col_6 align_rgt mar_btm3">大美装饰管理平台家装互联网公司</p>
			<p class="font24 col_6 align_rgt">
				<fmt:formatDate value="${message.msgTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</p>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
	<script type="text/javascript">
		;(function($){
		    $(function(){
		        var $divWidth = $('img').width();
		        $('img').css({'height':$divWidth});
		    })
		})(jQuery)
	</script>
</body>
</html>