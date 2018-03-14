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
	<title>墙地砖申请详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/main_switch.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/main_wall.css"/>
</head>
<body>
	<div class="g-switch g-wall">
		<header>
			<a class="back_btn"  href="${ctx}/app/manager/wallAndFloorBrickRecord?id=${materialManagement.id}"></a>
			<h2 class="title">墙地砖申请详情</h2>
		</header><!-- /header -->
		<!-- <nav>
			<a href="javascript:void(0)"><span class="active">地砖</span></a>
			<a href="javascript:void(0)"><span>墙砖</span></a>
		</nav> -->
		<section class="swicth_sec wall_sec wall_details">
			<div class="switch_list shadow">
				<h3>${materialManagement.communityName }-${materialManagement.buildNumber }-${materialManagement.buildUnit }-${materialManagement.buildRoom }-${materialManagement.customerName }</h3>
				<ul class="wall_list wall clearfix" style="margin-bottom:0;">
					<li class="clearfix">
						<span>采购单编号 ：</span>
						<p>${purchase.purchaseCode }</p>
					</li>
					<li class="clearfix">
						<span>采购单状态 ：</span>
						<p>${purchase.statusName }</p>
					</li>
					<li class="clearfix">
						<span>期望进场日期 ：</span>
						<p><fmt:formatDate value="${purchase.applyReceiveTime }" pattern="yyyy-MM-dd"/></p>
					</li>
					<li class="evi_line" class="clearfix">
						<span>纸质 下料 单 ：</span>
						<p>
							<c:if test="${ not empty purchasePic }">
								<c:forEach items="${purchasePic}" var="purchasePic">
									<img class="evi" src="${baseUrl }${purchasePic.picUrl }" alt="纸质下料 单">
								</c:forEach>
							</c:if>
						</p>
					</li>
				</ul>
			</div>
			<nav>
				<a href="javascript:void(0)"><span class="active">地砖</span></a>
				<a href="javascript:void(0)"><span>墙砖</span></a>
			</nav>
			<div class="wall_all">
				<c:forEach items="${floor }" var="a">
					<ul class="wall_list shadow">
						<li class="clearfix">
							<span>项目名称 ：</span>
							<p>${a.mainMateTypeName }</p>
						</li>
						<li class="clearfix">
							<span>使用位置 ：</span>
							<p>${a.position }</p>
						</li>
						<li class="clearfix">
							<span>品牌套餐 ：</span>
							<p>${a.brandCombo }</p>
						</li>
						<li class="clearfix">
							<span>型号 ：</span>
							<p>${a.model } </p>
						</li>
						<li class="clearfix">
							<span>规格 ：</span>
							<p> ${a.specification }</p>
						</li>
						<li class="clearfix">
							<span>单位 ：</span>
							<p>${a.unit }</p>
						</li>
						<li class="clearfix">
							<span>属性 ：</span>
							<p>${a.attribute }</p>
						</li>
						<li class="clearfix">
							<span>供应商 ：</span>
							<p>${a.supplier }</p>
						</li>
			
						<li class="clearfix">
							<span>预估数量 ：</span>
							<p>${a.includLossCount } （含损耗数量）</p>
						</li>
						<li class="clearfix">
							<span>实际数量 ：</span>
							<p>${a.applyCounta }</p>
						</li>
						<li class="clearfix">
							<span>备注信息 ：</span>
							<p>${a.remarks }</p>
						</li>
					</ul>
				</c:forEach>
			</div>
			<div class="wall_all undis">
				<c:forEach items="${wall }" var="a">
					<ul class="wall_list shadow">
						<li class="clearfix">
							<span>项目名称 ：</span>
							<p>${a.mainMateTypeName }</p>
						</li>
						<li class="clearfix">
							<span>使用位置 ：</span>
							<p>${a.position }</p>
						</li>
						<li class="clearfix">
							<span>品牌套餐 ：</span>
							<p>${a.brandCombo }</p>
						</li>
						<li class="clearfix">
							<span>型号 ：</span>
							<p>${a.model } </p>
						</li>
						<li class="clearfix">
							<span>规格：</span>
							<p>  ${a.specification }</p>
						</li>
						<li class="clearfix">
							<span>单位 ：</span>
							<p>${a.unit }</p>
						</li>
						<li class="clearfix">
							<span>属性 ：</span>
							<p>${a.attribute }</p>
						</li>
						<li class="clearfix">
							<span>供应商 ：</span>
							<p>${a.supplier }</p>
						</li>
						
						<li class="clearfix">
							<span>预估数量 ：</span>
							<p>${a.includLossCount } （含损耗数量）</p>
						</li>
						<li class="clearfix">
							<span>实际数量 ：</span>
							<p>${a.applyCounta }</p>
						</li>
						<li class="clearfix">
							<span>备注信息 ：</span>
							<p>${a.remarks }</p>
						</li>
					</ul>
				</c:forEach>
			</div>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script>
	    $(function () {
	    	$('.g-wall nav a').each(function(index,val){
		    	$(val).click(function(){
		    		$('.g-wall nav a').find('span').removeClass('active');
		    		$('.g-wall nav a').eq(index).find('span').addClass('active');
		    		$('.g-wall .wall_all').addClass('undis');
					$('.g-wall .wall_all').eq(index).removeClass('undis');
		    	});
		    });
	    });
	</script>
</body>
</html>