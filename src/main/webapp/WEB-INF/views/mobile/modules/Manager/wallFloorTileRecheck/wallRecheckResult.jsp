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
	<title>墙地砖实测面积复核</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/reset.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/header.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/common.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/Details.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/apply.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/wallSub.css" />
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/wallFloorTileRecheck/list"></a>
			<h2 class="title">墙地砖实测面积复核</h2>
		</header>
		<section class="pad_top88">
			<p class="pt20 pb20 align_center">
				<span class="font30 col_blue">${wallFloorTileRecheck.communityName }-${wallFloorTileRecheck.buildNumber }-${wallFloorTileRecheck.buildUnit }-${wallFloorTileRecheck.buildRoom }-${wallFloorTileRecheck.customerName }</span>
			</p>
			<div class="sec pl30 pr30 font0 bor_top_e5 bg_f shadow clearfix">
				<ul class="pt30 pb30 bor_dashed">
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">预算面积：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.squareBudget} ㎡<span class="font26 col_e90f0f fr">图纸面积*108%</span></p>
						
					</li> 
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">定额面积：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.squareQuota} ㎡<span class="font26 col_e90f0f fr">预算面积+1㎡损耗</span></p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">现场实测：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.squareMeasure} ㎡<span class="font26 col_e90f0f fr">含损耗面积</span></p>
					</li>
					<li class="clearfix">
						<span class="col_grey font30 fl">实际申请：</span>
						<p class="font30 col_3">${wallFloorTileRecheck.squarePurchase} ㎡<a class="detailsBtn font26 col_blue fr" >查看明细</a></p>
					</li>
				</ul>
				<ul class="pt30 pb30">
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">考核下砖量A ：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.assessSquareError1}</span></p>
						
					</li> 
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">考核下砖量B ：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.assessSquareError2}</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl pl1em">墙地砖单价：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.price}元</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl pl1em">考核金额一：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.assessAmount1}</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl pl1em">考核金额二：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.assessAmount2}</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl pl2em">考核人一：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.assessPersonName1}</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl pl2em">考核人二：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.assessPersonName2}</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl pl4em">备注：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.remarks}</p>
					</li>
				</ul>
			</div>
		</section>
		<div class="pt50">
			<a class="subBtn" href="${ctx}/app/manager/wallFloorTileRecheck/list">返 回</a>
		</div>
		<div style="padding-bottom:200px;"></div>
		<div id="detailsMask" class="mask undis">
			<ul class="ulWrapper">
				<li class="ulList clearfix">
					<span class="col_6 font30">墙砖：</span>
					<span class="font30 col_3 flow">${wallSquareCount } m2</span>
				</li>
				<li class="ulList clearfix">
					<span class="col_6 font30">地　砖：</span>
					<span class="font30 col_3 flow">${floorSquareCount } m2</span>
				</li>
				<p class="font34 col_blue pt26 pb38">合计：${wallFloorSquareCount } ㎡</p>
				<a class="ulBtn" >确定</a>
			</ul>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script>
		$(document).on('click','.detailsBtn',function(){
        	$('#detailsMask').removeClass('undis');
        });
        $(document).on('click','.ulBtn',function(){
        	$('#detailsMask').addClass('undis');
        });
	</script>
</body>
</html>