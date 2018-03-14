<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/mobile/modules/home/footer.jsp" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>施工进度</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/buildProgress.css"/>
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx }/app/home/projectprogress/list.php"></a>
			<h2 class="title">施工进度</h2>
		</header><!-- /header -->
		<c:if test="${none!=1}" >
		<div class="pad_top112">
			<p class="tit-wrap bg_f"><span class="tit-p">基本信息</span></p>
			<ul class="pad_left3 pad_top24 pad_btm24 bg_f border_btm_lgt">
				<li>
					<span class="font28 col_grey">合同开工日期：</span>
					<span class="font28 col_3">
						<fmt:formatDate value="${progressVo.contractStartDate }" pattern="yyyy-MM-dd"/>
					</span>
				</li>
				<li class="pad_top28">
					<span class="font28 col_grey">实际开工日期：</span>
					<span class="font28 col_3">
					<fmt:formatDate value="${progressVo.actualStartDate }" pattern="yyyy-MM-dd"/>
					</span>
				</li>
			</ul>
		</div>
		<div class="pad_top24 mar_btm300">
			<p class="tit-wrap bg_f"><span class="tit-p">确认开工信息</span></p>
			<ul class="pad_left3 pad_top24 pad_btm24 bg_f border_btm_lgt">
				<li>
					<span class="font28 col_grey">开工客户签字 ：</span>
					<span class="font28 col_3">
					<c:if test="${progressVo.isNeedSign ==1 }">是</c:if>
					<c:if test="${progressVo.isNeedSign ==0 }">否</c:if>
					
					</span>
				</li>
				<li>
					<span class="font28 col_grey">开工延期类型：</span>
					<span class="font28 col_3">
						<c:if test="${progressVo.delayType ==1 }">客戶</c:if>
					<c:if test="${progressVo.delayType ==0}">公司</c:if>
					</span>
				</li>
				<li class="pad_top28">
					<span class="font28 col_grey">开工延期说明：</span>
					<span class="font28 col_3">${progressVo.delayDescription }</span>
				</li>
				<li class="pad_top28">
					<span class="font28 col_grey">自装延期天数：</span>
					<span class="font28 col_3">${progressVo.delayDays }天</span>
				</li>
				<li class="pad_top28">
					<span class="font28 col_grey">自装客户签字：</span>
					<span class="font28 col_3">
						<c:if test="${progressVo.selfNeedSign ==1 }">是</c:if>
					<c:if test="${progressVo.selfNeedSign ==0}">否</c:if>
					</span>
				</li>
			</ul>
		</div>
		</c:if>
		<c:if test="${none==1}" >
			<div class="pad_top112">
			<p class="tit-wrap bg_f"><span class="tit-p">这里什么也没有</span></p>
		</div>
		</c:if>
	</div>

	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" >

	</script>
</body>
</html>