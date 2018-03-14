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
				<ul class="pt30 pb30">
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">项目经理：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.itemManager}</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">实测状态：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.statusDescribe}</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">预算面积：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.squareBudget} ㎡<span class="font26 col_e90f0f fr">图纸面积*108%</span></p>
						
					</li> 
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">定额面积：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.squareQuota} ㎡<span class="font26 col_e90f0f fr">预算面积+1㎡损耗</span></p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">实测面积：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.squareMeasure} ㎡<span class="font26 col_e90f0f fr">现场交底面积</span></p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">实际申请：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.squarePurchase} ㎡</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">计划测量：</span>
						<p class="font30 col_3 flow"><fmt:formatDate value="${wallFloorTileRecheck.planMeasureDate }" pattern="yyyy-MM-dd"/></p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">现场实测：</span>
						<p class="font30 col_3 flow"><fmt:formatDate value="${wallFloorTileRecheck.realMeasureDate }" pattern="yyyy-MM-dd"/></p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">实测说明：</span>
						<p class="font30 col_3 flow">${wallFloorTileRecheck.measureRemarks}</p>
					</li>
				</ul>
			</div>
			<section class="pt24 mb30 shadow">
				<p class="pb20">
					<span class="pl30 font30 col_blue bold">实测照片</span>
				</p>
				<div class="pics bor_top_ddd font0 shadow bg_f clearfix">
					
					<c:forEach items="${picList }" var="p">
						<div class="pic">
							<img src="${baseUrl}${p.picUrl }" alt="">
						</div>
					</c:forEach>
					
					
					
				</div>
			</section>
			<div class="sec pl30 pr30 pb30 font0 bor_top_e5 bg_f shadow clearfix">
				<c:forEach items="${list }" var="log">
					<ul class="pt30">
						<li class="mb30 clearfix">
							<span class="col_grey font30 fl">审批节点：</span>
							<p class="font30 col_3 flow">${log.businessStatusName }</span></p>
						</li> 
						<li class="mb30 clearfix">
							<span class="col_grey font30 fl pl1em">审批人：</span>
							<p class="font30 col_3 flow">${log.businessEmployeeName }</p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font30 fl pl1em">审批时间：</span>
							<p class="font30 col_3 flow"><fmt:formatDate value="${log.statusDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
						</li>
						<li class="pb30 clearfix bor_btm_e5">
							<span class="col_grey font30 fl pl1em">审批意见：</span>
							<p class="font30 col_3 flow">${log.businessRemarks }</p>
						</li>
					</ul>
				</c:forEach>
			</div>
		</section>
		<div class="pt50">
			<a class="subBtn" href="${ctx}/app/manager/wallFloorTileRecheck/list">返 回</a>
		</div>
		<div style="padding-bottom:200px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>
</html>