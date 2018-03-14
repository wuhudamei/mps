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
	<title>延期申请</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/applyDoneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/delay/delayList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/mask.css"/>
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/toindex"></a>
			<h2 class="title">延期申请</h2>
		</header><!-- /header -->
		<section class="pad_top88">
		<c:forEach items = "${list }" var="delay"> 
			<div class="sec font0 clearfix">
				<p class="col_0780ec pt30 pb20 pl30"><span class="font30 pr30 pl22 blueBar">延期申请</span><span class="font28">${delay.deferredApplicationDatetime }</span></p>
				<ul class="pl30 pr30 pad_top3 bg_f bor_top_ddd<c:if test="${delay.isScrap eq '1'}"> abandon</c:if>">
					<li class="mb30 clearfix">
						<span class="col_grey font28 flo_left">客户信息：</span>
						<p class="font28 col_3 pad_rgt3 flow">${delay.communityName }-${delay.buildNumber }-${delay.buildUnit }-${delay.buildRoom }-${delay.customerName }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font28 flo_left">延期原因：</span>
						<p class="font28 col_3 pad_rgt3 flow">${delay.delayBillCategoryId }</p>
					</li>
					<li class="mb30 rele clearfix">
						<span class="col_grey font28 flo_left">延期开始：</span>
						<p class="font28 col_3 pad_rgt3 flow">${delay.delayBeginDatetime }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font28 flo_left">延期结束：</span>
						<p class="font28 col_3 pad_rgt3 flow">${delay.delayEndDatetime }</p>
					</li>
					<li class="pb30 clearfix">
						<span class="col_grey font28 flo_left">订单状态：</span>
						<p class="font28 col_cf1709 pad_rgt3 flow">${delay.orderStatus }</p>
					</li>
					<li class="pb30 bor_dashed clearfix">
						<span class="col_grey font28 flo_left">审批状态：</span>
						<p class="font28 col_cf1709 pad_rgt3 flow">
						${fns:getDictLabel(delay.status,'delayed_approval_status','')}
						</p>
					</li>
				</ul>
				<c:if test="${empty delay.status}">
					<div class="bg_f">
						<div class="btn_wrapper clearfix">
							<a class="btnBlueBorder" onclick="applyDelay(${delay.orderId },${delay.isScrap })">申请延期</a>
						</div>
					</div>
				</c:if>
				<c:if test="${delay.status == 10}">
					<div class="bg_f">
						<div class="btn_wrapper clearfix">
							<a class="btnBlueBorder" href="${ctx }/app/manager/delaysheet/list?id=${delay.id }">查看详情</a>
						</div>
					</div>
				</c:if>
				<c:if test="${delay.status == 15}">
					<div class="bg_f">
						<div class="btn_wrapper clearfix">
							<a class="btnBlueBorder" href="${ctx }/app/manager/delaysheet/list?id=${delay.id }">查看详情</a>
						</div>
					</div>
				</c:if>
				<c:if test="${delay.status == 20 || delay.status == 90}">
					<div class="bg_f">
						<div class="btn_wrapper clearfix">
							<a class="btnBlueBorder" onclick="applyDelay(${delay.orderId },${delay.isScrap })">申请延期</a>
						</div>
					</div>
				</c:if>
			</div>
		</c:forEach>
		</section>
		<div style="padding-bottom:300px;"></div>
		<div class="g-mask undis" id = "tip">
			<div class="alertMask">
				<div class="maskWrapper">
					<p class="col_3 maskTit">通知</p>
					<div class="font28 col_6 maskContent align_ctr">该订单已作废了，不允许延期申请了！</div>
					<div class="maskBtns clearfix">
						<a class="maskBtn font33 col_f" onclick="closewin()">我知道了</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript">
		function closewin(){
			$("#tip").hide();
		}
		function applyDelay(orderId,isScrap){
			if(isScrap == '1'){
				$("#tip").show();
			}else{
				window.location.href="${ctx }/app/manager/delaysheet/list2?orderId="+orderId;
			}
				
		}
	</script>
</body>
</html>