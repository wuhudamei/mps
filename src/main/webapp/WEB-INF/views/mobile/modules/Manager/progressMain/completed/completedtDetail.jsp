<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>竣工详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/complete.css"/>
</head>
<body>
	<div class="g-sign_list">
		<header class="header">
			<a class="back_btn" href="${ctx}/app/manager/completedList"></a>
			<h2 class="title">竣工详情</h2>
		</header><!-- /header -->
		<ul class="pad_top2 pad_btm2 intro">
			<li class="clearfix">
				<span class="intro_left">顾客信息：</span>
				<p class="intro_right">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
			</li>
			<li class="clearfix">
				<span class="intro_left">申请日期：</span>
				<p class="intro_right"><fmt:formatDate type="date" value="${orderFinishProBill.applyDatetime }"/></p>
			</li>
			<li class="clearfix">
				<span class="intro_left"><label for="input_date">实际竣工日期：</label></span>
				<p class="intro_right"><fmt:formatDate type="date" value="${orderFinishProBill.realFinishProjectDate }"/></p>
			</li>
			<li class="clearfix">
				<span class="intro_left">订单状态：</span>
				<p class="intro_right">${fns:getDictLabel(order.orderStatusNumber, 'order_status', '')}</p>
			</li>
		</ul>
		<section class="photo_contain contain2">
			<div class="camera_container clearfix">
				<p class="col_6 font28 float_left"><em class="before font28 col_blue">*</em>工程竣工验收单<em class="after font28 col_blue">*</em></p>
				<div class="pic camera cameras" id="camera1">
				<c:if test="${busPicList== null || fn:length(busPicList) == 0}">
					<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="单据">
				</c:if>
				<c:if test="${busPicList != null || fn:length(busPicList) > 0}">
					<c:forEach items="${busPicList}" var="picList">
						<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="单据">
					<c:if test="${picList.businessType == '101' }">
						<img class="plus" src="${picPrefixName }${picList.picUrl }" alt="单据">
					</c:if>
					</c:forEach>
				</c:if>
				</div>
			</div>
			<div class="camera_container clearfix">
				<p class="col_6 font28 float_left"><em class="before font28 col_blue">*</em>项目总结书<em class="after font28 col_blue">*</em></p>
				<div class="pic camera cameras" id="camera2">
				<c:if test="${busPicList== null || fn:length(busPicList) == 0}">
					<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="单据">
				</c:if>
				<c:if test="${busPicList != null || fn:length(busPicList) > 0}">
				<c:forEach items="${busPicList}" var="picList">
				<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="单据">
				<c:if test="${picList.businessType == '102' }">
					<img class="plus" src="${picPrefixName }${picList.picUrl }" alt="单据">
				</c:if>
				<c:if test="${picList.businessType != '102' }">
					<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="单据">
				</c:if>
				</c:forEach>
				</c:if>
				</div>
			</div>
			<div class="camera_container clearfix">
				<p class="col_6 font28 float_left">延期确认单</p>
				<div class="pic camera cameras" id="camera3">
					<c:if test="${busPicList== null || fn:length(busPicList) == 0}">
						<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="单据">
					</c:if>
					<c:if test="${busPicList != null || fn:length(busPicList) > 0}">
						<c:forEach items="${busPicList}" var="picList">
							<c:if test="${picList.businessType == '103' }">
								<img id="yq" class="plus" src="${picPrefixName }${picList.picUrl }" alt="单据">
							</c:if>
							<c:if test="${picList.businessType != '103' }">
								<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="单据">
							</c:if>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</section>
		
		<!-- 查看大图弹出层 -->
		<!-- <div class="mask mask2 undis">
			<div class="big_pic_wraper">
				<img class="big_pic" id="big_pic" alt="单据">
			</div>
		</div> -->
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript">
		/* $(document).on('click', '#camera3 img', function() {
			var purl = $(this).attr("src");
			$("#big_pic").attr("src",purl);
			$('.mask2').removeClass('undis');
		}); */
		
		
	</script>
</body>
</html>