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
	<title>申请自采材料报销记录</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css"/>
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/budget/selfMetriRecordDetails.css">
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/applyMaterialSelfbuyReimburse/materialSelfbuyReimburseRecord?orderId=${orderId}"></a>
			<h2 class="title">申请自采材料报销记录</h2>
		</header><!-- /header -->
		<div class="pad_top88 pad_btm3 bg_bo align_center">
			<p class="font30 col_blue pad_top3 pad_left3 pad_rgt3">${materialManagement.communityName }-${materialManagement.buildNumber }-${materialManagement.buildUnit }-${materialManagement.buildRoom }-${materialManagement.customerName }</p>
		</div>
		<section>
			<c:forEach items="${list }" var="material">
				<div class="mar_btm26 bor_top_ea bor_btm_ea">
					<div class="font28 col_blue pad_top24 pad_btm24 pad_left3 pad_rgt3 bor_btm_ccc rela"><fmt:formatDate value="${material.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						<a class="font28 col_blue flo_rgt seeBtn" href="${ctx}/app/manager/applyMaterialSelfbuyReimburse/detailsPic?materialId=${material.id}">查看照片</a>
					</div>
					<ul class="bg_f pad_left3 pad_rgt3 pad_top24 pad_btm24 list">
						<li class="mar_btm26 clearfix">
							<span class="font28 col_9 spanRgt33 flo_left">自采材料名称：</span>
							<p class="font28 col_10 flow">${material.materialName }</p>
						</li>
						<li class="mar_btm26 clearfix">
							<span class="font28 col_9 spanRgt33 flo_left">客户交费金额：</span>
							<p class="font28 col_10 flow">${material.customerPayAmount }元</p>
						</li>
						<li class="mar_btm26 clearfix">
							<span class="font28 col_9 spanRgt33 flo_left">结算比例：</span>
							<p class="font28 col_10 flow">${material.settleRate }%</p>
						</li>
						<li class="mar_btm26 clearfix">
							<span class="font28 col_9 spanRgt33 flo_left">实际结算金额：</span>
							<p class="font28 col_10 flow">${material.settleAmount }元</p>
						</li>
						
						<c:forEach items="${statusLogList }" var="status">
							<c:if test="${status.businessOnlyMarkInt eq material.id }">
								<c:if test="${status.businessStatus eq 10 || status.businessStatus eq 15 }">
								
									<li class="mar_btm26 clearfix">
										<span class="font28 col_9 spanRgt33 flo_left">状态：</span>
										<p class="font28 col_10 flow">${status.statusName}</p>
									</li>
									<li class="mar_btm26 clearfix">
										<span class="font28 col_9 spanRgt33 flo_left">说明：</span>
										<p class="font28 col_10 flow">${status.businessRemarks}</p>
									</li>
									<li class="mar_btm26 clearfix">
										<span class="font28 col_9 spanRgt33 flo_left">提交人：</span>
											<p class="font28 col_10 flow">
												<c:if test="${not empty status.businessEmployeeName}">
													${status.businessEmployeeName}
												</c:if>
												<c:if test="${empty status.businessEmployeeName && status.createById eq 1}">
													系统管理员
												</c:if>
											</p>
									</li>
									
								</c:if>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				
				<c:forEach  items="${statusLogList }" var="log">
					<c:if test="${log.businessOnlyMarkInt eq material.id }">
						<c:if test="${log.businessStatus eq 20 || log.businessStatus eq 25}">
							<div class="mar_btm26 bor_top_ea bor_btm_ea">
								<div class="font28 col_blue pad_top24 pad_btm24 pad_left3 pad_rgt3 bor_btm_ccc rela"><fmt:formatDate value="${log.statusDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
								<ul class="bg_f pad_left3 pad_rgt3 pad_top24 pad_btm24 list">
									<li class="mar_btm26 clearfix">
										<span class="font28 col_9 spanRgt33 flo_left">结算员：</span>
										<p class="font28 col_10 flow">
											<c:if test="${not empty log.businessEmployeeName}">
												${log.businessEmployeeName}
											</c:if>
											<c:if test="${empty log.businessEmployeeName && log.createById eq 1}">
												系统管理员
											</c:if>
										</p>
									</li>
									<li class="mar_btm26 clearfix">
										<span class="font28 col_9 spanRgt33 flo_left">状态：</span>
										<p class="font28 col_10 flow">${log.statusName }</p>
									</li>
									<c:if test="${log.businessStatus eq 25}">
										<li class="mar_btm26 clearfix">
											<span class="font28 col_9 spanRgt33 flo_left">驳回原因：</span>
											<p class="font28 col_10 flow">${log.businessRemarks }</p>
										</li>
									</c:if>
								</ul>
								
							</div>
						</c:if>
					</c:if>
				</c:forEach>
				
			</c:forEach>
			
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
</html>