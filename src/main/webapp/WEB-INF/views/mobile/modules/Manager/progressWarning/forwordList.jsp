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
	<title>进度预警</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/forward/forwardList.css"/>
</head>
<body>
	<div class="">
		<header class="header">
			<a class="back_btn" href="${ctx}/app/manager/progressList"></a>
			<h2 class="title">进度预警</h2>
		</header><!-- /header -->
		<section class="pad_top88 font0">
			<ul class="mar_top3 nav">
				<a class="list-line100 bg_f forwordMetri" href="javascript:void(0)"><li class="list-text100">材料<span class="frAccout" id = "orderSum">延期订单总数：${count }
				<input id = "orderSumInput" hidden="hidden" value="${count }">
				</span></li></a>
				<ul class="lists undis">
				<c:forEach items ="${list}" var = "materila">
				<c:if test = "${materila.purchaseType == '1'}">
				<a class="list-line100 meterAuxi" href="${ctx}/app/manager/progressWarning/from?purchaseType=${materila.purchaseType}"><li class="list-text">辅材进场<span class="frNum">延期订单：${materila.count}</span></li></a>
				</c:if>
				<c:if test = "${materila.purchaseType == '5'}">
				<a class="list-line100 meterAuxi" href="${ctx}/app/manager/progressWarning/from?purchaseType=${materila.purchaseType}""><li class="list-text">瓷砖进场<span class="frNum">延期订单：${materila.count}</span></li></a>
				</c:if>
				</c:forEach>
				</ul>
			</ul>
			<!-- <ul class="nav">
				<a class="list-line100 bg_f mar_top3 forwordCheck" href="javascript:void(0)"><li class="list-text100">约检验收<span class="frAccout col_ff3b60">延期订单总数：10</span></li></a>
				<ul class="lists undis">
					<a class="list-line100 checkWater" href="javascript:void(0)"><li class="list-text">水电隐蔽验收<span class="frNum">延期订单：3</span></li></a>
					<a class="list-line100 checkWater" href="javascript:void(0)"><li class="list-text">防水验收<span class="frNum">延期订单：3</span></li></a>
					<a class="list-line100 checkWater" href="javascript:void(0)"><li class="list-text">瓦工验收<span class="frNum">延期订单：3</span></li></a>
					<a class="list-line100 checkWater" href="javascript:void(0)"><li class="list-text">基础施工验收<span class="frNum">延期订单：3</span></li></a>
					<a class="list-line100 checkWater" href="javascript:void(0)"><li class="list-text">竣工验收<span class="frNum">延期订单：3</span></li></a>
				</ul>
			</ul> -->
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script>
		;(function(){
			$(document).on('click','.nav > a',function(){
				if ($(this).find('li').hasClass('active')) {
					$(this).find('li').removeClass('active');
					$(this).find('li').addClass('list-text100');
					$(this).parent().find('.lists').addClass('undis');
				}else{
					$(this).find('li').removeClass('list-text100');
					$(this).find('li').addClass('active');
					$(this).parent().find('.lists').removeClass('undis');
				}
			})
		}());
		$(function(){
			var v = $("#orderSumInput").val();
			if(v = 0){
				
				$("#orderSum").css("color","green")
			}
			if(v > 5){
				
				$("#orderSum").css("color","yellow")
			}
			if(v < 5){
				
				$("#orderSum").css("color","red")
			}
			
		});
		
	</script>
</body>
</html>