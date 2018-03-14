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
			<a class="back_btn" href="${ctx}/app/manager/delaysheet/list3"></a>
			<h2 class="title">延期单详情</h2>
		</header><!-- /header -->
		<section class="pt112 shadow mb12">
			<p class="pl30 pr30 pt30 pb28 font30 col_3 bor_top_ddd bor_dashed bold bg_f">${delay.communityName }-${delay.buildNumber }-${delay.buildUnit }-${delay.buildRoom }-${delay.customerName }</p>
			<div class="sec pl30 pr30 font0 border_btm bg_f clearfix">
				<ul class="pad_top3">
					<li class="mb30 clearfix">
						<span class="col_6 font28 flo_left">延期阶段：</span>
						<p class="font28 col_3 pad_rgt3 flow">${delay.delayBillStageStatusName }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_6 font28 flo_left">延期类别：</span>
						<p class="font28 col_3 pad_rgt3 flow">${delay.delayBillCategoryId }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_6 font28 flo_left">延期原因：</span>
						<p class="font28 col_3 pad_rgt3 flow">${delay.delayBillCategoryIdReson }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_6 font28 flo_left">延期开始：</span>
						<p class="font28 col_3 pad_rgt3 flow">${delay.delayBeginDatetime }</p>
					</li>
					<li class="mb30 rele clearfix">
						<span class="col_6 font28 flo_left">延期结束：</span>
						<p class="font28 col_3 pad_rgt3 flow">${delay.delayEndDatetime }</p>
					</li>
					<li class="mb30 rele clearfix">
						<span class="col_6 font28 flo_left">延期天数：</span>
						<p class="font28 col_3 pad_rgt3 flow">${delay.delayDays }天</p>
					</li>
					<li class="mb30 rele clearfix">
						<span class="col_6 font28 flo_left">延期说明：</span>
						<p class="font28 col_3 pad_rgt3 flow">${delay.deferredInstruction }</p>
					</li>
				</ul>
			</div>
		</section>
		<section class="pt42">
			<p class="pt32 pl30 bor_top_ddd bg_f">
				<span class="font30 col_3 bold">证据照片</span>
			</p>
			<div class="pics font0 shadow clearfix">
				<c:forEach items="${picList }" var="p">
					<div class="pic">
						<img src="${baseUrl}${p.picUrl }">
					</div>
				</c:forEach>
			</div>
		</section>
		<c:if test="${delay.status == 15 }">
		<div class="tips">
			<span class="tipTit">拒绝原因：</span>
			<span class="tipContent">${delay.remarks }</span>
		</div>
		<div class="pt80">
				 <a class="subBtn" href="${ctx }/app/manager/delaysheet/list2?orderId=${delay.orderId }&id=${delay.id }">重新申请</a>
		</div>
		
		</c:if>
		<c:if test="${delay.status != 15 }">
			<div class="pt80">
				<a class="subBtn" onclick="history.go(-1)">返回</a>
			</div>
		</c:if>
		
		
		<div style="padding-bottom:300px;"></div>
		<div class="mask undis">
			<c:forEach items="${picList }" var="p">
					<div class="bigImgWrap undis">
						<img src="${baseUrl}${p.picUrl }">
					</div>
				</c:forEach>
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